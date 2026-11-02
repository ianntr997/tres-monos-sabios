package com.tresmonos;

import net.fabricmc.api.ModInitializer;

public class TresMonosSabios implements ModInitializer {
    public static final String MOD_ID = "tresmonos";

    @Override
    public void onInitialize() {
        ModItems.register();
    }
}
