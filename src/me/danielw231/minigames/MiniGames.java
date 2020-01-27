package me.danielw231.minigames;

import me.danielw231.minigames.commands.CommandArena;
import me.danielw231.minigames.commands.CommandKit;
import me.danielw231.minigames.listeners.*;
import me.danielw231.minigames.managers.Manager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MiniGames extends JavaPlugin
{
    private static MiniGames _instance;

    public static MiniGames getInstance()
    {
        return _instance;
    }

    @Override
    public void onEnable()
    {
        _instance = this;

        Manager.setup();

        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable()
    {

    }

    private void registerCommands()
    {
        getCommand("arena").setExecutor(new CommandArena());
        getCommand("kit").setExecutor(new CommandKit());
    }

    private void registerListeners()
    {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new DeathListener(), this);
        pm.registerEvents(new MiscListeners(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new LeaveListener(), this);
        pm.registerEvents(new DamageListener(), this);
        pm.registerEvents(new AsyncPlayerChatListener(), this);
    }
}