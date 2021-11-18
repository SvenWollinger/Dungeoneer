package io.wollinger.dungeoneer;

import io.wollinger.dungeoneer.utils.LogUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Dungeoneer {
    private static final String VERSION = "0.0.1";
    private JDA jda;

    public Dungeoneer() {
        LogUtils.init();
        BotConfig.init();
        LogUtils.log("Dungeoneer %c starting...", VERSION);
        setupJDA();
    }

    private void setupJDA() {
        JDABuilder builder = JDABuilder.create(BotConfig.getToken(), GatewayIntent.GUILD_EMOJIS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_PRESENCES);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.playing(BotConfig.getActivity()));
        try {
            jda = builder.build();
            jda.awaitReady();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

}
