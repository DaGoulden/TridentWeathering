package net.goulden.tridentweathering;

import net.minecraft.world.entity.projectile.ThrownTrident;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityEvent;

@EventBusSubscriber(modid = TridentWeathering.MODID)
public class MakingThunder {

    @SubscribeEvent
    public static void makeThunder(EntityEvent.EntityConstructing event) {
        if (event.getEntity() instanceof ThrownTrident trident) {
            System.out.println(trident.getKnownMovement());
        }
    }

}