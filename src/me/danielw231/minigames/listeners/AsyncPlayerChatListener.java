package me.danielw231.minigames.listeners;

import me.danielw231.minigames.util.F;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener
{
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        event.setCancelled(true);

        Bukkit.broadcastMessage(F.playerMessage(event.getPlayer().getDisplayName(), event.getMessage()));
    }
}
