package net.goulden.tridentweathering;

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
            final boolean[] tridentGoesUp = {false};
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (TriggerValues.thunderTriggers <= 0 && trident.getXRot() < -89) {
                        tridentGoesUp[0] = true;
                        TriggerValues.thunderTriggers++;
                    } else if (TriggerValues.thunderTriggers <= 3 && tridentGoesUp[0]) {
                        System.out.println("rayo");
                        TriggerValues.thunderTriggers++;
                    } else if (TriggerValues.thunderTriggers > 3){
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task, 1, 500);
        }
    }

}