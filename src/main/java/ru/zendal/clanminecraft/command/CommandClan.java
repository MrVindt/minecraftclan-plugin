package ru.zendal.clanminecraft.command;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.сlan.ClanManager;


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
        if (args.length == 2 && args[1].length() >= 3 && args[1].length() <= 20) {
            if (command.getName().equalsIgnoreCase("clan") && args[0].equalsIgnoreCase("create")) {
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateSuccess(args[1]));
                var player = sender instanceof Player ? ((Player) sender) : null;
                var chunk = player.getLocation().getChunk();

                clanManager.create(args[1], chunk, player);
                return true;
            }
        }
        sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateError());
        sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateNameClanError());
        return false;
    }
}
