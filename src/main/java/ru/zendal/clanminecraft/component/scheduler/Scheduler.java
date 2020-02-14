package ru.zendal.clanminecraft.component.scheduler;

/**
 * Scheduler component, for run task
 */
public interface Scheduler {


    /**
     * Run timer (repeated task)
     *
     * @param runnable task
     * @param delay    delay (in ticks (20 ticks = 1 seconds)) before start first
     * @param period   delay after end task (in ticks (20 ticks = 1 seconds))
     */
    void runTimer(Runnable runnable, long delay, long period);

}
