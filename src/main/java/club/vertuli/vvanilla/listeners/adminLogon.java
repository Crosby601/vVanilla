package club.vertuli.vvanilla.listeners;

import club.vertuli.vvanilla.vVanilla;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import club.vertuli.vvanilla.utils.ChatFormat;

public class adminLogon implements Listener {
    private final vVanilla plugin;

    public adminLogon(vVanilla plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAdminJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String permission = plugin.getConfig().getString("admin_permission","");
        String alog = plugin.getConfig().getString("admin_login", "");
        String apan = plugin.getConfig().getString("admin_panel","");
        if (player.hasPermission(permission)) {
            if (!alog.isEmpty()) {
                player.sendMessage("");
                player.sendMessage(ChatFormat.color(alog));
                player.sendMessage("");
            }
            if (!apan.equalsIgnoreCase("disable")) {
                player.sendMessage(ChatFormat.color("&cGet access to admin panel by command /cpanel"));
            }
        }
    }

}
