package ru.zendal.clanminecraft.сlan.exception;

public class IllegalChunkToClanException extends IllegalArgumentException implements ClanManagerException{
    public IllegalChunkToClanException(String message){
        super(message);
    }
}
