package io.wollinger.dungeoneer.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    // This method copies our current db file to the backups folder and renames it, always keeping the latest 5 backups alive.
    // This should be called BEFORE saving, so that a new fresh db file is created in the currents place
    public static void backupIncrement(String serverID, int backupsToKeep) {
        new File("backups").mkdir();
        File currentDB = new File(StringUtils.format("%c.db", serverID));

        File[] backups = new File[backupsToKeep];
        for(int i = backupsToKeep-1; i >= 0; i--) {
            backups[i] = new File(StringUtils.format("backups/%c.db.%c", serverID, i));
            if (i == backupsToKeep - 1) {
                if(backups[i].exists())
                    backups[i].delete();
            } else {
                backups[i].renameTo(backups[i+1]);
            }
        }

        try {
            Files.copy(currentDB.toPath(), backups[0].toPath());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        currentDB.delete();
    }
}
