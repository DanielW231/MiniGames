package me.danielw231.minigames.listeners;

import me.danielw231.minigames.util.F;
import me.danielw231.minigames.util.H;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        H.teleportToSpawn(player);

        if (player.getDisplayName().equalsIgnoreCase("DanielW231"))
        {
            event.setJoinMessage(F.info("Join", player.getDisplayName() + " is ready to fuck some shit up."));
        }

        else
            {
            event.setJoinMessage(F.info("Join", player.getDisplayName()));
        }
    }
}