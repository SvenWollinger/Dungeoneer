package io.wollinger.dungeoneer.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class CMDArgTest extends Command {
    public CMDArgTest() {
        super("argtest", "Test arguments dynamically");
    }

    @Override
    public CommandResult run(String serverID, MessageReceivedEvent event, ArrayList<CommandArgument> args) {
        String message = Arrays.toString(args.toArray());
        event.getChannel().sendMessage(message).queue();
        return CommandResult.SUCCESS;
    }
}
