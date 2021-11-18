package io.wollinger.dungeoneer.server;

import java.util.ArrayList;

public class Group {
    private String name;
    private GroupWebhook webhook;
    private ArrayList<GroupMember> members = new ArrayList<>();
}
