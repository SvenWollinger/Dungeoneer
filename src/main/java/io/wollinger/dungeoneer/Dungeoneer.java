package io.wollinger.dungeoneer;

import io.wollinger.dungeoneer.server.Character;
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
    private final HashMap<String, Server> servers = new HashMap<>(); //ID, Server -> Keeps track of all servers by server id
    private final HashMap<String, Character> characters = new HashMap<>(); //ID, Character -> Keeps track of all characters by owner id

    public Dungeoneer() {
        LogUtils.init();
        setupJDA(new BotConfig());
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

    public JDA getJDA() {
        return jda;
    }

}
