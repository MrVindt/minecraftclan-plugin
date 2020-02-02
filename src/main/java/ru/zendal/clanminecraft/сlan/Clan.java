package ru.zendal.clanminecraft.сlan;

import lombok.Builder;
import lombok.Data;
import org.bukkit.Chunk;

import java.util.List;

/**
 * Entity of Clan
 */
@Data
@Builder
public class Clan {

    /**
     * Name Clan
     */
    private String name;
    /**
     * The Central area is the reserved area of the clan
     */
    private Chunk mainChunk;
    /**
     * List of member
     */
    private List<Member> memberList;

}
