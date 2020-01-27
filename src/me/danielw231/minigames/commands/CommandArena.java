package me.danielw231.minigames.commands;

import me.danielw231.minigames.util.F;
import me.danielw231.minigames.util.H;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danielw231.minigames.managers.Manager;
import me.danielw231.minigames.managers.Arena;
import me.danielw231.minigames.managers.GameState;

public class CommandArena implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {

        if (!(sender instanceof Player))
        {
            sender.sendMessage("Only a player may execute this command!");
            return true;
        }

        final Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("arena"))
        {

            if (args.length < 1)
            {
                String[] message = {F.WHITE + "/ arena join [Arena ID]", F.WHITE + "/ arena leave", F.WHITE + "/ arena id"};
                H.sendPluginMessage(player, "Arena", "You must include a valid sub command!");
                player.sendMessage(message);
                return true;
            }

            final String subCommand = args[0];

            if (subCommand.equalsIgnoreCase("join"))
            {
                if (args.length != 2)
                {
                    H.sendPluginMessage(player, "Arena", "You must include an arena ID.");
                    return true;
                }

                final String idText = args[1];
                int id;

                try
                {
                    id = Integer.parseInt(idText);
                }
                catch (NumberFormatException e)
                {
                    H.sendPluginMessage(player, "Arena", "That is not a valid number.");
                    return true;
                }

                if (!Manager.getInstance().isArena(id))
                {
                    H.sendPluginMessage(player, "Arena", "That is not a valid arena. ");
                    return true;
                }

                final Arena arena = Manager.getInstance().getArena(id);
                final Arena current = Manager.getInstance().getArena(player.getUniqueId());

                if (current != null)
                {
                    Bukkit.dispatchCommand(player, "arena leave");
                }

                if (arena.getState() == GameState.LIVE)
                {
                    H.sendPluginMessage(player, "Arena", "You may not join this arena as it has already started.");
                    return true;
                }

                arena.addPlayer(player.getUniqueId());
                H.sendPluginMessage(player, "Arena", F.boldGreen + "You have joined arena " + arena.getId() + ".");
                return true;

            }

            else if (subCommand.equalsIgnoreCase("leave"))
            {
                final Arena arena = Manager.getInstance().getArena(player.getUniqueId());

                if (arena != null)
                {
                    arena.removePlayer(player.getUniqueId());
                    H.sendPluginMessage(player, "Arena", "You have left arena " + arena.getId() + ".");
                }

                else {
                    H.sendPluginMessage(player, "Arena", "You are not currently in an arena.");
                }
                return true;
            }

            else if (subCommand.equalsIgnoreCase("id"))
            {

                final Arena arena = Manager.getInstance().getArena(player.getUniqueId());

                if (arena != null)
                {
                    H.sendPluginMessage(player, "Arena", "You are in arena " + arena.getId() + ".");

                }

                else {
                    H.sendPluginMessage(player, "Arena", "You are not currently in an arena.");
                }
                return true;
            }

            else {
                H.sendPluginMessage(player, "Arena", "Invalid subcommand.");
                return true;
            }
        }
        return true;
    }
}