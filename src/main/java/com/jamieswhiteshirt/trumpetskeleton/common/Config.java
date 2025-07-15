package com.jamieswhiteshirt.trumpetskeleton.common;

import com.jamieswhiteshirt.trumpetskeleton.TrumpetSkeleton;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.nio.file.Paths;

public class Config {
    public static boolean fast_doot = false;
    public static void loadConfigs (File eventFile) {
        File configFile = new File(String.valueOf(Paths.get("config")) + File.separator + TrumpetSkeleton.MODID + ".cfg");
        Configuration configuration = new Configuration(configFile);

        fast_doot = configuration.getBoolean(
            "fast_doot",
            Configuration.CATEGORY_GENERAL,
            fast_doot,
            "Skeletons doot on contact rather than just on timer like original mod."
        );

        if (configuration.hasChanged()) configuration.save();
    }
}
