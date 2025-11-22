package net.goulden.tridentweathering;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.neoforged.bus.api.SubscribeEvent;

@EventBusSubscriber(modid = TridentWeathering.MODID)
public class TridentWeatheringClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.CLEAN_LIGHTNING_BOLT.get(),
                LightningBoltRenderer::new
        );
    }
}