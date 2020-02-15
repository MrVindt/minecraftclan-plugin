package ru.zendal.clanminecraft.сlan;

import com.google.inject.Singleton;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import ru.zendal.clanminecraft.сlan.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        this.validateCreate(nameClan, mainChunk, player);
        Clan clan = Clan.builder().name(nameClan).mainChunk(mainChunk).memberList(
                List.of(
                        Member.builder().role(RoleMember.ADMIN).player(player).build()
                )
        ).listPurchasedChunks(List.of(mainChunk)).build();
        this.listClan.add(clan);
    }

    @Override
    public void addChunk(Chunk chunk, Player player) {
        var nameClan = validateAddChunk(chunk, player);
        var listPurchasedChunks = this.listClan.stream().filter(clan -> clan.getName().equals(nameClan)).flatMap(
                clan -> clan.getListPurchasedChunks().stream()).collect(Collectors.toList());
        listPurchasedChunks.add(chunk);
        this.listClan.stream().filter(clan -> clan.getName().equals(nameClan)).forEach(
                clan -> clan.setListPurchasedChunks(listPurchasedChunks));
        player.sendMessage(listClan.toString());
    }

    private String validateAddChunk(Chunk chunk, Player player) {
        var nameClanPlayer = this.checkBelongPlayerToClan(player);
        if (nameClanPlayer.isEmpty()) {
            throw new IllegalPlayerNotMemberClanException("You can't add a chunk to a clan because you're not a member of a clan and you're not admin of clan");
        }
        var nameClan = this.checkChunkClanNearby(chunk, player, nameClanPlayer.get());
        if (nameClan.isEmpty()) {
            throw new IllegalChunkNearbyException("Nearby there are no chunks with the clan to expand the area");
        }
        if (checkValidAreaClan(chunk, nameClanPlayer.get())) {
            throw new IllegalValidAreaClanException("You can't add a chunk to a clan because he allowed area of the clan has been exceeded");
        }
        if (this.checkChunkToClan(chunk, nameClanPlayer.get())) {
            throw new IllegalChunkToClanException("The chunk has already been added to the clan");
        }
        return nameClanPlayer.get();
    }

    private boolean checkValidAreaClan(Chunk chunk, String nameClanPlayer) {
        var x = chunk.getX();
        var z = chunk.getZ();
        var maxX = x + 7;
        var maxZ = z + 7;
        var minX = x - 7;
        var minZ = z - 7;
        return this.listClan.stream().filter(clan -> clan.getName().equals(nameClanPlayer)).noneMatch(clan ->
                maxX >= clan.getMainChunk().getX() && maxZ >= clan.getMainChunk().getZ()
                        && clan.getMainChunk().getX() >= minX && clan.getMainChunk().getZ() >= minZ);
    }

    private boolean checkChunkToClan(Chunk chunk, String nameClanPlayer) {
        return this.listClan.stream().anyMatch(clan -> clan.getName().equals(nameClanPlayer) && clan.getListPurchasedChunks().contains(chunk));
    }

    private Optional<String> checkBelongPlayerToClan(Player player) {
        return this.listClan.stream().filter(clan -> clan.getMemberList().stream().anyMatch(
                member -> member.getPlayer().getUniqueId().equals(player.getUniqueId())))
                .map(clan -> clan.getName()).findFirst();
    }

    private Optional<String> checkChunkClanNearby(Chunk chunk, Player player, String nameClanPlayer) {
        var x = chunk.getX();
        var z = chunk.getZ();
        var world = player.getWorld();
        return this.listClan.stream().filter(clan -> clan.getName().equals(nameClanPlayer) && clan.getListPurchasedChunks().contains(world.getChunkAt(x, z + 1))
                || clan.getListPurchasedChunks().contains(world.getChunkAt(x, z - 1))
                || clan.getListPurchasedChunks().contains(world.getChunkAt(x + 1, z))
                || clan.getListPurchasedChunks().contains(world.getChunkAt(x - 1, z))).map(clan -> clan.getName()).findFirst();

    }

    private void validateCreate(String nameClan, Chunk mainChunk, Player player) {
        if (nameClan.length() < 3 || nameClan.length() > 20) {
            throw new IllegalNameClanException("Name must be unique and length more 2 and less 3");
        }
        if (!this.checkUniqueNameClan(nameClan)) {
            throw new IllegalNameClanIsExistException(nameClan + " is exist");
        }
        if (!this.checkChunk(mainChunk)) {
            throw new IllegalChunkClanException("Chunk is busy with another clan");
        }
        var optionalClan = this.checkPlayerAdminAnotherClan(player);
        if (optionalClan.isPresent()) {
            throw new IllegalPlayerAdminAnotherClanException("The player is the admin of another clan", optionalClan.get());
        }
    }

    private Optional<Clan> checkPlayerAdminAnotherClan(Player player) {
        return this.listClan.stream().filter(
                clan -> clan.getMemberList().stream().anyMatch(
                        member -> member.getPlayer().getUniqueId().equals(player.getUniqueId()) && member.getRole() == RoleMember.ADMIN
                )
        ).findFirst();
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
