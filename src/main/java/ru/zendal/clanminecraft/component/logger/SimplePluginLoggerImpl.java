package ru.zendal.clanminecraft.component.logger;


import java.util.logging.Logger;

/**
 * Simple plugin logger implements. Wrapper of logger
 */
public class SimplePluginLoggerImpl implements PluginLogger {

    /**
     * Instance of logger
     */
    private final Logger logger;

    /**
     * Constructor.
     *
     * @param logger instance of logger
     */
    public SimplePluginLoggerImpl(Logger logger) {
        this.logger = logger;
    }


    @Override
    public void info(String message, Object... data) {
        this.logger.info(this.appendDataIntoString(message, data));
    }

    @Override
    public void error(String message, Object... data) {
        this.logger.severe(this.appendDataIntoString(message, data));
    }

    /**
     * Replace placeholder "{}" with data.
     *
     * @param message raw message
     * @param data    data for replace placeholder's
     * @return prepared message
     */
    private String appendDataIntoString(String message, Object... data) {
        for (Object object : data) {
            message = message.replace("{}", object.toString());
        }
        return message;
    }
}
