package api.utilities;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class TestLogger {

    // Logger instance
    private static final Logger logger = LogManager.getLogger(TestLogger.class);

    // Info log method
    public static void logInfo(String message) {
        logger.info(message);
    }

    // Error log method
    public static void logError(String message) {
        logger.error(message);
    }

    // Debug log method
    public static void logDebug(String message) {
        logger.debug(message);
    }

    // Warn log method
    public static void logWarn(String message) {
        logger.warn(message);
    }

    // Fatal log method
    public static void logFatal(String message) {
        logger.fatal(message);
    }

    // Custom log for exceptions
    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
