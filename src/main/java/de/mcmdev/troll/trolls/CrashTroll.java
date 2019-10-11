package de.mcmdev.troll.trolls;

import de.mcmdev.troll.TrollPlugin;
import de.mcmdev.troll.trolls.api.SimpleTroll;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class CrashTroll extends SimpleTroll {

    public CrashTroll(TrollPlugin trollPlugin) {
        super("§cCrash", Material.RED_STAINED_GLASS, trollPlugin, "§cCrashes the players game");
    }

    @Override
    public void execute(TrollPlugin plugin, Player sender, Player target) {
        target.spawnParticle(Particle.FLAME, target.getLocation(), Integer.MAX_VALUE);
    }
}
