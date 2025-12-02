package net.goulden.tridentweathering;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.entity.projectile.ThrownTrident;

import static net.goulden.tridentweathering.MakingClear.makeClear;
import static net.goulden.tridentweathering.MakingThunder.*;

@Mod.EventBusSubscriber(modid = TridentWeathering.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TridentWeatheringHandler {
    @SubscribeEvent
    public void getEventEntity(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            for (ServerLevel level : event.getServer().getAllLevels()) {
                for (Entity entity : level.getAllEntities()) {
                    if (entity instanceof ThrownTrident) {
                        makeClear((ThrownTrident) entity);
                        makeThunder((ThrownTrident) entity);
                        cancelThundering((ThrownTrident) entity);
                    } else if (entity instanceof LightningBolt) {
                        shortLightningBolt((LightningBolt) entity);
                    }
                }
            }
        }
    }
}