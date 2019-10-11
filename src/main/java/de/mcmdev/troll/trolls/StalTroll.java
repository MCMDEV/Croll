package de.mcmdev.troll.trolls;

import de.mcmdev.troll.TrollPlugin;
import de.mcmdev.troll.trolls.api.SimpleTroll;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class StalTroll extends SimpleTroll {

    public StalTroll(TrollPlugin trollPlugin) {
        super("§8Stal", Material.MUSIC_DISC_STAL, trollPlugin, "§7Plays Music Disc Stal to the player");
    }

    @Override
    public void execute(TrollPlugin plugin, Player sender, Player target) {
        target.playSound(target.getLocation(), Sound.MUSIC_DISC_STAL, 10, 1);
    }
}
