package club.vertuli.vvanilla.utils;

import org.bukkit.ChatColor;

public class ChatFormat {
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
