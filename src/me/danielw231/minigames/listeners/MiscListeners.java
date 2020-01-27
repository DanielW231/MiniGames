package me.danielw231.minigames.listeners;

import me.danielw231.minigames.util.F;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class MiscListeners implements Listener
{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE)
        {
            event.setCancelled(true);
            player.sendMessage(F.info("Game", "You are not allowed to break blocks!"));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE)
        {
            event.setCancelled(true);
            player.sendMessage(F.info("Game", "You are not allowed to place blocks!"));
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event)
    {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE)
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event)
    {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE)
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event)
    {
        event.setCancelled(true);
    }
}
