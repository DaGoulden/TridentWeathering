package net.goulden.tridentweathering;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(TridentWeathering.MODID)
public class TridentWeathering {

    public static final String MODID = "tridentweathering";

    public TridentWeathering(ModContainer modContainer) {

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.SERVER, TridentWeatheringConfig.SERVER_SPEC);

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        MakingClear.delay = TridentWeatheringConfig.SERVER.clearTriggerDelay.get();
        MakingRain.wait = TridentWeatheringConfig.SERVER.rainWaitTriggers.get();
        MakingRain.fakes = TridentWeatheringConfig.SERVER.rainFakeTriggers.get();
        MakingThunder.wait = TridentWeatheringConfig.SERVER.thunderWaitTriggers.get();
        MakingThunder.bolts = TridentWeatheringConfig.SERVER.thunderBoltsNeeded.get();
        MakingThunder.probability = TridentWeatheringConfig.SERVER.thunderBoltProbability.get();
    }
}