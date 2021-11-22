package io.wollinger.dungeoneer.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
    private String cmd;
    private String description;

    public Command (String cmd, String description) {
        this.cmd = cmd;
        this.description = description;
    }

    public CommandResult run(String serverID, MessageReceivedEvent event, CommandArgument... args) {
        return CommandResult.SUCCESS;
    }

    public String getCMD() {
        return cmd;
    }

    public String getDescription() {
        return description;
    }
}
