package club.vertuli.vvanilla.commands;

import club.vertuli.vvanilla.utils.BossbarUtils;
import club.vertuli.vvanilla.utils.ChatFormat;
import club.vertuli.vvanilla.menus.adminMenu;
import club.vertuli.vvanilla.vVanilla;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class adminPanel implements CommandExecutor {
    private final vVanilla plugin;
    private final adminMenu adminMenu;

    public adminPanel(vVanilla plugin) {
        this.plugin = plugin;
        this.adminMenu = new adminMenu(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command can be used only by players");
            return true;
        }

        Player player = (Player) sender;
        String permission = plugin.getConfig().getString("admin_permission", "");
        String apan = plugin.getConfig().getString("admin_panel", "");
        String pheader = plugin.getConfig().getString("admin_panel_header", "");
        String ann = plugin.getConfig().getString("admin_announce", "");
        String aplin = plugin.getConfig().getString("admin_announce_msg_login", "");
        String aplout = plugin.getConfig().getString("admin_announce_msg_logout", "");
        String no_perm = plugin.getConfig().getString("no_permission","");

        if (!player.hasPermission(permission)) {
            player.sendMessage(ChatFormat.color(no_perm));
            return true;
        }

        if (args.length == 0) {
            adminMenu.openAdminMenu(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "login":
                if (!player.isOp() && apan.equalsIgnoreCase("enable")) {
                    player.sendMessage(ChatFormat.color(pheader));
                    player.sendMessage(ChatFormat.color("&fYou've been logged as an &cadmin"));
                    player.setOp(true);
                    player.setGameMode(GameMode.CREATIVE);
                    if (ann.equalsIgnoreCase("enable")) {
                        String loginMessage = ChatFormat.color(aplin.replace("%admin%", player.getName()));
                        for (Player p : plugin.getServer().getOnlinePlayers()) {
                            BossbarUtils.bossbarShow(ChatFormat.color(loginMessage),5);
                            if (p != player) {
                                p.sendMessage(ChatFormat.color(pheader));
                                p.sendMessage(loginMessage);
                            }
                        }
                    }
                }
                break;
            case "logout":
                if (player.isOp() && apan.equalsIgnoreCase("enable")) {
                    player.sendMessage(ChatFormat.color(pheader));
                    player.sendMessage(ChatFormat.color("&fYou've been &clogged out &fof &cadmin"));
                    player.setOp(false);
                    player.setGameMode(GameMode.SURVIVAL);
                    if (ann.equalsIgnoreCase("enable")) {
                        String logoutMessage = ChatFormat.color(aplout.replace("%admin%", player.getName()));
                        for (Player p : plugin.getServer().getOnlinePlayers()) {
                            BossbarUtils.bossbarShow(ChatFormat.color(logoutMessage),5);
                            if (p != player) {
                                p.sendMessage(ChatFormat.color(pheader));
                                p.sendMessage(logoutMessage);
                            }
                        }
                    }
                }
                break;
            default:
                player.sendMessage(ChatFormat.color("&cInvalid command usage. Use /cpanel <login|logout> or /cpanel"));
                break;
        }

        return true;
    }
}
