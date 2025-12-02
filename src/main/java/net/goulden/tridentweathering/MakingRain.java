package net.goulden.tridentweathering;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

@EventBusSubscriber(modid = TridentWeathering.MODID)
public class MakingRain {

    protected static int wait;
    protected static int fakes;

    @SubscribeEvent
    public static void makeRain(LivingEntityUseItemEvent.Tick event) {

        if (!(event.getItem().getItem() == Items.TRIDENT)) return;
        Entity entity = event.getEntity();
        if (entity.level().isClientSide) return;
        if (!(event.getDuration() % 20 == 0)) return;
        if (!(entity.getXRot() <= -80)) return;
        if (entity.level().isRaining()) return;
        BlockPos pos = entity.blockPosition();
        if (entity.level().getBiome(pos).value().getPrecipitationAt(pos) == Biome.Precipitation.NONE) return;

        int triggers = (72000 - event.getDuration()) / 20;
        if (triggers > wait && triggers <= wait + fakes) {
            entity.level().setRainLevel(0.39F);
        } else if (triggers > wait + fakes) {
            ServerLevel serverLevel = ((ServerLevel) entity.level());
            serverLevel.setWeatherParameters(0, ServerLevel.RAIN_DURATION.sample(serverLevel.getRandom()) , true, false);
        }
    }
}