/*package net.goulden.tridentweathering;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import java.util.Timer;
import java.util.TimerTask;

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

            if (rainActualTriggers < rainDelay) {
                rainActualTriggers++;
                rainResetIfStopped(player, rainActualTriggers, 1);
                //player.sendSystemMessage(Component.literal("delay"));
            } else if (rainActualTriggers < (rainDelay + rainFakeTriggers)) {
                rainActualTriggers++;
                rainResetIfStopped(player, rainActualTriggers, 2);
                player.level().setRainLevel(0.5F);
                //player.sendSystemMessage(Component.literal("fake"));
            } else {
                serverLevel.setWeatherParameters(0, ServerLevel.RAIN_DURATION.sample(serverLevel.getRandom()) , true, false);
                player.sendSystemMessage(Component.literal("Set the weather to rain"));
            }
        }
    }

    private static int rainActualTriggers = 0; // not change
    private static int rainDelay = 2;
    private static int rainFakeTriggers = 2;
    private static void rainResetIfStopped(Player player, int rainPastTriggers, int seconds) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (rainActualTriggers != rainPastTriggers + 1) {
                    rainActualTriggers = 0;
                    //player.sendSystemMessage(Component.literal("reset"));
                }
            }
        }; timer.schedule(task, seconds * 1000L + 50);
    }
}*/