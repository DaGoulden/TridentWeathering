package net.goulden.tridentweathering.event;

import net.goulden.tridentweathering.TridentWeathering;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = TridentWeathering.MODID)
public class MakingClear {

    public static int delay;

    @SubscribeEvent
    public static void makeClear(EntityTickEvent.Post event) {

        if (!(event.getEntity() instanceof ThrownTrident trident)) return;
        if (trident.level().isClientSide()) return;
        if (trident.tickCount >= 10 && trident.tickCount % delay != 0) return;
        if (!(trident.level().getBlockEntity(trident.getOnPos()) instanceof ConduitBlockEntity conduit)) return;
        if (!(conduit.isActive())) return;

        trident.lerpMotion(Vec3.ZERO);
        ServerLevel serverLevel = ((ServerLevel) trident.level());
        serverLevel.setWeatherParameters(ServerLevel.RAIN_DELAY.sample(serverLevel.getRandom()), 0, false, false);
    }
}