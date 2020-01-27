package me.danielw231.minigames.kits;

import java.util.UUID;

import me.danielw231.minigames.util.F;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import me.danielw231.minigames.util.H;

public class Barbarian extends Kit
{
    public Barbarian(UUID uuid) {
        super(uuid, KitType.BARBARIAN, H.createItem(F.boldBlue + "Barbarian's Sword", Material.STONE_SWORD));
    }

    @Override
    public void onStart(Player player)
    {

    }

    @Override
    public void update(Player player, int time)
    {

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event)
    {
        if (!(event.getEntity() instanceof Player))
        {
            return;
        }

        Player player = (Player) event.getEntity();

        if (!(uuid == player.getUniqueId()))
        {
            return;
        }

        event.setDamage(event.getDamage() - 1.5);
    }
}