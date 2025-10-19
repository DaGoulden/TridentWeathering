package net.goulden.tridentweathering;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.Timer;
import java.util.TimerTask;

import static net.goulden.tridentweathering.TriggerValues.*;

@EventBusSubscriber(modid = TridentWeathering.MODID)
public class MakingRain {

    @SubscribeEvent
    public static void makeRain(LivingEntityUseItemEvent.Tick event) {
        if (event.getItem().getItem() == Items.TRIDENT &&
            event.getEntity() instanceof Player player &&
            player.getXRot() <= -75 &&
            player.level().getGameTime() % 20 == 0 &&
            player.level() instanceof ServerLevel serverLevel &&
            !serverLevel.isRaining()) {

            // Values resetter
            if (firstTime) {
                firstTime = false;
                Timer timer = new Timer();
                TimerTask delayTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (actualTriggers <= rainDelay) {
                            actualTriggers = 0;
                            firstTime = true;
                            player.sendSystemMessage(Component.literal("reset delay"));
                        } else {
                            player.sendSystemMessage(Component.literal("no reset delay"));
                            TimerTask fakeTriggersTask = new TimerTask() {
                                @Override
                                public void run() {
                                    if (actualTriggers <= rainDelay + rainFakeTriggers) {
                                        actualTriggers = 0;
                                        firstTime = true;
                                        player.sendSystemMessage(Component.literal("reset fakes"));
                                    } else {
                                        player.sendSystemMessage(Component.literal("no reset fakes"));
                                    }
                                }
                            }; timer.schedule(fakeTriggersTask, rainFakeTriggers * 2000L + 1100);
                        }
                    }
                }; timer.schedule(delayTask, rainDelay * 1000L + 100);
            }

            // The function
            if (actualTriggers < rainDelay) {
                actualTriggers++;
                player.sendSystemMessage(Component.literal("delay"));
            } else if (actualTriggers < (rainDelay + rainFakeTriggers)) {
                actualTriggers++;
                player.level().setRainLevel(0.5F);
                player.sendSystemMessage(Component.literal("fake"));
            } else {
                actualTriggers = 0;
                serverLevel.setWeatherParameters(0, ServerLevel.RAIN_DURATION.sample(serverLevel.getRandom()) , true, false);
                player.sendSystemMessage(Component.literal("Set the weather to rain"));
            }

        }
    }

}