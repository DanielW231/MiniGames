package me.danielw231.minigames.managers;

import java.util.Map.Entry;
import java.util.UUID;

import me.danielw231.minigames.MiniGames;
import me.danielw231.minigames.managers.Arena;
import me.danielw231.minigames.managers.GameState;
import me.danielw231.minigames.util.F;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


import me.danielw231.minigames.kits.Kit;
import me.danielw231.minigames.util.H;

public class Game extends BukkitRunnable
{

    private final Arena arena;
    private int time;

    public Game(Arena arena)
    {
        this.arena = arena;
        this.time = 0;
    }

    public void start()
    {
        arena.setState(GameState.LIVE);
        arena.arenaBroadcast("Arena", F.AQUA + "The game has started!");

        for (Entry<UUID, Kit> entry : arena.getKits().entrySet())
        {
            Player player = Bukkit.getPlayer(entry.getKey());
            Kit kit = entry.getValue();
            player.setHealth(20);
            player.setFoodLevel(20);

            H.clearInventoryAndEffects(player);

            kit.onStart(player);

            player.teleport(arena.getLocation());

            for (int i = 0; i < kit.getItems().length; i++)
            {
                player.getInventory().addItem(kit.getItems()[i]);
            }
        }
        this.runTaskTimer(MiniGames.getInstance(), 0L, 20L);
    }

    @Override
    public void run()
    {
        for (Entry<UUID, Kit> entry : arena.getKits().entrySet())
        {
            entry.getValue().update(Bukkit.getPlayer(entry.getKey()), time);
        }
        time++;
    }
}