package io.wollinger.dungeoneer.command.cmd;

import io.wollinger.dungeoneer.command.Command;
import io.wollinger.dungeoneer.command.CommandArgument;
import io.wollinger.dungeoneer.command.CommandResult;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class CMDPing extends Command {
    public CMDPing() {
        super("ping", "Pong!");
    }

    @Override
    public CommandResult run(String serverID, MessageReceivedEvent event, ArrayList<CommandArgument> args) {
        event.getChannel().sendMessage("Pong!").queue();
        return CommandResult.SUCCESS;
    }

}
