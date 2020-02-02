package ru.zendal.clanminecraft.command;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.сlan.Clan;

public class CommandTest implements CommandExecutor {


    private final PluginLocalization pluginLocalization;


    @Inject
    public CommandTest(PluginLocalization pluginLocalization) {
        this.pluginLocalization = pluginLocalization;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2 && args[1].length() >= 3 && args[1].length() <= 20) {
            if (command.getName().equalsIgnoreCase("clan") && args[0].equalsIgnoreCase("create")) {
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateSuccess());
                Clan clan = Clan.builder().name(args[1]).build();
                var player = sender instanceof Player ? ((Player) sender) : null;
                player.getLocation().getChunk();
                return true;
            }
        }
        sender.sendMessage("Clan create with command: clan create NameClan");
        sender.sendMessage("NameClan must be between 3 and 20 characters");
        //System.out.println(clan.getName());
        return false;
    }
}
