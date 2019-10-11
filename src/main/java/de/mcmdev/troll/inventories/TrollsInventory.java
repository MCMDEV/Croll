package de.mcmdev.troll.inventories;

import de.mcmdev.troll.TrollPlugin;
import de.mcmdev.troll.trolls.api.SimpleTroll;
import de.mcmdev.troll.trolls.api.Troll;
import de.mcmdev.troll.utils.ItemStackBuilder;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class TrollsInventory {

    private TrollPlugin trollPlugin;
    private Player sender;
    private Player target;

    private SmartInventory inventory;

    public TrollsInventory(TrollPlugin trollPlugin, Player sender, Player target) {
        this.trollPlugin = trollPlugin;
        this.sender = sender;
        this.target = target;

        load();
    }

    public void load()  {
        inventory = SmartInventory.builder()
                .id("troll_target")
                .manager(trollPlugin.getInventoryManager())
                .title("§c➤ Select a Troll")
                .size(6, 9)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        for(Troll troll : trollPlugin.getTrollManager().getRegistry())  {
                            contents.add(ClickableItem.of(ItemStackBuilder.create(troll.getMaterial()).withName(troll.getName()).withLore(troll.getDescription()).build(), new Consumer<InventoryClickEvent>() {
                                @Override
                                public void accept(InventoryClickEvent event) {
                                    if(troll instanceof SimpleTroll)    {
                                        sender.sendMessage(TrollPlugin.PREFIX + "§7Executed " + troll.getName() + " §7on " + target.getDisplayName());
                                    }
                                    troll.execute(trollPlugin, sender, target);
                                }
                            }));
                        }
                    }

                    @Override
                    public void update(Player player, InventoryContents contents) {

                    }
                })
                .build();
        inventory.open(sender);
    }
}
