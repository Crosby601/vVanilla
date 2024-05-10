package club.vertuli.vvanilla.listeners;

import club.vertuli.vvanilla.utils.ChatFormat;
import club.vertuli.vvanilla.vVanilla;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getServer;

public class playerJEListener implements Listener {
    private final vVanilla plugin;

    public playerJEListener(vVanilla plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player joiningPlayer = event.getPlayer();
        if (!joiningPlayer.isOp()) {
            String message = plugin.getConfig().getString("entry_message", "");
            String bc = plugin.getConfig().getString("welcome_broadcast", "");
            if (!bc.isEmpty()) {
                bc = bc.replace("%player%", event.getPlayer().getName());
                for (Player player : getServer().getOnlinePlayers()) {
                    player.sendMessage(ChatFormat.color(bc));
                }
            }
            if (!message.isEmpty()) {
                message = message.replace("%player%", event.getPlayer().getName());
                joiningPlayer.sendMessage(ChatFormat.color(message));
            }
        } else {
            String am = plugin.getConfig().getString("welcome_admin", "");
            if (!am.isEmpty()) {
                am = am.replace("%admin", event.getPlayer().getName());
                for (Player player : getServer().getOnlinePlayers()) {
                    player.sendMessage(ChatFormat.color(am));
                }
            }
        }
    }
}