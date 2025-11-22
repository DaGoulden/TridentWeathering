package net.goulden.tridentweathering;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = TridentWeathering.MODID)
public class MakingThunder {

    private static final String TAG_GOING_UP = "TridentWeathering.GoingUp";
    private static final String TAG_ACTUAL_TRIGGERS = "TridentWeathering.ActualTriggers";
    private static final String TAG_THUNDERBOLTS_TRIGGERED = "TridentWeathering.ThunderboltsTriggered";
    private static final String TAG_CANCEL_WEATHERING = "TridentWeathering.Cancel";
    private static final String TAG_GENERATED_BOLT = "TridentWeathering.GeneratedBolt";

    @SubscribeEvent
    public static void mainThundering(EntityTickEvent.Pre event) {

        if (!(event.getEntity() instanceof ThrownTrident trident)) return;
        if (trident.level().isClientSide()) return;
        if (!(trident.tickCount % 10 == 0)) return;

        CompoundTag data = trident.getPersistentData();
        if (trident.getXRot() > 85 && trident.tickCount == 10) {
            data.putBoolean(TAG_GOING_UP, true);
            data.putInt(TAG_ACTUAL_TRIGGERS, 0);
            data.putInt(TAG_THUNDERBOLTS_TRIGGERED, 0);
        }

        if (data.getBoolean(TAG_GOING_UP) && !data.getBoolean(TAG_CANCEL_WEATHERING)) {
            if (data.getInt(TAG_ACTUAL_TRIGGERS) < 2) {
                data.putInt(TAG_ACTUAL_TRIGGERS, data.getInt(TAG_ACTUAL_TRIGGERS) + 1);
            } else if (data.getInt(TAG_ACTUAL_TRIGGERS) < 5) {
                data.putInt(TAG_ACTUAL_TRIGGERS, data.getInt(TAG_ACTUAL_TRIGGERS) + 1);
                LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(trident.level());
                if (bolt != null) {
                    bolt.setPos(trident.position());
                    bolt.setVisualOnly(true);
                    bolt.getPersistentData().putBoolean(TAG_GENERATED_BOLT, true);
                    trident.level().addFreshEntity(bolt);
                }
            } else if (data.getInt(TAG_ACTUAL_TRIGGERS) == 5) {
                if (!(trident.level() instanceof ServerLevel serverLevel)) return;
                serverLevel.setWeatherParameters(0, ServerLevel.THUNDER_DURATION.sample(serverLevel.getRandom()), true, true);
                data.putBoolean(TAG_GOING_UP, false);
            }
        }

    }

    @SubscribeEvent
    public static void shortLightningBolt(EntityTickEvent.Pre event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof LightningBolt bolt)) return;
        if (!bolt.getPersistentData().getBoolean(TAG_GENERATED_BOLT)) return;
        if (bolt.tickCount >= 1) {
            bolt.discard();
        }
    }

    @SubscribeEvent
    private static void cancelThundering(ProjectileImpactEvent event) {
        if (!(event.getProjectile() instanceof ThrownTrident trident)) return;
        CompoundTag data = trident.getPersistentData();
        data.putBoolean(TAG_CANCEL_WEATHERING, true);
    }

}
// hacer el random para el actual trigger
// que sea con channeling
// solo cuando esta lloviendo