package io.wollinger.dungeoneer.server;

import io.wollinger.dungeoneer.Dungeoneer;
import io.wollinger.dungeoneer.utils.FileUtils;
import io.wollinger.dungeoneer.utils.LogUtils;
import io.wollinger.dungeoneer.utils.StringUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private String id;
    private final ArrayList<Group> groups = new ArrayList<>();
    private final HashMap<String, Character> characters = new HashMap<>(); //ID, Character -> Keeps track of all characters by owner id
    private ServerConfig config;

    private final Dungeoneer dungeoneer;

    public Server(String id, Dungeoneer dungeoneer) {
        this.id = id;
        this.dungeoneer = dungeoneer;
        LogUtils.log(id, "Server init");
        if (new File(id + ".db").exists()) {
            LogUtils.log(id, "Database file found! Loading...");
            loadFromDatabase();
        } else {
            LogUtils.log(id, "No database file found, loading defaults...");
            config = new ServerConfig();
        }
        LogUtils.log(id, "Done loading server!");
    }

    public void saveToDatabase() {
        FileUtils.backupIncrement(id, dungeoneer.getBotConfig().getBackupAmount());

        try {
            Connection connection = getDatabaseConnection();
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS characters (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255), avatar_url VARCHAR(255), description VARCHAR(255), creator_id VARCHAR(255) NOT NULL);");
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS groups (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255) NOT NULL, webhook_url VARCHAR(255));");
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS group_members (user_id VARCHAR(255) NOT NULL, role VARCHAR(255));");
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void loadFromDatabase() {
        //TODO: add loading
    }

    public Connection getDatabaseConnection() {
        try {
            return DriverManager.getConnection(StringUtils.format("jdbc:sqlite:%c.db", id));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public ServerConfig getConfig() {
        return config;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addCharacter(Character character) {
        characters.put(character.getCreatorID(), character);
    }
}
