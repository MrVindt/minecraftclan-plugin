package ru.zendal.clanminecraft.сlan;

import lombok.Builder;
import lombok.Data;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.List;

@Data
@Builder

public class Clan {

    private String name;
    private Chunk mainChunk;
    private List<Player> memberList;

}
