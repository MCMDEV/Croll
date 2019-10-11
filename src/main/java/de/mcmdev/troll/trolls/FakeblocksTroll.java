package de.mcmdev.troll.trolls;

import de.mcmdev.troll.TrollPlugin;
import de.mcmdev.troll.trolls.api.SimpleTroll;
import de.mcmdev.troll.trolls.api.ToggleableTroll;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class FakeblocksTroll extends ToggleableTroll {

    public FakeblocksTroll(TrollPlugin trollPlugin) {
        super("§aFakeblocks", Material.BEDROCK, trollPlugin, "§aCages the player in fake glass");
    }

    @Override
    public void runTick(TrollPlugin plugin, Player target) {
        target.sendBlockChange(target.getLocation().add(0, -1, 0), Material.GLASS.createBlockData());
        target.sendBlockChange(target.getLocation().add(1, 0, 0), Material.GLASS.createBlockData());
        target.sendBlockChange(target.getLocation().add(-1, 0, 0), Material.GLASS.createBlockData());
        target.sendBlockChange(target.getLocation().add(0, 0, 1), Material.GLASS.createBlockData());
        target.sendBlockChange(target.getLocation().add(0, 0, -1), Material.GLASS.createBlockData());
        target.sendBlockChange(target.getLocation().add(0, 2, 0), Material.GLASS.createBlockData());
    }
}
