package io.wollinger.dungeoneer.command;

import io.wollinger.dungeoneer.Dungeoneer;
import io.wollinger.dungeoneer.command.cmd.CMDArgTest;
import io.wollinger.dungeoneer.command.cmd.CMDHelp;
import io.wollinger.dungeoneer.command.cmd.CMDPing;
import io.wollinger.dungeoneer.utils.StringUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CommandManager  extends ListenerAdapter {
    private Dungeoneer dungeoneer;
    private HashMap<String, Command> commands = new HashMap<>();

    public CommandManager(Dungeoneer dungeoneer) {
        this.dungeoneer = dungeoneer;
        addCommand(new CMDHelp(dungeoneer, this));
        addCommand(new CMDPing());
        addCommand(new CMDArgTest());
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
        arguments.remove(0);
        if(cmd.startsWith(dungeoneer.getServer(serverID).getConfig().getCommandPrefix())) { //
            cmd = cmd.replaceFirst("!", "");
            if(commands.containsKey(cmd)) {
                commands.get(cmd).run(serverID, event, arguments);
            } else {
                //TODO: Command not found
            }
        }
    }

    public ArrayList<Command> getAllCommandsAsList() {
        Set<String> keys = commands.keySet();
        ArrayList<Command> array = new ArrayList<>();
        for(String key : keys) {
            array.add(commands.get(key));
        }
        return array;
    }

    public void addCommand(Command cmd) {
        commands.put(cmd.getCMD(), cmd);
    }
}
