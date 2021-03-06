package io.wollinger.dungeoneer.command;

import io.wollinger.dungeoneer.Dungeoneer;
import io.wollinger.dungeoneer.command.cmd.CMDArgTest;
import io.wollinger.dungeoneer.command.cmd.CMDHelp;
import io.wollinger.dungeoneer.command.cmd.CMDPing;
import io.wollinger.dungeoneer.utils.StringUtils;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CommandManager  extends ListenerAdapter {
    private final Dungeoneer dungeoneer;
    private final HashMap<String, Command> commands = new HashMap<>();

    public CommandManager(Dungeoneer dungeoneer) {
        this.dungeoneer = dungeoneer;
        addCommand(new CMDHelp(dungeoneer, this));
        addCommand(new CMDPing());
        addCommand(new CMDArgTest());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getChannelType() == ChannelType.TEXT) {
            ArrayList<String> rawParts = StringUtils.splitIntoArray(event.getMessage().getContentRaw());
            ArrayList<CommandArgument> arguments = new ArrayList<>();
            for (String part : rawParts) {
                arguments.add(new CommandArgument(part));
            }
            String cmd = arguments.get(0).getContent();
            String serverID = event.getGuild().getId();
            String cmdPrefix = dungeoneer.getServer(serverID).getConfig().getCommandPrefix();
            arguments.remove(0);
            if (cmd.startsWith(cmdPrefix)) { //
                cmd = cmd.replaceFirst(cmdPrefix, "");
                if (commands.containsKey(cmd)) {
                    CommandResult result = commands.get(cmd).run(serverID, event, arguments);
                    String reaction = "❓";
                    switch(result) {
                        case SUCCESS: reaction = "✅"; break;
                        case FAIL: reaction = "❌"; break;
                        case NO_PERMISSION: reaction = "\uD83D\uDCD3"; break; //TODO: Use other emoji for this
                    }
                    event.getMessage().addReaction(reaction).queue();
                }
            }
        } else if(event.getChannelType() == ChannelType.PRIVATE) {
            //TODO: Commands in private?
        }
    }

    public ArrayList<Command> getAllCommandsAsList() {
        return new ArrayList<>(commands.values());
    }

    public void addCommand(Command cmd) {
        commands.put(cmd.getCMD(), cmd);
    }
}
