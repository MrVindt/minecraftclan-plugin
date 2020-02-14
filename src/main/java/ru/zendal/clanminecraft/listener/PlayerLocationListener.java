package ru.zendal.clanminecraft.listener;

import com.google.inject.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.event.PlayerEntryOnClanTerritoryEvent;
import ru.zendal.clanminecraft.event.PlayerLeaveClanTerritoryEvent;

/**
 * Listener about player location
 */
public final class PlayerLocationListener implements Listener {

    /**
     * Localization component
     */
    private final PluginLocalization pluginLocalization;

    /**
     * Constructor
     *
     * @param pluginLocalization component for getting localization
     */
    @Inject
    public PlayerLocationListener(PluginLocalization pluginLocalization) {
        this.pluginLocalization = pluginLocalization;
    }

    /**
     * On Player entry on clan territory
     *
     * @param event data about
     */
    @EventHandler
    public void onPlayerEntryClanTerritory(PlayerEntryOnClanTerritoryEvent event) {
        event.getPlayer().sendTitle(
                pluginLocalization.getTitleLocalization().getOnEntryClanTerritory(event.getClan().getName()),
                pluginLocalization.getTitleLocalization().getSubOnEntryClanTerritory(),
                5, 20, 5);
    }

    /**
     * On player leave from territory of clan
     *
     * @param event data about
     */
    @EventHandler
    public void onPlayerLeaveClanTerritory(PlayerLeaveClanTerritoryEvent event) {
        event.getPlayer().sendTitle(
                pluginLocalization.getTitleLocalization().getOnExitClanTerritory(event.getClan().getName()),
                pluginLocalization.getTitleLocalization().getSubExitEntryClanTerritory(),
                5, 20, 5);
    }
}
