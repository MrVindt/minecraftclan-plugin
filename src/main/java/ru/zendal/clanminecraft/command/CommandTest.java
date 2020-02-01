package ru.zendal.clanminecraft.command;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zendal.clanminecraft.сlan.Clan;

public class CommandTest implements CommandExecutor {

    @Inject
    public CommandTest(JavaPlugin jP){
        //jP.getCommand(
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2 && args[1].length() >=3 && args[1].length() <= 20){
            if (command.getName().equalsIgnoreCase("clan") && args[0].equalsIgnoreCase("create")) {
                sender.sendMessage("Clan create successfull");
                Clan clan = Clan.builder().name(args[1]).build();
                return true;
            }
        }
        sender.sendMessage("Clan create with command: clan create NameClan");
        sender.sendMessage("NameClan must be between 3 and 20 characters");
        //System.out.println(clan.getName());
    return false;
    }
}
