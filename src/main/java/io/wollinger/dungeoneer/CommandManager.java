package io.wollinger.dungeoneer;

import io.wollinger.dungeoneer.command.CMDPing;
import io.wollinger.dungeoneer.command.Command;
import io.wollinger.dungeoneer.command.CommandArgument;
import io.wollinger.dungeoneer.utils.StringUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandManager  extends ListenerAdapter {
    private Dungeoneer dungeoneer;
    private HashMap<String, Command> commands = new HashMap<>();

    public CommandManager(Dungeoneer dungeoneer) {
        this.dungeoneer = dungeoneer;
        CMDPing ping = new CMDPing();
        commands.put(ping.getCMD(), ping);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        ArrayList<String> rawParts = StringUtils.splitIntoArray(event.getMessage().getContentRaw());
        CommandArgument[] arguments = new CommandArgument[rawParts.size()];
        for(int i = 0; i < rawParts.size(); i++) {
            arguments[i] = new CommandArgument(rawParts.get(i));
        }
    }
}
