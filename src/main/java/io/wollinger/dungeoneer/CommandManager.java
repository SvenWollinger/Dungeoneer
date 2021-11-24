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
        addCommand(new CMDPing());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        ArrayList<String> rawParts = StringUtils.splitIntoArray(event.getMessage().getContentRaw());
        ArrayList<CommandArgument> arguments = new ArrayList<>();
        for(String part : rawParts) {
            arguments.add(new CommandArgument(part));
        }
        String cmd = arguments.get(0).getContent();
        String serverID = event.getGuild().getId();
        if(cmd.startsWith(dungeoneer.getServer(serverID).getConfig().getCommandPrefix())) { //
            cmd = cmd.replaceFirst("!", "");
            if(commands.containsKey(cmd)) {
                commands.get(cmd).run(serverID, event, arguments);
            } else {
                //TODO: Command not found
            }
        }
    }

    public void addCommand(Command cmd) {
        commands.put(cmd.getCMD(), cmd);
    }
}
