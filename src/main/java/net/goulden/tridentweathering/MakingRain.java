package net.goulden.tridentweathering;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import static net.goulden.tridentweathering.TriggersHandler.*;

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
                resetIfPlayerStop(player, rainActualTriggers, 1);
                player.sendSystemMessage(Component.literal("delay"));
            } else if (rainActualTriggers < (rainDelay + rainFakeTriggers)) {
                rainActualTriggers++;
                resetIfPlayerStop(player, rainActualTriggers, 2);
                player.level().setRainLevel(0.5F);
                player.sendSystemMessage(Component.literal("fake"));
            } else {
                serverLevel.setWeatherParameters(0, ServerLevel.RAIN_DURATION.sample(serverLevel.getRandom()) , true, false);
                player.sendSystemMessage(Component.literal("Set the weather to rain"));
            }
        }
    }
}