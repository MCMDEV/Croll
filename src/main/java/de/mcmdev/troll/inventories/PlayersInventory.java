package de.mcmdev.troll.inventories;

import de.mcmdev.troll.TrollPlugin;
import de.mcmdev.troll.utils.ItemStackBuilder;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class PlayersInventory {

    private TrollPlugin troll;
    private Player sender;

    private SmartInventory inventory;

    public PlayersInventory(TrollPlugin troll, Player sender) {
        this.troll = troll;
        this.sender = sender;

        load();
    }

    public void load()  {
        inventory = SmartInventory.builder()
                .id("troll_player")
                .manager(troll.getInventoryManager())
                .title("§9➤ Select a Player")
                .size(6, 9)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {
                        Pagination pagination = contents.pagination();

                        List<ClickableItem> clickables = new ArrayList<>();
                        Collection<? extends Player> players = troll.getServer().getOnlinePlayers();

                        for(Player all : players)   {
                            ClickableItem item = ClickableItem.of(ItemStackBuilder.create(Material.PLAYER_HEAD).setSkullOwner(all).withName("§a" + all.getDisplayName()).build(), new Consumer<InventoryClickEvent>() {
                                @Override
                                public void accept(InventoryClickEvent event) {
                                    new TrollsInventory(troll, sender, all);
                                }
                            });

                            clickables.add(item);
                        }

                        pagination.setItems(clickables.toArray(new ClickableItem[] {}));
                        pagination.setItemsPerPage(45);

                        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 0, 0));

                        contents.set(5, 3, ClickableItem.of(ItemStackBuilder.create(Material.ARROW).withName("§eLast Page").build(),
                                e -> inventory.open(player, pagination.previous().getPage())));
                        contents.set(5, 5, ClickableItem.of(ItemStackBuilder.create(Material.ARROW).withName("§eNext Page").build(),
                                e -> inventory.open(player, pagination.next().getPage())));
                    }

                    @Override
                    public void update(Player player, InventoryContents contents) {

                    }
                })
                .build();
        inventory.open(sender);
    }
}
