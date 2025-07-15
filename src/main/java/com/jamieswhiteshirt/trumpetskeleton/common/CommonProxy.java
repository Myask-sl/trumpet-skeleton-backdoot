package com.jamieswhiteshirt.trumpetskeleton.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Config.loadConfigs(event.getSuggestedConfigurationFile());
    }
}
