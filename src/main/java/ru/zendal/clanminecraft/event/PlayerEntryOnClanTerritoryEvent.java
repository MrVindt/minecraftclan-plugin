package ru.zendal.clanminecraft.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import ru.zendal.clanminecraft.сlan.Clan;

/**
 * Event of when player entry on clan territory
 */
public final class PlayerEntryOnClanTerritoryEvent extends Event {

    /**
     * List of handler's
     */
    private static final HandlerList handlers = new HandlerList();

    /**
     * Instance of player
     */
    private final Player player;

    /**
     * Instance of clan
     */
    private final Clan clan;

    /**
     * Constructor
     *
     * @param player who entry
     * @param clan   On whose clan went
     */
    public PlayerEntryOnClanTerritoryEvent(Player player, Clan clan) {
        super(false);
        this.player = player;
        this.clan = clan;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Required method...
     *
     * @return handler list
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Get who entry
     *
     * @return an instance of the player who entered the territory of the clan
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get clan
     *
     * @return Clan on which have reached
     */
    public Clan getClan() {
        return clan;
    }
}
