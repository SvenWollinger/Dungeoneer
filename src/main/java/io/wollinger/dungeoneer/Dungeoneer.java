package io.wollinger.dungeoneer;

import io.wollinger.dungeoneer.server.Server;
import io.wollinger.dungeoneer.utils.LogUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.HashMap;

public class Dungeoneer {
    private static final String VERSION = "0.0.1";
    private JDA jda;
    private BotConfig botConfig;
    private final HashMap<String, Server> servers = new HashMap<>(); //ID, Server -> Keeps track of all servers by server id

    public Dungeoneer() {
        LogUtils.init();
        botConfig = new BotConfig();
        setupJDA(botConfig);
        jda.addEventListener(new CommandManager(this));
        LogUtils.log("Dungeoneer %c started!", VERSION);
    }

    private void setupJDA(BotConfig config) {
        JDABuilder builder = JDABuilder.create(config.getToken(), GatewayIntent.GUILD_EMOJIS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_PRESENCES);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.playing(config.getActivity()));
        try {
            jda = builder.build();
            jda.awaitReady();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    public void addServer(String id) {
        servers.put(id, new Server(id, this));
    }

    public JDA getJDA() {
        return jda;
    }

    public Server getServer(String serverID) {
        return servers.get(serverID);
    }

    public BotConfig getBotConfig() {
        return botConfig;
    }
}
