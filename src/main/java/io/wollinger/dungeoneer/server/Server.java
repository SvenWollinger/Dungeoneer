package io.wollinger.dungeoneer.server;

import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private String id;
    private ArrayList<Group> groups = new ArrayList<>();
    private final HashMap<String, Character> characters = new HashMap<>(); //ID, Character -> Keeps track of all characters by owner id
}
