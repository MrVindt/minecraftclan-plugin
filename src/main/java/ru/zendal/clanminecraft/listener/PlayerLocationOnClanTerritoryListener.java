package ru.zendal.clanminecraft.listener;

import com.google.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import ru.zendal.clanminecraft.component.scheduler.Scheduler;
import ru.zendal.clanminecraft.event.PlayerEntryOnClanTerritoryEvent;
import ru.zendal.clanminecraft.event.PlayerLeaveClanTerritoryEvent;
import ru.zendal.clanminecraft.сlan.Clan;
import ru.zendal.clanminecraft.сlan.ClanManager;

import java.util.HashMap;
import java.util.Map;

public final class PlayerLocationOnClanTerritoryListener implements Listener {


    private final Scheduler scheduler;
    private final ClanManager clanManager;

    @Inject
    public PlayerLocationOnClanTerritoryListener(Scheduler scheduler, ClanManager clanManager) {
        this.scheduler = scheduler;
        this.clanManager = clanManager;
        this.initScheduler();
    }


    private void initScheduler() {
        this.scheduler.runTimer(new Runnable() {
            Map<String, Boolean> bufferLocation = new HashMap<>();

            @Override
            public void run() {

                Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                    var locationChunk = player.getLocation().getChunk();
                    Boolean isNear = bufferLocation.get(player.getUniqueId().toString());
                    if (isNear == null) {
                        bufferLocation.put(player.getUniqueId().toString(), true);
                    } else {
                        for (Clan clan : clanManager.getAllClans()) {
                            var mainChunk = clan.getMainChunk();
                            if (mainChunk.getX() + 7 >= locationChunk.getX() &&
                                    mainChunk.getZ() + 7 >= locationChunk.getZ() &&
                                    mainChunk.getX() - 7 <= locationChunk.getX() &&
                                    mainChunk.getZ() - 7 <= locationChunk.getZ()) {
                                if (!isNear) {
                                    bufferLocation.replace(player.getUniqueId().toString(), true);
                                    new PlayerEntryOnClanTerritoryEvent(player, clan).callEvent();
                                    break;
                                }

                            } else if (isNear) {
                                bufferLocation.replace(player.getUniqueId().toString(), false);
                                new PlayerLeaveClanTerritoryEvent(player, clan).callEvent();
                                break;
                            }
                        }
                    }
                });
            }
        }, 0L, 6L);
    }
}
