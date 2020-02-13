package ru.zendal.clanminecraft.сlan.exception;

public class IllegalPlayerNotMemberClanException extends IllegalArgumentException implements ClanManagerException{
    public IllegalPlayerNotMemberClanException(String message){
        super(message);
    }
}
