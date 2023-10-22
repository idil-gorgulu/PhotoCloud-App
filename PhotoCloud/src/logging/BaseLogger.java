package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseLogger {
    private static final String info = "application_info.txt";
    private static final String error = "application_error.txt";

    private static BufferedWriter infoWriter;
    private static BufferedWriter errorWriter;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("[E MMM dd HH:mm:ss z yyyy]");
    private static BaseLogger ins = null;

    private BaseLogger() {
        try {
            infoWriter = new BufferedWriter(new FileWriter(info, true));
            errorWriter = new BufferedWriter(new FileWriter(error, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (infoWriter != null) {
                logInfo("Application shutdown at " + dateFormat.format(new Date()));
            }
            closeWriters();
        }));
    }

    public static BaseLogger getInstance() {
        if (ins == null) {
            ins = new BaseLogger();
        }
        return ins;
    }

    public static void logInfo(String message) {
        if (infoWriter != null) {
            log(message, infoWriter);
        }
    }

    public static void logError(String message) {
        if (errorWriter != null) {
            log(message, errorWriter);
        }
    }

    private static void log(String message, BufferedWriter writer) {
        try {
            String time = dateFormat.format(new Date());
            String formattedMessage = String.format("%s [INFO] %s", time, message);
            writer.write(formattedMessage);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeWriters() {
        try {
            if (infoWriter != null) {
                infoWriter.close();
            }
            if (errorWriter != null) {
                errorWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


