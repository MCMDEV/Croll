package de.mcmdev.troll.trolls.api;

import de.mcmdev.troll.TrollPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class ToggleableTroll implements Troll {

    private String name;
    private String[] description;
    private Material material;
    TrollPlugin plugin;

    List<Player> enabled = new ArrayList<>();

    public ToggleableTroll(String name, Material material, TrollPlugin plugin, String... description) {
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

    public abstract void runTick(TrollPlugin plugin, Player target);

    @Override
    public void execute(TrollPlugin plugin, Player sender, Player target) {
        if(enabled.contains(target))    {
            disable(plugin, sender, target);
        }   else    {
            enable(plugin, sender, target);
        }
    }

    public void enable(TrollPlugin plugin, Player sender, Player target)    {
        sender.sendMessage(TrollPlugin.PREFIX + "§7Enabled " + getName() + " §7on " + target.getDisplayName());
        enabled.add(target);
    };

    public void disable(TrollPlugin plugin, Player sender, Player target)    {
        sender.sendMessage(TrollPlugin.PREFIX + "§7Disabled " + getName() + " §7on " + target.getDisplayName());
        enabled.remove(target);
    };

    public void enable(Player target)   {
        enabled.add(target);
    }

    public void disable(Player target)  {
        enabled.remove(target);
    }
}
