package net.goulden.tridentweathering;

import net.neoforged.fml.ModContainer;

import net.neoforged.fml.common.Mod;

@Mod(TridentWeathering.MODID)
public class TridentWeathering {

    public static final String MODID = "tridentweathering";

    public TridentWeathering(ModContainer modContainer) {

        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.SERVER, ModConfig.SERVER_SPEC);

    }
}