package io.wollinger.dungeoneer.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CMDPing extends Command{
    public CMDPing() {
        super("ping", "Pong!");
    }

    @Override
    public CommandResult run(String serverID, MessageReceivedEvent event, CommandArgument... args) {
        event.getChannel().sendMessage("Pong!").queue();
        return CommandResult.SUCCESS;
    }

}
