package com.jamieswhiteshirt.trumpetskeleton.common;

import com.jamieswhiteshirt.trumpetskeleton.TrumpetSkeleton;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.nio.file.Paths;

public class Config {
    public static int TRUMPET_SKELETON_ENTITY_ID = 254;
    public static boolean fast_doot = false;
    public static void loadConfigs (File eventFile) {
        File configFile = new File(Paths.get("config") + File.separator + TrumpetSkeleton.MODID + ".cfg");
        Configuration configuration = new Configuration(configFile);

        fast_doot = configuration.getBoolean(
            "fast_doot",
            Configuration.CATEGORY_GENERAL,
            fast_doot,
            "Skeletons doot on contact rather than just on timer like original mod."
        );

        TRUMPET_SKELETON_ENTITY_ID = configuration.getInt("TRUMPET_SKELETON_ENTITY_ID",
            Configuration.CATEGORY_GENERAL,
            TRUMPET_SKELETON_ENTITY_ID,
            1,
            Integer.MAX_VALUE,
            "Entity ID: Trumpet Skeleton [over 255 requires EndlessIDs or similar. NEIDs won't do]");

        if (configuration.hasChanged()) configuration.save();
    }
}
