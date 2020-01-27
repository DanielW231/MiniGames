package me.danielw231.minigames.kits;

import me.danielw231.minigames.MiniGames;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class Kit implements Listener
{
    protected final UUID uuid;
    protected final KitType type;
    protected final ItemStack[] items;

    public static Kit getKit(UUID uuid, KitType type)
    {
        switch (type)
        {
            case ARCHER:
                return new Archer(uuid);

            case BARBARIAN:
                return new Barbarian(uuid);

            default:
                return null;
        }
    }

    public Kit(UUID uuid, KitType type, ItemStack... items)
    {
        this.uuid = uuid;
        this.type = type;
        this.items = items;

        Bukkit.getPluginManager().registerEvents(this, MiniGames.getInstance());
    }

    public void remove()
    {
        HandlerList.unregisterAll(this);
    }

    public abstract void onStart(Player player);
    public abstract void update(Player player, int time);

    public UUID getUUID()
    {
        return uuid;
    }

    public KitType getType()
    {
        return type;
    }

    public ItemStack[] getItems()
    {
        return items;
    }
}
