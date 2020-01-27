package me.danielw231.minigames.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.UUID;

public class Manager
{

    private static Manager _manager;
    private ArrayList<Arena> _arenas;
    public final static Location SPAWN_POINT = new Location(Bukkit.getWorld("world"), 240.0, 68.0, 280.0);

    private Manager()
    {
        this._arenas = new ArrayList<Arena>();
    }

    public static void setup()
    {
            new Arena(1, new Location(Bukkit.getWorld("world"), 0, 10.0, 0));
    }

    public static Manager getInstance()
    {
        if (_manager == null)
        {
            _manager = new Manager();
        }
        return _manager;
    }

    public void addArena(Arena arena)
    {
        _arenas.add(arena);
    }

    public void removeArena(Arena arena)
    {
        _arenas.remove(arena);
    }

    public Arena getArena(int id)
    {
        for (int i = 0; i < _arenas.size(); i++)
        {
            final Arena arena = _arenas.get(i);

            if (arena.getId() == id)
            {
                return arena;
            }
        }
        return null;
    }

    public boolean isArena(int id)
    {
        return getArena(id) != null;
    }

    public Arena getArena(UUID uuid)
    {
        for (int i = 0; i < _arenas.size(); i++)
        {
            final Arena arena = _arenas.get(i);

            if (arena.contains(uuid))
            {
                return arena;
            }
        }
        return null;
    }
}