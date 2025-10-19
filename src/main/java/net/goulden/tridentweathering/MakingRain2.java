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
public class MakingRain2 {

    @SubscribeEvent
    public static void makeRain(LivingEntityUseItemEvent.Tick event) {
        if (event.getItem().getItem() == Items.TRIDENT &&
            event.getEntity() instanceof Player player &&
            player.getXRot() <= -75 &&
            player.level().getGameTime() % 20 == 0 &&
            player.level() instanceof ServerLevel serverLevel &&
            !serverLevel.isRaining()) {
            if (firstTime) {
                firstTime = false;
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        firstTime = true;
                        zero = 0;
                    }
                };
                timer.schedule(task, ((rainDelay + rainFakeTriggers * 2L) * 1000) + 500);
            }
            if (zero < rainDelay) {
                zero++;
            } else if (zero < (rainDelay + rainFakeTriggers)) {
                zero++;
                player.level().setRainLevel(0.5F);
            } else {
                serverLevel.setWeatherParameters(0, ServerLevel.RAIN_DURATION.sample(serverLevel.getRandom()) , true, false);
                player.sendSystemMessage(Component.literal("Set the weather to rain"));
            }
        }
    }

}