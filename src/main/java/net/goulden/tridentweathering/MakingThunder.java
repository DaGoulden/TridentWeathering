/*package net.goulden.tridentweathering;

import net.minecraft.server.commands.SummonCommand;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityEvent;

import java.util.Timer;
import java.util.TimerTask;

@EventBusSubscriber(modid = TridentWeathering.MODID)
public class MakingThunder {

    @SubscribeEvent
    public static void makeThunder(EntityEvent.EntityConstructing event) {
        if (event.getEntity() instanceof ThrownTrident trident) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println(trident.getXRot());
                    SummonCommand.createEntity()
                }
            };
            timer.schedule(task, 500, 500);
        }
    }

}*/