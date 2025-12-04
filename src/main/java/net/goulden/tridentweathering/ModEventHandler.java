package net.goulden.tridentweathering;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;

@EventBusSubscriber(modid = TridentWeathering.MODID)
public class ModEventHandler {

    @SubscribeEvent
    public static void configLoading(ModConfigEvent.Loading event) {
        refreshConfig();
    }

    @SubscribeEvent
    public static void configReloading(ModConfigEvent.Reloading event) {
        refreshConfig();
    }

    private static void refreshConfig() {
        MakingClear.delay = ModConfig.SERVER.clearTriggerDelay.get();
        MakingRain.wait = ModConfig.SERVER.rainWaitTriggers.get();
        MakingRain.fakes = ModConfig.SERVER.rainFakeTriggers.get();
        MakingThunder.wait = ModConfig.SERVER.thunderWaitTriggers.get();
        MakingThunder.bolts = ModConfig.SERVER.thunderBoltsNeeded.get();
        MakingThunder.probability = ModConfig.SERVER.thunderBoltProbability.get();
    }
}
