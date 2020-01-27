package me.danielw231.minigames.commands;

import me.danielw231.minigames.util.H;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danielw231.minigames.managers.Manager;
import me.danielw231.minigames.managers.Arena;
import me.danielw231.minigames.managers.GameState;
import me.danielw231.minigames.kits.Kit;
import me.danielw231.minigames.kits.KitType;

public class CommandKit implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            Player player = (Player) sender;
            H.sendPluginMessage(player, "Kit", "Only a player can use this command!");
            return true;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("kit"))
        {
            if (args.length != 1)
            {
                H.sendPluginMessage(player, "Kit", "You must include a kit to select.");
                return true;
            }

            final Arena arena = Manager.getInstance().getArena(player.getUniqueId());

            if (arena == null)
            {
                H.sendPluginMessage(player, "Kit", "You must be in an arena to choose a kit.");
                return true;
            }

            if (!KitType.isKit(args[0]))
            {
                H.sendPluginMessage(player, "Kit", "That is not a valid kit!");
                return true;
            }

            final KitType type = KitType.getKit(args[0]);

            if (arena.getState() == GameState.LIVE)
            {
                H.sendPluginMessage(player, "Kit", "You may not select a kit after the game has started!");
                return true;
            }

            final Kit current = arena.getKit(player.getUniqueId());

            if (current != null)
            {
                current.remove();
            }

            final Kit kit = Kit.getKit(player.getUniqueId(), type);
            arena.setKit(player.getUniqueId(), kit);
            H.sendPluginMessage(player, "Kit", "You have chosen the " + kit.getType().getName().toLowerCase() + " kit.");
            return true;
        }
        return true;
    }
}