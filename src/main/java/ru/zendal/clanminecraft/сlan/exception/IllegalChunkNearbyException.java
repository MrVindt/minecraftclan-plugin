package ru.zendal.clanminecraft.сlan.exception;

public class IllegalChunkNearbyException extends IllegalArgumentException implements ClanManagerException {
    public IllegalChunkNearbyException(String message) {
        super(message);
    }
}
