package io.wollinger.dungeoneer.server;

public class Character {
    private String name;
    private String avatarUrl;
    private String description;
    private String creatorID;

    public Character(String name, String description, String avatarUrl, String creatorID) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.description = description;
        this.creatorID = creatorID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatorID() {
        return creatorID;
    }

}
