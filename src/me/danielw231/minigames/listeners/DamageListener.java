package me.danielw231.minigames.listeners;

import me.danielw231.minigames.managers.Arena;
import me.danielw231.minigames.managers.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener
{
    @EventHandler
    public void onFallEvent(EntityDamageEvent event)
    {
        if (!(event.getEntity() instanceof Player))
            return;

        if (!(event.getCause().equals(EntityDamageEvent.DamageCause.FALL)))
            return;

        Arena arena = Manager.getInstance().getArena(event.getEntity().getUniqueId());

        if (arena == null)
            return;

        event.setCancelled(true);
    }
}
