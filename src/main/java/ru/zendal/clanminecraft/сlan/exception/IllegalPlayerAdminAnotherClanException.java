package ru.zendal.clanminecraft.сlan.exception;

import ru.zendal.clanminecraft.сlan.Clan;

public class IllegalPlayerAdminAnotherClanException extends IllegalArgumentException implements ClanManagerException {
    private final Clan clan;

    public IllegalPlayerAdminAnotherClanException(String message, Clan clan) {
        super(message);
        this.clan = clan;
    }


    public Clan getClan() {
        return clan;
    }
}
