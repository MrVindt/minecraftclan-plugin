package ru.zendal.clanminecraft.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Configuration of plugin.
 * Wrapper for Config file
 *
 * @see JavaPlugin#getConfig()
 */
public final class PluginConfiguration extends AbstractModule {

    /**
     * Instance of java plugin
     */
    private final JavaPlugin javaPlugin;

    /**
     * Constructor of configuration
     *
     * @param javaPlugin instance of java plugin
     */
    public PluginConfiguration(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.initializeConfiguration();
    }

    /**
     * Initialize configuration file, on user side
     */
    private void initializeConfiguration() {
        this.javaPlugin.saveDefaultConfig();
    }

    /**
     * Get locale from config file
     *
     * @return Locale string
     */
    @Provides
    @Named("pluginLocale")
    public String localePluginProvider() {
        return this.javaPlugin.getConfig().getString("settings.locale");
    }

}
