package me.danielw231.minigames.managers;

import me.danielw231.minigames.MiniGames;

import me.danielw231.minigames.util.F;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable
{
    private int _time;
    private final Arena _arena;

    public Countdown(Arena arena)
    {
        this._arena = arena;
        this._time = 0;
    }

    public void start (int time)
    {
        _arena.setState(GameState.COUNTDOWN);
        this._time = time;
        this.runTaskTimer(MiniGames.getInstance(), 0L, 20L);
    }

    @Override
    public void run()
    {
        if (_time == 0)
        {
            cancel();
            _arena.getGame().start();
            return;
        }

        if (_time % 15 == 0 || _time <= 10)
        {
            if (_time != 1)
            {
                _arena.arenaBroadcast("Arena", "Game will start in " + _time + " seconds.");
            }

            else
                {
                _arena.arenaBroadcast("Arena", "Game will start in " + _time + " second.");
            }
        }

        if (_arena.getPlayers().size() < _arena.getRequiredPlayers())
        {
            cancel();
            _arena.setState(GameState.RECRUITING);
            _arena.arenaBroadcast("Countdown", F.boldRed + "Too few players - countdown stopped.");
            return;
        }
        _time --;
    }

    public boolean isRunning()
    {
        return _arena.getState() == GameState.COUNTDOWN;
    }

    public int getTime()
    {
        return _time;
    }

    public void setTime(int time)
    {
        this._time = time;
    }

    public Arena getArena()
    {
        return _arena;
    }
}
