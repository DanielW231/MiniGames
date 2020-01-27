package me.danielw231.minigames.managers;


import me.danielw231.minigames.kits.Kit;
import me.danielw231.minigames.util.F;
import me.danielw231.minigames.util.H;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Arena
{
    private final int _id;
    private ArrayList<UUID> _players;
    private HashMap<UUID, Kit> _kits;
    private GameState _state;
    private final Location _location;
    private final Countdown _countdown;
    private final Game _game;
    private final int _requiredPlayers;
    private final String _prefix;

    public Arena(int id, Location location)
    {
        this._id = id;
        this._players = new ArrayList<UUID>();
        this._kits = new HashMap<UUID, Kit>();
        this._state = GameState.RECRUITING;
        this._location = location;
        this._countdown = new Countdown(this);
        this._game = new Game(this);
        this._requiredPlayers = 2;
        this._prefix = F.GRAY + "[" + F.boldGreen + "Arena " + _id + F.GRAY + "] " + F.RESET;

        Manager.getInstance().addArena(this);
    }

    public void reset()
    {
        this._players.clear();
        this._kits.clear();
        this._state = GameState.RECRUITING;
    }

    public void addPlayer(UUID uuid)
    {
        _players.add(uuid);

        if (!_countdown.isRunning() && _players.size() >= _requiredPlayers)
        {
            _countdown.start(20);
        }
    }

    public void removePlayer(UUID uuid)
    {
        _players.remove(uuid);

        final Kit kit = getKit(uuid);
        if (kit != null)
        {
            kit.remove();
        }

        _kits.remove(kit);


        if (_state == GameState.LIVE && _players.size() == 1)
        {
            _game.cancel();

            Player winner = Bukkit.getPlayer(_players.get(0));


            H.broadcast(F.boldRed + winner.getDisplayName() + " has won arena " + _id + "!");
            removePlayer(winner.getUniqueId());
            reset();
        }
    }

    public void arenaBroadcast(String module, String message)
    {
        for (int i = 0; i < _players.size(); i++)
        {
            Bukkit.getPlayer(_players.get(i)).sendMessage(F.info(module, message));
        }
    }

    public Kit getKit(UUID uuid)
    {
        return _kits.get(uuid);
    }

    public void setKit(UUID uuid, Kit kit)
    {
        _kits.put(uuid, kit);
    }

    public boolean contains(UUID uuid)
    {
        return _players.contains(uuid);
    }

    public ArrayList<UUID> getPlayers()
    {
        return _players;
    }

    public void setPlayers(ArrayList<UUID> players)
    {
        this._players = players;
    }

    public HashMap<UUID, Kit> getKits()
    {
        return _kits;
    }

    public void setKits(HashMap<UUID, Kit> kits)
    {
        this._kits = kits;
    }

    public GameState getState()
    {
        return _state;
    }

    public void setState(GameState state)
    {
        this._state = state;
    }

    public Location getLocation()
    {
        return _location;
    }

    public int getId()
    {
        return _id;
    }

    public Countdown getCountdown()
    {
        return _countdown;
    }

    public Game getGame()
    {
        return _game;
    }

    public int getRequiredPlayers()
    {
        return _requiredPlayers;
    }

    public String getPrefix()
    {
        return _prefix;
    }
}
