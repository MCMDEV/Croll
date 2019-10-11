package de.mcmdev.troll.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemStackBuilder {
    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public static ItemStackBuilder create(Material material) {
        return new ItemStackBuilder(material);
    }

    public static ItemStackBuilder create(Material material, byte data) {
        return new ItemStackBuilder(material, data);
    }


    private ItemStackBuilder(Material material) {
        this.itemStack = new ItemStack(material, 1);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    private ItemStackBuilder(Material material, byte data) {
        this.itemStack = new ItemStack(material, 1, (short)data);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemStackBuilder withAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemStackBuilder withDamage(byte damage) {
        this.itemStack.setDurability((short)damage);
        return this;
    }

    public ItemStackBuilder withColor(BlockColor blockColor) {
        this.itemStack.setDurability((short)blockColor.getDataValue());
        return this;
    }

    public ItemStackBuilder withName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemStackBuilder withLore(String... lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemStackBuilder addLore(String line) {
        List<String> old = this.itemMeta.getLore();
        old.add(line);
        this.itemMeta.setLore(old);
        return this;
    }

    public ItemStackBuilder makeUnbreakable() {
        this.itemMeta.spigot().setUnbreakable(true);
        return this;
    }

    public ItemStackBuilder addEnchantment(Enchantment enchantment, int lvl) {
        this.itemMeta.addEnchant(enchantment, lvl, false);
        return this;
    }

    public ItemStackBuilder addUnsafeEnchantment(Enchantment enchantment, int lvl) {
        this.itemMeta.addEnchant(enchantment, lvl, true);
        return this;
    }

    public ItemStackBuilder addItemFlags(ItemFlag... flags) {
        this.itemMeta.addItemFlags(flags);
        return this;
    }

    public ItemStackBuilder setSkullOwner(OfflinePlayer skullOwner)    {
        ((SkullMeta) this.itemMeta).setOwningPlayer(skullOwner);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }



    public enum BlockColor
    {
        WHITE((byte)0),
        ORANGE((byte)1),
        MAGENTA((byte)2),
        LIGHT_BLUE((byte)3),
        YELLOW((byte)4),
        LIME((byte)5),
        PINK((byte)6),
        GRAY((byte)7),
        LIGHT_GRAY((byte)8),
        CYAN((byte)9),
        PURPLE((byte)10),
        BLUE((byte)11),
        BROWN((byte)12),
        GREEN((byte)13),
        RED((byte)14),
        BLACK((byte)15);

        private byte dataValue;


        BlockColor(byte dataValue) { this.dataValue = dataValue; }



        public byte getDataValue() { return this.dataValue; }
    }
}
