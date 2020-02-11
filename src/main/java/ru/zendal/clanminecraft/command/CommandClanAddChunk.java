package ru.zendal.clanminecraft.command;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.сlan.ClanManager;

public class CommandClanAddChunk implements CommandExecutor {

    private final ClanManager clanManager;
    private final PluginLocalization pluginLocalization;

    @Inject
    public CommandClanAddChunk(ClanManager clanManager, PluginLocalization pluginLocalization){
        this.clanManager = clanManager;
        this.pluginLocalization = pluginLocalization;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("clan.add.chunk")){
            var player = sender instanceof Player ? ((Player) sender) : null;
            var chunk = player.getLocation().getChunk();
            clanManager.addChunk(chunk, player);
            return true;
        }
        return false;
    }
}
