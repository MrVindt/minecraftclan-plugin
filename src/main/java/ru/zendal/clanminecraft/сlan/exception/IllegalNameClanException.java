package ru.zendal.clanminecraft.сlan.exception;


public class IllegalNameClanException extends IllegalArgumentException implements ClanManagerException {
    public IllegalNameClanException(String message) {
        super(message);
    }

    public IllegalNameClanException(String message, Throwable cause) {
        super(message, cause);
    }
}
