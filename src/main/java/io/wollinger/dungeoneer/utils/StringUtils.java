package io.wollinger.dungeoneer.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //https://stackoverflow.com/a/366532
    public static ArrayList<String> splitIntoArray(String content) {
        ArrayList<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(content);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }
        return matchList;
    }
}
