package ru.zendal.clanminecraft.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zendal.clanminecraft.component.logger.SimplePluginLoggerImpl;
import ru.zendal.clanminecraft.component.logger.PluginLogger;

/**
 * Configuration file of Guice
 */
public class GuiceConfiguration extends AbstractModule {

    /**
     * Instance of JavaPlugin
     */
    private final JavaPlugin javaPlugin;

    /**
     * Constructor
     *
     * @param javaPlugin instance of java plugin
     */
    public GuiceConfiguration(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    @Provides
    public JavaPlugin javaPluginProvider() {
        return this.javaPlugin;
    }


    @Provides
    public PluginLogger pluginLoggerProvider() {
        return new SimplePluginLoggerImpl(this.javaPlugin.getLogger());
    }
}
