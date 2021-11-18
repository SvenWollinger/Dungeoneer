package io.wollinger.dungeoneer.utils;

public class StringUtils {
    public static String format(final String message, final Object ...args) {
        String newMessage = message;
        for (Object arg : args) {
            String replacer = "NULL";
            if (arg != null)
                replacer = arg.toString();
            newMessage = newMessage.replaceFirst("%c", replacer);
        }
        return newMessage;
    }
}
