package de.mcmdev.troll.trolls.api;

import de.mcmdev.troll.TrollPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface Troll {

    public void execute(TrollPlugin plugin, Player sender, Player target);

    public Material getMaterial();

    public String getName();

    public String[] getDescription();

}
