package io.wollinger.dungeoneer.command;

import io.wollinger.dungeoneer.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class CMDHelp extends Command {
    private CommandManager cmdManager;

    public CMDHelp(CommandManager cmdManager) {
        super("help", "View available commands");
        this.cmdManager = cmdManager;
    }

    @Override
    public CommandResult run(String serverID, MessageReceivedEvent event, ArrayList<CommandArgument> args) {
        String message = "Available commands:\n";
        for(Command cmd : cmdManager.getAllCommandsAsList()) {
            message += cmd.getCMD() + " - " + cmd.getDescription() + "\n";
        }
        event.getChannel().sendMessage(message).queue();
        return CommandResult.SUCCESS;
    }
}
