package club.vertuli.vvanilla.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BossbarUtils {
    private static Plugin plugin = Bukkit.getPluginManager().getPlugin("vVanilla");

    public static void bossbarShow(String text, int seconds) {
        String formattedText = ChatColor.translateAlternateColorCodes('&', text);

        BossBar bossBar = Bukkit.createBossBar(formattedText, BarColor.RED, BarStyle.SEGMENTED_10);
        bossBar.setVisible(true);

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            bossBar.setVisible(false);
            bossBar.removeAll();
        }, 20L * seconds);  // 20 ticks = 1 second
    }
}
