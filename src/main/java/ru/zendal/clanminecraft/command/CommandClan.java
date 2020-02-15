package ru.zendal.clanminecraft.command;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.zendal.clanminecraft.command.handler.CommandHandler;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.сlan.ClanManager;

import java.util.List;


public class CommandClan implements CommandExecutor {

    private final List<CommandHandler> handlers;


    @Inject
    public CommandClan(@Named("ClanHandlers") List<CommandHandler> commandHandlerList) {
        this.handlers = commandHandlerList;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (CommandHandler handler : this.handlers) {
            if (handler.canProcess(args)) {
                return handler.run(sender, args);
            }
        }
        return false;
    }
}