package ru.zendal.clanminecraft.command;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.сlan.ClanManager;
import ru.zendal.clanminecraft.сlan.exception.IllegalChunkNearbyException;
import ru.zendal.clanminecraft.сlan.exception.IllegalChunkToClanException;
import ru.zendal.clanminecraft.сlan.exception.IllegalPlayerNotMemberClanException;
import ru.zendal.clanminecraft.сlan.exception.IllegalValidAreaClanException;

public class CommandClanAddChunk implements CommandExecutor {

    private final ClanManager clanManager;
    private final PluginLocalization pluginLocalization;

    @Inject
    public CommandClanAddChunk(ClanManager clanManager, PluginLocalization pluginLocalization) {
        this.clanManager = clanManager;
        this.pluginLocalization = pluginLocalization;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("clan.add.chunk")) {
            var player = sender instanceof Player ? ((Player) sender) : null;
            var chunk = player.getLocation().getChunk();
            try {
                clanManager.addChunk(chunk, player);
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkSuccess());
            } catch (IllegalChunkNearbyException e) {
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkErrorNoChunkNearby());
            } catch (IllegalPlayerNotMemberClanException e) {
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkErrorPlayerNotMemberClan());
            } catch (IllegalChunkToClanException e) {
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkErrorChunkToClan());
            } catch (IllegalValidAreaClanException e) {
                sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkErrorValidAreaClan());
            }
            return true;
        }
        sender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkError());
        return false;
    }
}
