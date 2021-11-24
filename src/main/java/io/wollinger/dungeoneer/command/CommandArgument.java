package io.wollinger.dungeoneer.command;

import io.wollinger.dungeoneer.utils.StringUtils;

public class CommandArgument {
    private String content;
    private long id;
    private TYPE type;

    enum TYPE { NORMAL, TAG_USER, TAG_CHANNEL }

    private static final String TAG_USER_REGEX = "<@![0-9]+>";
    private static final String TAG_USER_REGEX_ALT = "<@[0-9]+>";
    private static final String TAG_CHANNEL_REGEX = "<#[0-9]+>";

    // Explanation: Hello; "Hello world"; "@SvenWollinger"; Hello@SvenWollinger
    // This means that tags are only accepted as tags if they are written with a space between text, as a seperate argument.
    public CommandArgument(String content) {
        this.content = content;
        if(content.matches(TAG_USER_REGEX) || content.matches(TAG_USER_REGEX_ALT)) {
            type = TYPE.TAG_USER;
            id = getIDFromContent(content);
        } else if(content.matches(TAG_CHANNEL_REGEX)) {
            type = TYPE.TAG_CHANNEL;
            id = getIDFromContent(content);
        } else {
            type = TYPE.NORMAL;
        }
    }

    private long getIDFromContent(String content) {
        if(content.matches(TAG_USER_REGEX)) {
            if(content.startsWith("<@!"))
                return Long.parseLong(content.replace("<@!", "").replace(">", ""));
            else
                return Long.parseLong(content.replace("<@", "").replace(">", ""));
        } else if(content.matches(TAG_CHANNEL_REGEX)) {
            return Long.parseLong(content.replace("<#", "").replace(">", ""));
        }
        return 0;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return StringUtils.format("[Content: %c, Type: %c, ID: %c]", content, type, id);
    }
}
