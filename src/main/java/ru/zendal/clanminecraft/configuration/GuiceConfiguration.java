package ru.zendal.clanminecraft.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zendal.clanminecraft.component.logger.PluginLogger;
import ru.zendal.clanminecraft.component.logger.SimplePluginLoggerImpl;
import ru.zendal.clanminecraft.component.scheduler.BukkitScheduler;
import ru.zendal.clanminecraft.component.scheduler.Scheduler;
import ru.zendal.clanminecraft.сlan.ClanManager;
import ru.zendal.clanminecraft.сlan.ClanManagerMemory;

/**
 * Configuration file of Guice
 */
public final class GuiceConfiguration extends AbstractModule {

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

    @Override
    protected void configure() {
        bind(ClanManager.class).to(ClanManagerMemory.class);
        bind(Scheduler.class).to(BukkitScheduler.class);
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
