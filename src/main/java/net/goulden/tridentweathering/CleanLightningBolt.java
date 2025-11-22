package net.goulden.tridentweathering;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;

public class CleanLightningBolt extends LightningBolt {
    public CleanLightningBolt(EntityType<? extends LightningBolt> type, Level world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount > 1) {
            this.discard();
        }
    }
}