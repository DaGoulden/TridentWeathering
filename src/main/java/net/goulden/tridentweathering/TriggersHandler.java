package net.goulden.tridentweathering;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.Timer;
import java.util.TimerTask;

public class TriggersHandler {

    public static int rainActualTriggers = 0; // not change
    public static int rainDelay = 3;
    public static int rainFakeTriggers = 2;

    public static void resetIfPlayerStop(Player player, int rainPastTriggers, int seconds) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (rainActualTriggers != rainPastTriggers + 1) {
                    rainActualTriggers = 0;
                    player.sendSystemMessage(Component.literal("reset"));
                }
            }
        }; timer.schedule(task, seconds * 1000L + 50);
    }

    public static int thunderActualTriggers = 0;
    public static int thunderDelay = 2;
    public static int thunderNeedTriggers = 3;

}