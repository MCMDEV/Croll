package de.mcmdev.troll.commands;

import de.mcmdev.troll.TrollPlugin;
import de.mcmdev.troll.inventories.PlayersInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTroll implements CommandExecutor {

    private TrollPlugin troll;

    public CommandTroll(TrollPlugin troll) {
        this.troll = troll;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)    {
            Player player = (Player) sender;
            if(player.hasPermission("troll"))   {
                if(args.length == 0)    {
                    new PlayersInventory(troll, player);
                }   else if(args.length == 1)    {
                    if(args[0].equalsIgnoreCase("reload"))  {
                        troll.getTrollManager().load();
                        player.sendMessage(TrollPlugin.PREFIX + "§aReloading Trolls");
                    }
                }
            }
        }
        return false;
    }
}
