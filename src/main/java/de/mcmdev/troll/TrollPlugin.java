package de.mcmdev.troll;

import de.mcmdev.troll.commands.CommandTroll;
import de.mcmdev.troll.trolls.api.Ticker;
import fr.minuskube.inv.InventoryManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TrollPlugin extends JavaPlugin {

    public static String PREFIX = "§c[§f§lTroll§c] ";

    private InventoryManager inventoryManager;
    private TrollManager trollManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("troll").setExecutor(new CommandTroll(this));

        inventoryManager = new InventoryManager(this);
        inventoryManager.init();

        trollManager = new TrollManager(this);

        new Ticker(this).runTaskTimerAsynchronously(this, 20, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public TrollManager getTrollManager() {
        return trollManager;
    }
}
