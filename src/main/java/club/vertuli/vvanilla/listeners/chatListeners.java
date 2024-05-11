package club.vertuli.vvanilla.listeners;

import club.vertuli.vvanilla.utils.ChatFormat;
import club.vertuli.vvanilla.vVanilla;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatListeners implements Listener {
    private final vVanilla plugin;

    public chatListeners(vVanilla plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String cdd = plugin.getConfig().getString("chat_disabled_message","");
        if (!plugin.isChatEnabled()) {
            if (!event.getPlayer().hasPermission("vanilla.chat")) {
                event.getPlayer().sendMessage(ChatFormat.color(cdd));
                event.setCancelled(true);
            }
        }
    }
}
