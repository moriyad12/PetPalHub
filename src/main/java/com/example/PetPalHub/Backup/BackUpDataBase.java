package com.example.PetPalHub.Backup;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class BackUpDataBase {
    public static boolean backUpDb() {
        try {

            CodeSource codeSource = BackUpDataBase.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();


            String dbName = "petPal";
            String dbUser = "springstudent";
            String dbPass = "springstudent";


            String folderPath = jarDir + "\\backup";

            File f1 = new File(folderPath);
            f1.mkdir();
            String checkServerCmd = "C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqladmin -u" + dbUser + " -p" + dbPass + " ping";
            Process checkServerProcess = Runtime.getRuntime().exec(checkServerCmd);
            int serverStatus = checkServerProcess.waitFor();
            if (serverStatus == 0) {
                String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";
                ProcessBuilder processBuilder = new ProcessBuilder(
                        "C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump",
                        "--user=" + dbUser,
                        "--password=" + dbPass,
                        dbName,
                        "-r",
                        savePath
                );
                processBuilder.redirectErrorStream(true);
                Process runtimeProcess = processBuilder.start();
                int processComplete = runtimeProcess.waitFor();
                try (InputStream inputStream = runtimeProcess.getInputStream();
                     InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                try (InputStream errorStream = runtimeProcess.getErrorStream();
                     InputStreamReader errorStreamReader = new InputStreamReader(errorStream);
                     BufferedReader errorBufferedReader = new BufferedReader(errorStreamReader)) {

                    String errorLine;
                    while ((errorLine = errorBufferedReader.readLine()) != null) {
                        System.err.println(errorLine);
                    }
                }
                return processComplete == 0;
            } else {
                System.out.println("MySQL server is not running.");
            }
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            System.out.println("Error at Backuprestore" + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
        return false;
    }

    public static boolean restoreDB() {
        try {
            CodeSource codeSource = BackUpDataBase.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            String dbName = "petPal";
            String dbUser = "springstudent";
            String dbPass = "springstudent";

            String restorePath = jarDir + "\\backup" + "\\" + "backup.sql";


            String[] executeCmd = new String[]{"C:/Program Files/MySQL/MySQL Server 8.0/bin/mysql", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            try (InputStream inputStream = runtimeProcess.getInputStream();
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            try (InputStream errorStream = runtimeProcess.getErrorStream();
                 InputStreamReader errorStreamReader = new InputStreamReader(errorStream);
                 BufferedReader errorBufferedReader = new BufferedReader(errorStreamReader)) {

                String errorLine;
                while ((errorLine = errorBufferedReader.readLine()) != null) {
                    System.err.println(errorLine);
                }
            }
            if (processComplete == 0) {
                return true;
            } else {
                return false;
            }

        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            System.out.println("Error at Restoredbfromsql" + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }
        return false;

    }
}
