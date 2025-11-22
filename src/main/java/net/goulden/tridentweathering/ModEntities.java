package net.goulden.tridentweathering;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TridentWeathering.MODID);

    public static final Supplier<EntityType<CleanLightningBolt>> CLEAN_LIGHTNING_BOLT =
            ENTITY_TYPES.register("clean_lightning_bolt", () ->
                    EntityType.Builder.<CleanLightningBolt>of(CleanLightningBolt::new, MobCategory.MISC)
                            .noSave()
                            .noSummon()
                            .sized(0.0F, 0.0F)
                            .clientTrackingRange(512)
                            .updateInterval(1)
                            .build("clean_lightning_bolt")
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}