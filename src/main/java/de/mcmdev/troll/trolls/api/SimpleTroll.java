package de.mcmdev.troll.trolls.api;

import de.mcmdev.troll.TrollPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public abstract class SimpleTroll implements Troll {

    private String name;
    private String[] description;
    private Material material;
    private TrollPlugin plugin;

    public SimpleTroll(String name, Material material, TrollPlugin plugin, String... description) {
        this.name = name;
        this.description = description;
        this.material = material;
        this.plugin = plugin;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public String[] getDescription() {
        return description;
    }

    public abstract void execute(TrollPlugin plugin, Player sender, Player target);
}
