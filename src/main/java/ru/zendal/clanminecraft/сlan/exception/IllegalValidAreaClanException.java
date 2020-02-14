package ru.zendal.clanminecraft.сlan.exception;

public class IllegalValidAreaClanException extends IllegalArgumentException implements ClanManagerException {
    public IllegalValidAreaClanException(String message){
        super(message);
    }
}
