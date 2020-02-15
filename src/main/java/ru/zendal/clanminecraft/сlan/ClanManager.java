package ru.zendal.clanminecraft.сlan;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Manipulator Clan
 *
 * @see Clan
 */
public interface ClanManager {

    /**
     * Create Clan
     *
     * @param nameClan  Name Clan
     * @param mainChunk The Central area is the reserved area of the clan
     * @param player    Creator of Clan
     */
    void create(String nameClan, Chunk mainChunk, Player player);

    /**
     * Add chunk in clan
     *
     * @param chunk  The chunk that the player is currently in
     * @param player The current player
     */
    void addChunk(Chunk chunk, Player player);

    /**
     * Get all Clan
     *
     * @return List of Clans
     */
    List<Clan> getAllClans();
}
