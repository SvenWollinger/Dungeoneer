package io.wollinger.dungeoneer.command.cmd;

import io.wollinger.dungeoneer.Dungeoneer;
import io.wollinger.dungeoneer.command.Command;
import io.wollinger.dungeoneer.command.CommandArgument;
import io.wollinger.dungeoneer.command.CommandManager;
import io.wollinger.dungeoneer.command.CommandResult;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class CMDHelp extends Command {
    private Dungeoneer dungeoneer;
    private CommandManager cmdManager;

    public CMDHelp(Dungeoneer dungeoneer, CommandManager cmdManager) {
        super("help", "View available commands");
        this.dungeoneer = dungeoneer;
        this.cmdManager = cmdManager;
    }

    @Override
    public CommandResult run(String serverID, MessageReceivedEvent event, ArrayList<CommandArgument> args) {
        StringBuilder message = new StringBuilder("Available commands:\n");
        for(Command cmd : cmdManager.getAllCommandsAsList()) {
            message.append(dungeoneer.getServer(serverID).getConfig().getCommandPrefix()).append(cmd.getCMD()).append(" - ").append(cmd.getDescription()).append("\n");
        }
        event.getChannel().sendMessage(message.toString()).queue();
        return CommandResult.SUCCESS;
    }
}
