package ru.zendal.clanminecraft.command.handler;

import com.google.inject.Inject;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.сlan.ClanManager;
import ru.zendal.clanminecraft.сlan.exception.IllegalChunkClanException;
import ru.zendal.clanminecraft.сlan.exception.IllegalNameClanException;
import ru.zendal.clanminecraft.сlan.exception.IllegalNameClanIsExistException;
import ru.zendal.clanminecraft.сlan.exception.IllegalPlayerAdminAnotherClanException;

public class ClanCreateCommandHandler implements CommandHandler {


    private final ClanManager clanManager;
    private final PluginLocalization pluginLocalization;

    @Inject
    public ClanCreateCommandHandler(PluginLocalization pluginLocalization, ClanManager clanManager) {
        this.clanManager = clanManager;
        this.pluginLocalization = pluginLocalization;
    }

    @Override
    public boolean canProcess(String[] args) {
        return args.length == 2;
    }

    @Override
    public boolean run(CommandSender commandSender, String[] args) {
        if (args.length != 2) {
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateError());
            return false;
        }
        var player = commandSender instanceof Player ? ((Player) commandSender) : null;
        var chunk = player.getLocation().getChunk();
        try {
            clanManager.create(args[1], chunk, player);
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateSuccess(args[1]));
        } catch (IllegalNameClanException e) {
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateNameClanError());
        } catch (IllegalNameClanIsExistException e) {
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateNameClanIsExist(args[1]));
        } catch (IllegalChunkClanException e) {
            commandSender.sendMessage(pluginLocalization.getCommandLocale().getOnClanCreateErrorChunkIsBusy());
        } catch (IllegalPlayerAdminAnotherClanException e) {
            commandSender.sendMessage(
                    pluginLocalization.getCommandLocale().getOnClanCreateErrorPlayerIsAdminAnotherClan(e.getClan().getName())
            );
        }

        return true;
    }
}
