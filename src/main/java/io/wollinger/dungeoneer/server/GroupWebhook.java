package io.wollinger.dungeoneer.server;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

public class GroupWebhook {
    private final WebhookClient client;

    public GroupWebhook(String url) {
        client = WebhookClient.withUrl(url);
    }

    public void sendMessage(Character character, String content) {
        sendMessage(character.getName(), character.getAvatarUrl(), content);
    }

    public void sendMessage(String name, String avatarUrl, String content) {
        WebhookMessageBuilder builder = new WebhookMessageBuilder();
        builder.setUsername(name);
        builder.setAvatarUrl(avatarUrl);
        builder.setContent(content);
        client.send(builder.build());
    }
}
