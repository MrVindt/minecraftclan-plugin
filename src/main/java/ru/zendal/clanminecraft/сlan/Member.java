package ru.zendal.clanminecraft.сlan;

import lombok.Builder;
import lombok.Data;
import org.bukkit.entity.Player;

/**
 * Member of Clan
 */
@Data
@Builder
public class Member {

    /**
     * Instance of player
     */
    private Player player;
    /**
     * Role for current member
     */
    private RoleMember role;

}
