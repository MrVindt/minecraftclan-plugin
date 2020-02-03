package ru.zendal.clanminecraft.сlan;

import com.google.inject.Singleton;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement of Clan Manager
 * Usage memory storage
 */
@Singleton
public class ClanManagerMemory implements ClanManager {

    /**
     * Storage of Clans
     */
    private List<Clan> listClan = new ArrayList<>();
    private List<String> listNameClans = new ArrayList<>();

    @Override
    public void create(String nameClan, Chunk mainChunk, Player player) {
        Clan clan = Clan.builder().name(nameClan).mainChunk(mainChunk).memberList(
                List.of(
                        Member.builder().role(RoleMember.ADMIN).player(player).build()
                )
        ).build();
        this.listClan.add(clan);
        this.listNameClans.add(nameClan);
    }

    @Override
    public List<Clan> getAllClans() {
        return this.listClan;
    }

    @Override
    public List<String> getAllNameClans(){
        return this.listNameClans;
    }
}
