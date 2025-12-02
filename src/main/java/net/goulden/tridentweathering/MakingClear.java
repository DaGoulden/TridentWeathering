package net.goulden.tridentweathering;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;

public class MakingClear {

    protected static int delay;

    public static void makeClear(ThrownTrident trident) {

        if (trident.level().isClientSide) return;
        if (trident.tickCount >= 10 && trident.tickCount % delay != 0) return;
        if (!(trident.level().getBlockEntity(trident.getOnPos()) instanceof ConduitBlockEntity conduit)) return;
        if (!(conduit.isActive())) return;

        trident.lerpMotion(0, 0, 0);
        ServerLevel serverLevel = ((ServerLevel) trident.level());
        serverLevel.setWeatherParameters(ServerLevel.RAIN_DELAY.sample(serverLevel.getRandom()), 0, false, false);
    }
}