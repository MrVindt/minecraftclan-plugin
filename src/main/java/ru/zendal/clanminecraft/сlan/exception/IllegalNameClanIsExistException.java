package ru.zendal.clanminecraft.сlan.exception;

public class IllegalNameClanIsExistException extends IllegalArgumentException implements ClanManagerException{
    public IllegalNameClanIsExistException(String message){
        super(message);
    }
}
