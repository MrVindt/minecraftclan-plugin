package ru.zendal.clanminecraft.event;

import com.google.inject.Inject;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import ru.zendal.clanminecraft.сlan.ClanManager;

public class Event implements Listener {

    public final ClanManager clanManager;

    @Inject
    public Event(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent blockBreakEvent){
        if (getClanByChunk(blockBreakEvent.getPlayer()))
            blockBreakEvent.setCancelled(true);
    }

    boolean getClanByChunk(Player player){
        var chunkNow = player.getLocation().getChunk();
        return clanManager.getAllClans().stream().anyMatch(clan -> clan.getMainChunk() == chunkNow && clan.getMemberList().equals(player.getName()));
    }
}
