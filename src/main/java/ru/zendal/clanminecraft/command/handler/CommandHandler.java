package ru.zendal.clanminecraft.command.handler;

import org.bukkit.command.CommandSender;

public interface CommandHandler {

    boolean canProcess(String[] args);


    boolean run(CommandSender commandSender, String[] args);
}
