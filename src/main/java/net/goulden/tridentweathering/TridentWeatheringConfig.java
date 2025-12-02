package net.goulden.tridentweathering;

import net.minecraftforge.common.ForgeConfigSpec;

public class TridentWeatheringConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    static final ForgeConfigSpec SERVER_SPEC = BUILDER.build();

    protected static final ForgeConfigSpec.IntValue clearTriggerDelay = BUILDER
            .comment("Delay (in ticks) between triggers to clear the weather when the trident is in the conduit.")
            .defineInRange("clearTriggerDelay", 50, 1, 10000);

    protected static final ForgeConfigSpec.IntValue rainWaitTriggers = BUILDER
            .comment("Wait (in seconds) before starting with the fake triggers.")
            .defineInRange("rainWaitTriggers", 1, 0, Integer.MAX_VALUE);

    protected static final ForgeConfigSpec.IntValue rainFakeTriggers = BUILDER
            .comment("Amount of fake triggers before making it rain.")
            .defineInRange("rainFakeTriggers", 2, 0, Integer.MAX_VALUE);

    protected static final ForgeConfigSpec.IntValue thunderWaitTriggers = BUILDER
            .comment("Wait (in seconds) before starting with the lightning bolts triggers.")
            .defineInRange("thunderWaitTriggers", 2, 0, Integer.MAX_VALUE);

    protected static final ForgeConfigSpec.IntValue thunderBoltsNeeded = BUILDER
            .comment("Amount of lightning bolts that can strike and that are needed for making it thunder.")
            .defineInRange("thunderBoltsNeeded", 3, 0, Integer.MAX_VALUE);

    protected static final ForgeConfigSpec.DoubleValue thunderBoltProbability = BUILDER
            .comment("Probability of striking a lightning bolt.")
            .defineInRange("thunderBoltProbability", 0.85, 0, 1);

}