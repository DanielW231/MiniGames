package me.danielw231.minigames.kits;

import java.util.UUID;

import me.danielw231.minigames.util.F;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.danielw231.minigames.util.H;

public class Archer extends Kit
{
    public Archer(UUID uuid)
    {
        super(uuid, KitType.ARCHER, H.createItem(F.boldGreen + "Archer's Bow", Material.BOW), H.createItem(F.boldWhite + "Fletched Arrow", Material.ARROW));
    }

    @Override
    public void onStart(Player player)
    {

    }

    @Override
    public void update(Player player, int time)
    {

    }
}