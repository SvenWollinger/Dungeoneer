package io.wollinger.dungeoneer.server;

import java.util.ArrayList;

public class Group {
    private String name;
    private String description;
    private long timeUnix;
    private boolean repeat;
    private GroupWebhook webhook;
    private final ArrayList<GroupMember> members = new ArrayList<>();
}
