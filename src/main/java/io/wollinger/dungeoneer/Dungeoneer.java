package io.wollinger.dungeoneer;

import io.wollinger.dungeoneer.utils.LogUtils;

public class Dungeoneer {
    private static final String VERSION = "0.0.1";

    public Dungeoneer() {
        LogUtils.init();
        BotConfig.init();
        LogUtils.log("Dungeoneer %c started. Hello world!", VERSION);
    }

}
