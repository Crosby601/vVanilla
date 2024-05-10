package club.vertuli.vvanilla.menus;

import club.vertuli.vvanilla.utils.ChatFormat;
import club.vertuli.vvanilla.vVanilla;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class adminMenu {
    private final vVanilla plugin;

    public adminMenu(vVanilla plugin) {
        this.plugin = plugin;
    }
    public void openAdminMenu(Player player) {
        String amname = plugin.getConfig().getString("admin_panel_header");
        Inventory inv = Bukkit.createInventory(player, 27, ChatFormat.color(amname));

        ItemStack time = new ItemStack(Material.CLOCK,1);
        ItemMeta MetaTime = time.getItemMeta();
        MetaTime.setDisplayName(ChatFormat.color("&aSet time"));
        time.setItemMeta(MetaTime);

        ItemStack weather = new ItemStack(Material.SUNFLOWER,1);
        ItemMeta MetaWeather = weather.getItemMeta();
        MetaWeather.setDisplayName(ChatFormat.color("&9Set weather"));
        weather.setItemMeta(MetaWeather);

        ItemStack saveItems = new ItemStack(Material.CHEST, 1);
        ItemMeta MetaSItems = saveItems.getItemMeta();
        MetaSItems.setDisplayName(ChatFormat.color("&eKeep Inventory"));
        saveItems.setItemMeta(MetaSItems);



        inv.setItem(10, time);
        inv.setItem(13, weather);
        inv.setItem(16, saveItems);

        player.openInventory(inv);
    }
}
