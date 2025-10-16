package net.goulden.tridentweathering;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@EventBusSubscriber(modid = TridentWeathering.MODID)
public class MakingRain {

    @SubscribeEvent
    public static void makeRain(LivingEntityUseItemEvent.Tick event) {

        if (event.getItem().getItem() == Items.TRIDENT) {

            Entity eventEntity = event.getEntity();
            if (eventEntity instanceof Player &&
                eventEntity.getXRot() <= -75 &&
                eventEntity.level().getGameTime() % 20 == 0 &&
                eventEntity.level() instanceof ServerLevel serverLevel &&
                !serverLevel.isRaining()) {

                Random random = new Random();
                boolean trigger = random.nextBoolean();
                if (trigger && TridentWeatheringHandler.rainTriggers > 1) {

                    final boolean[] deactivate = {false};
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {

                        @Override
                        public void run() {
                            if (!deactivate[0]) {
                                eventEntity.level().setRainLevel(0.5F);
                                TridentWeatheringHandler.rainTriggers--;
                                deactivate[0] = true;
                            } else {
                                TridentWeatheringHandler.rainTriggers = 3;
                                timer.cancel();
                            }
                        }

                    };
                    timer.schedule(task, 0, 15000);

                } else if (trigger && TridentWeatheringHandler.rainTriggers == 1) {

                    serverLevel.setWeatherParameters(0, ServerLevel.RAIN_DURATION.sample(serverLevel.getRandom()) , true, false);
                    eventEntity.sendSystemMessage(Component.literal("Set the weather to rain"));

                }
            }
        }
    }
}// hacer config para cantidad de fake triggers y el tiempo para reiniciarlos