package ru.zendal.clanminecraft.event;

import com.google.inject.Inject;
import org.bukkit.Chunk;
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

    private final ClanManager clanManager;
    private final PluginLocalization pluginLocalization;

    @Inject
    public PlayerEvent(ClanManager clanManager, PluginLocalization pluginLocalization) {
        this.clanManager = clanManager;
        this.pluginLocalization = pluginLocalization;
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent blockBreakEvent) {
        if (!this.checkPermissionForPlayerAtChuck(blockBreakEvent.getPlayer(), blockBreakEvent.getBlock().getChunk())) {
            blockBreakEvent.getPlayer().sendMessage(pluginLocalization.getCommandLocale().getOnClanEventOnBreakBlockError());
            blockBreakEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onPutBlock(BlockPlaceEvent blockPlaceEvent) {
        if (!this.checkPermissionForPlayerAtChuck(blockPlaceEvent.getPlayer(), blockPlaceEvent.getBlock().getChunk())) {
            blockPlaceEvent.getPlayer().sendMessage(pluginLocalization.getCommandLocale().getOnClanEventOnPutBlockError());
            blockPlaceEvent.setCancelled(true);
        }
    }

    /**
     * Check for permission interact player at chunk
     *
     * @param player       the current player
     * @param chunkAtBlock the chunk that the player interacts with
     * @return {@code true} if player is member of plan else {@code false}
     */
    private boolean checkPermissionForPlayerAtChuck(Player player, Chunk chunkAtBlock) {
        var chunkNow = player.getLocation().getChunk();
        var chunkInClan = clanManager.getAllClans().stream().anyMatch(clan ->
                this.equalsChuck(clan.getMainChunk(), chunkNow) || this.equalsChuck(chunkAtBlock, chunkNow)
        );
        var playerInClan = clanManager.getAllClans().stream().anyMatch(clan ->
                clan.getMemberList().stream().anyMatch(member -> member.getPlayer().getUniqueId().equals(player.getUniqueId())));
        return !chunkInClan || playerInClan;
    }

    /**
     * Comparing two chuck between self
     *
     * @param chunk        first chuck
     * @param chunkAnother second chuck
     * @return {@code true} if two chuck equals between self else {@code false}
     */
    private boolean equalsChuck(Chunk chunk, Chunk chunkAnother) {
        return chunk.getX() == chunkAnother.getX() &&
                chunk.getZ() == chunkAnother.getZ();
    }
}
