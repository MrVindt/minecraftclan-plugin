package ru.zendal.clanminecraft.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.bukkit.plugin.java.JavaPlugin;

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
}
