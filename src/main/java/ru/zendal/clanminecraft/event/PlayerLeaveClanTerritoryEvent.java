package ru.zendal.clanminecraft.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import ru.zendal.clanminecraft.сlan.Clan;

/**
 * Event of when player leave form clan territory
 */
public final class PlayerLeaveClanTerritoryEvent extends Event {
    /**
     * Handler list
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
     * @param player who leave from territory clan
     * @param clan   clan instance
     */
    public PlayerLeaveClanTerritoryEvent(Player player, Clan clan) {
        super(false);
        this.player = player;
        this.clan = clan;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Required method
     *
     * @return handler list
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Get player
     *
     * @return Instance of player who leaved from territory of clan
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get clan
     *
     * @return clan
     */
    public Clan getClan() {
        return clan;
    }
}
