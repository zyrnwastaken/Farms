package cc.zyrn.farms.farm;

import cc.zyrn.farms.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum FarmType {

    COMMON(5, 1, new ItemBuilder(Material.PLAYER_HEAD).setName("&a&lCommon Farm").setLore("&7Place this to create a common farm")
            .getStack()),
    UNCOMMON(10, 2, new ItemBuilder(Material.CREEPER_HEAD).setName("&b&lUncommon Farm").setLore("&7Place this to create a common farm")
            .setGlow(true).getStack()),
    RARE(15, 3,
            new ItemBuilder(Material.PIGLIN_HEAD).setName("&e&lRare Farm").setLore("&7Place this to create a common farm")
                    .setGlow(true).getStack()),
    LEGENDARY(20,4,
            new ItemBuilder(Material.DRAGON_BREATH).setName("&g&lLegendary Farm").setLore("&7Place this to create a common farm")
                    .setGlow(true).getStack());

    private final int radius, speedMultiplier;
    private final ItemStack stack;

    FarmType(int radius, int speedMultiplier, ItemStack stack) {
        this.radius = radius;
        this.speedMultiplier = speedMultiplier;
        this.stack = stack;
    }



}
