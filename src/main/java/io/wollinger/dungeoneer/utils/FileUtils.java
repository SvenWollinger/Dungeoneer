package io.wollinger.dungeoneer.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    // This method copies our current db file to the backups folder and renames it, always keeping the latest 5 backups alive.
    // This should be called BEFORE saving, so that a new fresh db file is created in the currents place
    public static void backupIncrement(String serverID) {
        new File("backups").mkdir();
        File currentDB = new File(StringUtils.format("%c.db", serverID));
        File backupFile1 = new File(StringUtils.format("backups/%c.db.1", serverID));
        File backupFile2 = new File(StringUtils.format("backups/%c.db.2", serverID));
        File backupFile3 = new File(StringUtils.format("backups/%c.db.3", serverID));

        if(backupFile3.exists())
            backupFile3.delete();
        if(backupFile2.exists())
            backupFile2.renameTo(backupFile3);
        if(backupFile1.exists())
            backupFile1.renameTo(backupFile2);

        try {
            Files.copy(currentDB.toPath(), backupFile1.toPath());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        currentDB.delete();
    }
}
