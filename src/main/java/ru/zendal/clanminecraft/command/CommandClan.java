package ru.zendal.clanminecraft.command;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.сlan.ClanManager;
import ru.zendal.clanminecraft.сlan.exception.IllegalChunkClanException;
import ru.zendal.clanminecraft.сlan.exception.IllegalNameClanException;
import ru.zendal.clanminecraft.сlan.exception.IllegalNameClanIsExistException;


public class CommandClan implements CommandExecutor {

    private final ClanManager clanManager;
    private final PluginLocalization pluginLocalization;


    @Inject
    public CommandClan(PluginLocalization pluginLocalization, ClanManager clanManager) {
        this.pluginLocalization = pluginLocalization;
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2 && command.getName().equalsIgnoreCase("clan") && args[0].equalsIgnoreCase("create")) {
            var player = sender instanceof Player ? ((Player) sender) : null;
            var chunk = player.getLocation().getChunk();

            try {
                clanManager.create(args[1], chunk, player);
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateSuccess(args[1]));
            } catch (IllegalNameClanException e) {
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateNameClanError());
            } catch (IllegalNameClanIsExistException e) {
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateNameClanIsExist(args[1]));
            } catch (IllegalChunkClanException e) {
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateErrorChunkIsBusy());
            }
            return true;

        }
        sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateError());
        sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateNameClanError());
        return false;
    }
}
