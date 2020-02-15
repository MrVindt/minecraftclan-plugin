package ru.zendal.clanminecraft.command.handler;

import com.google.inject.Inject;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.сlan.ClanManager;
import ru.zendal.clanminecraft.сlan.exception.IllegalChunkNearbyException;
import ru.zendal.clanminecraft.сlan.exception.IllegalChunkToClanException;
import ru.zendal.clanminecraft.сlan.exception.IllegalPlayerNotMemberClanException;
import ru.zendal.clanminecraft.сlan.exception.IllegalValidAreaClanException;

public class ClanAddChunkCommand implements CommandHandler {

    private final ClanManager clanManager;
    private final PluginLocalization pluginLocalization;

    @Inject
    public ClanAddChunkCommand(ClanManager clanManager, PluginLocalization pluginLocalization) {
        this.clanManager = clanManager;
        this.pluginLocalization = pluginLocalization;
    }

    @Override
    public boolean canProcess(String[] args) {
        if (args[0].equalsIgnoreCase("add"))
            return true;
        return false;
    }

    @Override
    public boolean run(CommandSender commandSender, String[] args) {
        if (args.length !=2 || !args[1].equalsIgnoreCase("chunk")) {
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkError());
            return false;
        }
        var player = commandSender instanceof Player ? ((Player) commandSender) : null;
        var chunk = player.getLocation().getChunk();
        try {
            clanManager.addChunk(chunk, player);
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkSuccess());
        } catch (IllegalChunkNearbyException e) {
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkErrorNoChunkNearby());
        } catch (IllegalPlayerNotMemberClanException e) {
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkErrorPlayerNotMemberClan());
        } catch (IllegalChunkToClanException e) {
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkErrorChunkToClan());
        } catch (IllegalValidAreaClanException e) {
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanAddChunkErrorValidAreaClan());
        }
        return true;
    }
}
