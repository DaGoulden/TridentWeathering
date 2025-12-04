package net.goulden.tridentweathering;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod(TridentWeathering.MODID)
public class TridentWeathering {

    public static final String MODID = "tridentweathering";

    public TridentWeathering() {

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, TridentWeatheringConfig.SPEC);

    }

    @SubscribeEvent
    public void onServerStarted(ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == TridentWeatheringConfig.SPEC) {
            MakingClear.delay = TridentWeatheringConfig.clearTriggerDelay.get();
            MakingRain.wait = TridentWeatheringConfig.rainWaitTriggers.get();
            MakingRain.fakes = TridentWeatheringConfig.rainFakeTriggers.get();
            MakingThunder.wait = TridentWeatheringConfig.thunderWaitTriggers.get();
            MakingThunder.bolts = TridentWeatheringConfig.thunderBoltsNeeded.get();
            MakingThunder.probability = TridentWeatheringConfig.thunderBoltProbability.get();
        }
    }
}