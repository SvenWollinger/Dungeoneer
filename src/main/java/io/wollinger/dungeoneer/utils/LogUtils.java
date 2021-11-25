package io.wollinger.dungeoneer.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;

public class LogUtils {
    private static Logger LOGGER = null;
    public static String ID_MAIN = "MAIN";

    public static void init() {
        try {
            new File("logs").mkdirs();
            InputStream stream = LogUtils.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
            LOGGER = Logger.getLogger(LogUtils.class.getName());

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy__HH_mm_ss");
            FileHandler fileHandler = new FileHandler("logs/" + format.format(Calendar.getInstance().getTime()) + ".log");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static String toID(String id) {
        return StringUtils.format("[%c]", id);
    }

    public static void log(String id, String message, final Object... args) {
        LogUtils.log(id, Level.INFO, StringUtils.format(message, args));
    }

    public static void log(String id, String message) {
        LogUtils.log(id, Level.INFO, message);
    }

    public static void log(String id, Level level, String message, final Object... args) {
        LogUtils.log(id, level, StringUtils.format(message, args));
    }

    public static void log(String id, Level level, String message) {
        LOGGER.log(level, toID(id) + " " + message);
    }
}
