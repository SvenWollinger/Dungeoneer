package io.wollinger.dungeoneer;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;

public class JoinLeaveListener extends ListenerAdapter {
    private Dungeoneer dungeoneer;

    public JoinLeaveListener(Dungeoneer dungeoneer) {
        this.dungeoneer = dungeoneer;
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        dungeoneer.addServer(event.getGuild().getId());
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event) {
        new File(event.getGuild().getId() + ".db").delete();
        dungeoneer.removeServer(event.getGuild().getId());
    }
}
