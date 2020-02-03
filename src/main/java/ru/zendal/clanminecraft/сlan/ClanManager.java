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
     * Get all Clan
     *
     * @return List of Clans
     */
    List<Clan> getAllClans();
    List<String> getAllNameClans();
}
