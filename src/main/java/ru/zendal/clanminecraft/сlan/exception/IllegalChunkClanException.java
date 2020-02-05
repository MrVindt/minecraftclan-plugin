package ru.zendal.clanminecraft.сlan.exception;

public class IllegalChunkClanException extends IllegalArgumentException implements ClanManagerException {
    public IllegalChunkClanException(String message){
        super(message);
    }
}
