package ru.zendal.clanminecraft.event;

import com.google.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import ru.zendal.clanminecraft.сlan.ClanManager;

/**
 * Events of the player
 */
public class Event implements Listener {

    public final ClanManager clanManager;

    @Inject
    public Event(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent blockBreakEvent) {
        if (getResultOnEvent(blockBreakEvent.getPlayer()) == false)
            blockBreakEvent.setCancelled(true);
    }

    @EventHandler
    public void onPutBlock(BlockPlaceEvent blockPlaceEvent) {
        if (getResultOnEvent(blockPlaceEvent.getPlayer()) == false)
            blockPlaceEvent.setCancelled(true);
    }

    boolean getResultOnEvent(Player player) {
        var chunkNow = player.getLocation().getChunk();
        var chunkInClan = clanManager.getAllClans().stream().anyMatch(clan -> clan.getMainChunk() == chunkNow);
        var playerInClan = clanManager.getAllClans().stream().anyMatch(clan ->
                clan.getMemberList().stream().anyMatch(member -> member.getPlayer() == player));
        if (chunkInClan == true && playerInClan == true || chunkInClan == false && playerInClan == true ||
                chunkInClan == false && playerInClan == false)
            return true;
        else
            return false;
    }
}
