package ru.zendal.clanminecraft.сlan;

import com.google.inject.Singleton;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import ru.zendal.clanminecraft.сlan.exception.IllegalChunkClanException;
import ru.zendal.clanminecraft.сlan.exception.IllegalNameClanException;
import ru.zendal.clanminecraft.сlan.exception.IllegalNameClanIsExistException;
import ru.zendal.clanminecraft.сlan.exception.IllegalPlayerAdminAnotherClanException;

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

    @Override
    public void create(String nameClan, Chunk mainChunk, Player player) {
        this.validate(nameClan, mainChunk, player);
        Clan clan = Clan.builder().name(nameClan).mainChunk(mainChunk).memberList(
                List.of(
                        Member.builder().role(RoleMember.ADMIN).player(player).build()
                )
        ).build();
        this.listClan.add(clan);
    }


    private void validate(String nameClan, Chunk mainChunk, Player player) {
        if (nameClan.length() < 3 || nameClan.length() > 20) {
            throw new IllegalNameClanException("Name must be unique and length more 2 and less 3");
        }
        if (!this.checkUniqueNameClan(nameClan)) {
            throw new IllegalNameClanIsExistException(nameClan + " is exist");
        }
        if (!this.checkChunk(mainChunk)) {
            throw new IllegalChunkClanException("Chunk is busy with another clan");
        }
        if (this.checkPlayerAdminAnotherClan(player)){
            throw new IllegalPlayerAdminAnotherClanException("The player is the admin of another clan");
        }
    }

    private boolean checkPlayerAdminAnotherClan(Player player){
        return this.listClan.stream().anyMatch(clan -> clan.getMemberList().stream().anyMatch(member -> member.getPlayer() == player));
    }

    private boolean checkUniqueNameClan(String nameClan) {
        return this.listClan.stream().noneMatch((clan) -> clan.getName().equalsIgnoreCase(nameClan));
    }

    private boolean checkChunk(Chunk mainChunk) {
        var x = mainChunk.getX();
        var z = mainChunk.getZ();
        var maxX = x + 14;
        var maxZ = z + 14;
        var minX = x - 14;
        var minZ = z - 14;
        return this.listClan.stream().noneMatch(clan ->
                maxX >= clan.getMainChunk().getX() && maxZ >= clan.getMainChunk().getZ()
                        && clan.getMainChunk().getX() >= minX && clan.getMainChunk().getZ() >= minZ);
    }


    @Override
    public List<Clan> getAllClans() {
        return this.listClan;
    }

}
