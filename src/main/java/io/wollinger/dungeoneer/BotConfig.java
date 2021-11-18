package io.wollinger.dungeoneer;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class BotConfig {
    private static String TOKEN;
    private static String ACTIVITY;

    private BotConfig() { }

    public static void init() {
        File configFile = new File("config.json");
        if(!configFile.exists()) {
            //Create config with user
        } else {
            try {
                String text = new String(Files.readAllBytes(configFile.toPath()), StandardCharsets.UTF_8);
                JSONObject json = new JSONObject(text);
                TOKEN = json.getString("TOKEN");
                ACTIVITY = json.getString("ACTIVITY");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
