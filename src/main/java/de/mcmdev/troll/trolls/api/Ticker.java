package de.mcmdev.troll.trolls.api;

import de.mcmdev.troll.TrollPlugin;
import de.mcmdev.troll.trolls.api.ToggleableTroll;
import de.mcmdev.troll.trolls.api.Troll;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Ticker extends BukkitRunnable {

    private TrollPlugin trollPlugin;

    public Ticker(TrollPlugin trollPlugin) {
        this.trollPlugin = trollPlugin;
    }

    @Override
    public void run() {
        for(Troll troll : trollPlugin.getTrollManager().getRegistry())  {
            if(troll instanceof ToggleableTroll)    {
                ToggleableTroll toggleableTroll =  (ToggleableTroll) troll;
                for(Player player : toggleableTroll.enabled)    {
                    toggleableTroll.runTick(toggleableTroll.plugin, player);
                }
            }
        }
    }
}
