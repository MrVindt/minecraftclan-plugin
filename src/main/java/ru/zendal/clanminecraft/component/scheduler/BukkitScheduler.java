package ru.zendal.clanminecraft.component.scheduler;

import com.google.inject.Inject;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Implements of scheduler, based on Bukkit scheduler.
 */
public final class BukkitScheduler implements Scheduler {

    /**
     * Instance of Java plugin
     */
    private final JavaPlugin javaPlugin;

    /**
     * Constructor
     *
     * @param javaPlugin instance of java plugin
     */
    @Inject
    public BukkitScheduler(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }


    @Override
    public void runTimer(Runnable runnable, long delay, long period) {
        javaPlugin.getServer().getScheduler().runTaskTimer(this.javaPlugin, runnable, delay, period);
    }
}
