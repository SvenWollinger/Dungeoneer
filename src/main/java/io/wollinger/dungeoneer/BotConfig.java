package io.wollinger.dungeoneer;

import io.wollinger.dungeoneer.utils.LogUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

public class BotConfig {
    private String token;
    private String activity;
    private int backupAmount;
    private static final String ID = "BOTCONFIG";

    public BotConfig() {
        LogUtils.log(ID, "Trying to load Bot Config...");
        File configFile = new File("config.json");
        if(!configFile.exists()) {
            LogUtils.log(ID, "Config not found! Lets get this setup together! :^)");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to dungeoneer!");
            System.out.println("It appears that you dont have a config.json file...");
            System.out.println("Would you like to set that up right now?");
            System.out.print("y/n: ");
            String input = scanner.nextLine();
            switch(input) {
                case "n":
                    System.out.println("Okay! Have a nice day!");
                    System.exit(0);
                case "y":
                    System.out.println("Perfect, lets go.");
                    break;
            }
            System.out.println("\nFirst, we need a discord bot token.\nYou can get that here: https://discord.com/developers/");
            System.out.print("Please enter your token: ");
            token = scanner.nextLine();
            System.out.println("\nGreat! Now, a discord bot can have an activity, for example 'playing DND'.");
            System.out.println("Please enter an activity to display there. (Enter 'none' if you dont want your bot to have an activity.");
            System.out.print("Playing ");
            activity = scanner.nextLine();
            System.out.println("\nNow, regarding backups, how many would you like to keep?");
            System.out.print("Amount: ");
            backupAmount = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nThats it! I will now save that to a file so that you dont have to do it next time.");
            JSONObject config = new JSONObject();
            config.put("token", token);
            config.put("activity", activity);
            config.put("backupAmount", backupAmount);
            try {
                Files.write(configFile.toPath(), config.toString().getBytes());
                System.out.println("Done! Press enter to continue.");
                System.in.read();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            LogUtils.log(ID, "Config file found, loading...");
            try {
                String text = new String(Files.readAllBytes(configFile.toPath()), StandardCharsets.UTF_8);
                JSONObject json = new JSONObject(text);
                token = json.getString("token");
                activity = json.getString("activity");
                backupAmount = json.getInt("backupAmount");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        LogUtils.log(ID, "Bot Config loaded!");
    }

    public String getToken() {
        return token;
    }

    public String getActivity() {
        return activity;
    }

    public int getBackupAmount() {
        return backupAmount;
    }

}
