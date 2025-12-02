package net.goulden.tridentweathering;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(TridentWeathering.MODID)
public class TridentWeathering {

    public static final String MODID = "tridentweathering";

    public TridentWeathering(ModContainer modContainer) {

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, TridentWeatheringConfig.SERVER_SPEC);

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        MakingClear.delay = TridentWeatheringConfig.clearTriggerDelay.get();
        MakingRain.wait = TridentWeatheringConfig.rainWaitTriggers.get();
        MakingRain.fakes = TridentWeatheringConfig.rainFakeTriggers.get();
        MakingThunder.wait = TridentWeatheringConfig.thunderWaitTriggers.get();
        MakingThunder.bolts = TridentWeatheringConfig.thunderBoltsNeeded.get();
        MakingThunder.probability = TridentWeatheringConfig.thunderBoltProbability.get();
    }
}