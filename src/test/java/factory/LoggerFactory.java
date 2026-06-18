package factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerFactory
{

    private static final ThreadLocal<Logger> tlLogger = new ThreadLocal<>();

    private LoggerFactory()
    {
        // Private constructor to prevent instantiation
    }

    public static void setLogger(Class<?> clazz)
    {
        tlLogger.set(LogManager.getLogger(clazz));
    }

    public static Logger getLogger()
    {
        return tlLogger.get();
    }

    public static void cleanupLogger()
    {
        tlLogger.remove();
    }
}