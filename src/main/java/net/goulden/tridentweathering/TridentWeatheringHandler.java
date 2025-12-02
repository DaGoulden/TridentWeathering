package net.goulden.tridentweathering;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.entity.projectile.ThrownTrident;

@Mod.EventBusSubscriber(modid = TridentWeathering.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TridentWeatheringHandler {
    @SubscribeEvent
    public Entity getEventEntity(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            for (ServerLevel level : event.getServer().getAllLevels()) {
                for (Entity entity : level.getAllEntities()) {
                    switch (entity) {
                        case ThrownTrident trident ->
                    }
                }
            }
        }
    }
}
