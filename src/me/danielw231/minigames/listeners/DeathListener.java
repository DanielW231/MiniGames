package me.danielw231.minigames.listeners;

import me.danielw231.minigames.managers.Arena;
import me.danielw231.minigames.managers.Manager;
import me.danielw231.minigames.util.F;
import me.danielw231.minigames.util.H;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener
{
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        event.setDeathMessage(null);

        Arena arena = Manager.getInstance().getArena(player.getUniqueId());
        if (arena != null)
        {
            arena.removePlayer(player.getUniqueId());

            if (!(player.getKiller() == null))
            {
                Player killer = player.getKiller();
                arena.arenaBroadcast("Arena", F.RED + player.getName() + " was killed by " + killer.getName() + " using " + killer.getItemInHand() + ".");
            }

            else
                {
                arena.arenaBroadcast("Arena",  player.getName() + " died.");
            }

            event.getDrops().clear();
            H.teleportToSpawn(player);
            H.clearInventoryAndEffects(player);
        }
    }
}
