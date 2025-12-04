package net.goulden.tridentweathering;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = TridentWeathering.MODID)
public class TridentWeatheringConfig {

    public static final ModConfigSpec SERVER_SPEC;
    public static final Server SERVER;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        SERVER = new Server(builder);
        SERVER_SPEC = builder.build();
    }

    public static class Server {

        private final ModConfigSpec.IntValue clearTriggerDelay;
        private final ModConfigSpec.IntValue rainWaitTriggers;
        private final ModConfigSpec.IntValue rainFakeTriggers;
        private final ModConfigSpec.IntValue thunderWaitTriggers;
        private final ModConfigSpec.IntValue thunderBoltsNeeded;
        private final ModConfigSpec.DoubleValue thunderBoltProbability;

        Server(ModConfigSpec.Builder builder) {

            clearTriggerDelay = builder
                    .comment("Delay (in ticks) between triggers to clear the weather when the trident is in the conduit.")
                    .defineInRange("clearTriggerDelay", 50, 1, 10000);

            rainWaitTriggers = builder
                    .comment("Wait (in seconds) before starting with the fake triggers.")
                    .defineInRange("rainWaitTriggers", 1, 0, Integer.MAX_VALUE);

            rainFakeTriggers = builder
                    .comment("Amount of fake triggers before making it rain.")
                    .defineInRange("rainFakeTriggers", 2, 0, Integer.MAX_VALUE);

            thunderWaitTriggers = builder
                    .comment("Wait (in seconds) before starting with the lightning bolts triggers.")
                    .defineInRange("thunderWaitTriggers", 2, 0, Integer.MAX_VALUE);

            thunderBoltsNeeded = builder
                    .comment("Amount of lightning bolts that can strike and that are needed for making it thunder.")
                    .defineInRange("thunderBoltsNeeded", 3, 0, Integer.MAX_VALUE);

            thunderBoltProbability = builder
                    .comment("Probability of striking a lightning bolt.")
                    .defineInRange("thunderBoltProbability", 0.85, 0, 1);
        }
    }

    @SubscribeEvent
    public static void configLoading(ModConfigEvent.Loading event) {
        refreshConfig();
    }
    @SubscribeEvent
    public static void configReloading(ModConfigEvent.Reloading event) {
        refreshConfig();
    }

    private static void refreshConfig() {
        MakingClear.delay = SERVER.clearTriggerDelay.get();
        MakingRain.wait = SERVER.rainWaitTriggers.get();
        MakingRain.fakes = SERVER.rainFakeTriggers.get();
        MakingThunder.wait = SERVER.thunderWaitTriggers.get();
        MakingThunder.bolts = SERVER.thunderBoltsNeeded.get();
        MakingThunder.probability = SERVER.thunderBoltProbability.get();
    }
}