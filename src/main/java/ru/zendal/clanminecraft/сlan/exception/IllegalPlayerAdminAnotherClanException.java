package ru.zendal.clanminecraft.сlan.exception;

public class IllegalPlayerAdminAnotherClanException extends IllegalArgumentException implements ClanManagerException {
    public IllegalPlayerAdminAnotherClanException(String message){
        super(message);
    }
}
