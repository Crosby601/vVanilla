package club.vertuli.vvanilla;

import club.vertuli.vvanilla.commands.adminPanel;
import club.vertuli.vvanilla.commands.chatCommands;
import club.vertuli.vvanilla.listeners.adminLogon;
import club.vertuli.vvanilla.listeners.chatListeners;
import club.vertuli.vvanilla.listeners.playerJEListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class vVanilla extends JavaPlugin {
    private boolean chatEnabled = true; //Default chat is on

    @Override
    public void onEnable() {
        getLogger().info("Plugin has been loaded correctly :)");
        getServer().getPluginManager().registerEvents(new playerJEListener(this), this);
        getServer().getPluginManager().registerEvents(new adminLogon(this), this);
        getServer().getPluginManager().registerEvents(new chatListeners(this), this);
        this.getCommand("chat").setExecutor(new chatCommands(this));
        this.getCommand("cpanel").setExecutor(new adminPanel(this));
        if (!this.getDataFolder().exists())
            this.getDataFolder().mkdirs();
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has been unloaded");
    }

    public void setChatEnabled(boolean enabled) {
        this.chatEnabled = enabled;
    }

    public boolean isChatEnabled() {
        return chatEnabled;
    }
}
