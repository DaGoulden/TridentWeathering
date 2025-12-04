package net.goulden.tridentweathering;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.ProjectileImpactEvent;

public class MakingThunder {

    protected static int wait;
    protected static int bolts;
    protected static double probability;

    private static final String TAG_ACTIVE = "TridentWeathering.Active";
    private static final String TAG_LIGHTNING_BOLTS_TRIGGERED = "TridentWeathering.LightningBoltsTriggered";

    public static void makeThunder(ThrownTrident trident) {

        Level tLevel = trident.level();
        if (tLevel.isClientSide()) return;
        if (!(trident.tickCount % 10 == 1)) return;
        if (!(tLevel.isRainingAt(trident.blockPosition()))) return;
        /*if (Objects.requireNonNull(
                trident.getPickupItemStackOrigin().get(DataComponents.ENCHANTMENTS))
                    .getLevel(tLevel.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.CHANNELING))
        < 1) return;*/

        CompoundTag tData = trident.getPersistentData();
        if (trident.getXRot() > 85 && trident.tickCount == 1) {
            tData.putBoolean(TAG_ACTIVE, true);
            tData.putInt(TAG_LIGHTNING_BOLTS_TRIGGERED, 0);
        }

        if (tData.getBoolean(TAG_ACTIVE) && tLevel.canSeeSkyFromBelowWater(trident.blockPosition())) {
            int triggers = (trident.tickCount - 1) / 10;
            if (triggers > wait && triggers <= wait + bolts) {
                if (tLevel.random.nextDouble() < probability) {
                    LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(tLevel);
                    if (bolt != null) {
                        bolt.setPos(trident.position());
                        bolt.setVisualOnly(true);
                        bolt.getPersistentData().putBoolean(TAG_ACTIVE, true);
                        tLevel.addFreshEntity(bolt);
                    }
                    tData.putInt(TAG_LIGHTNING_BOLTS_TRIGGERED, tData.getInt(TAG_LIGHTNING_BOLTS_TRIGGERED) + 1);
                }
            } else if (triggers > wait + bolts && tData.getInt(TAG_LIGHTNING_BOLTS_TRIGGERED) == bolts) {
                ServerLevel serverLevel = ((ServerLevel) tLevel);
                serverLevel.setWeatherParameters(0, ServerLevel.THUNDER_DURATION.sample(serverLevel.getRandom()), true, true);
                tData.putBoolean(TAG_ACTIVE, false);
            }
        }
    }

    public static void shortLightningBolt(LightningBolt bolt) {
        if (bolt.level().isClientSide()) return;
        if (!bolt.getPersistentData().getBoolean(TAG_ACTIVE)) return;
        if (bolt.tickCount >= 1) {
            bolt.discard();
        }
    }

    public static void cancelThundering(ThrownTrident trident) {
        if (trident.level().isClientSide()) return;
        if (trident.isInWall() && trident.getPersistentData().getBoolean(TAG_ACTIVE)) {
            trident.getPersistentData().putBoolean(TAG_ACTIVE, false);
        }
    }
} //no funca, no hay rayos