package ru.zendal.clanminecraft;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import ru.zendal.clanminecraft.command.CommandClanAddChunk;
import ru.zendal.clanminecraft.command.CommandClan;
import ru.zendal.clanminecraft.configuration.CommandHandlerConfiguration;
import ru.zendal.clanminecraft.configuration.GuiceConfiguration;
import ru.zendal.clanminecraft.configuration.LanguageConfiguration;
import ru.zendal.clanminecraft.configuration.PluginConfiguration;
import ru.zendal.clanminecraft.event.PlayerEvent;

import java.io.File;

/**
 * Startup class for java plugin ClanMinecraft
 */
public final class ClanMinecraft extends JavaPlugin {

    /**
     * Instance of injector
     */
    private Injector injector;

    /**
     * Default constructor with default injector
     */
    public ClanMinecraft() {
        this.injector = this.createDefaultInjector();
    }

    /**
     * Additional constructor
     *
     * @param loader      loader of plugin
     * @param description description of plugin
     * @param dataFolder  data folder
     * @param file        file of plugin
     * @param injector    injector instance
     */
    public ClanMinecraft(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file, Injector injector) {
        super(loader, description, dataFolder, file);
        this.injector = injector;
    }

    @Override
    public void onEnable() {
        this.getCommand("clan").setExecutor(this.injector.getInstance(CommandClan.class));
        this.getCommand("clan.add.chunk").setExecutor(this.injector.getInstance(CommandClanAddChunk.class));
        this.getServer().getPluginManager().registerEvents(this.injector.getInstance(PlayerEvent.class), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Create default injector
     *
     * @return instance of injector
     */
    private Injector createDefaultInjector() {
        return Guice.createInjector(
                new GuiceConfiguration(this),
                new PluginConfiguration(this),
                new LanguageConfiguration(this),
                new CommandHandlerConfiguration()
        );
    }
}
