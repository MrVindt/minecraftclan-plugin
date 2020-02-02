package ru.zendal.clanminecraft.component.logger;

/**
 * Interface of plugin logger
 */
public interface PluginLogger {

    /**
     * Print info message.
     *
     * @param message message for logging
     * @param data    data for replace placeholder into message
     */
    void info(String message, Object... data);

    /**
     * Print error message.
     *
     * @param message message for logging
     * @param data    data for replace placeholder into message
     */
    void error(String message, Object... data);
}
