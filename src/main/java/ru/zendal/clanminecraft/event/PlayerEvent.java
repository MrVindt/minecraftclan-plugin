package ru.zendal.clanminecraft.event;

import com.google.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.сlan.ClanManager;

/**
 * Events of the player
 */
public class PlayerEvent implements Listener {

    public final ClanManager clanManager;
    public final PluginLocalization pluginLocalization;

    @Inject
    public PlayerEvent(ClanManager clanManager, PluginLocalization pluginLocalization) {
        this.clanManager = clanManager;
        this.pluginLocalization = pluginLocalization;
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent blockBreakEvent) {
        if (!getResultOnEvent(blockBreakEvent.getPlayer(), blockBreakEvent)) {
            blockBreakEvent.getPlayer().sendMessage(pluginLocalization.getCommandLocale().getOnClanEventOnBreakBlockError());
            blockBreakEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onPutBlock(BlockPlaceEvent blockPlaceEvent) {
        if (!getResultOnEvent(blockPlaceEvent.getPlayer(), blockPlaceEvent)) {
            blockPlaceEvent.getPlayer().sendMessage(pluginLocalization.getCommandLocale().getOnClanEventOnPutBlockError());
            blockPlaceEvent.setCancelled(true);
        }
    }

    private boolean getResultOnEvent(Player player, Object obj) {
        var chunkNow = player.getLocation().getChunk();
        var blockEvent = obj instanceof BlockPlaceEvent ? ((BlockPlaceEvent) obj) : ((BlockBreakEvent) obj);
        var chunkInClan = clanManager.getAllClans().stream().anyMatch(clan -> clan.getMainChunk().getX() == chunkNow.getX() && clan.getMainChunk().getZ() == chunkNow.getZ()
                || blockEvent.getBlock().getChunk() != chunkNow);
        var playerInClan = clanManager.getAllClans().stream().anyMatch(clan ->
                clan.getMemberList().stream().anyMatch(member -> member.getPlayer().getUniqueId().equals(player.getUniqueId())));
        return !chunkInClan || playerInClan;
    }
}
