package me.danielw231.minigames.listeners;

import me.danielw231.minigames.managers.Arena;
import me.danielw231.minigames.managers.Manager;
import me.danielw231.minigames.util.F;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener
{
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();

        Arena arena = Manager.getInstance().getArena(player.getUniqueId());

        if (arena != null)
        {
            arena.removePlayer(player.getUniqueId());
            arena.arenaBroadcast("Arena", player.getName() + " left the server, so has been removed from the arena.");
        }
        else
            {
            event.setQuitMessage(F.info("Quit", player.getDisplayName()));
        }
    }
}
