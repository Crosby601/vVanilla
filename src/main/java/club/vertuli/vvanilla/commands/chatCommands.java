package club.vertuli.vvanilla.commands;

import club.vertuli.vvanilla.utils.ChatFormat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import club.vertuli.vvanilla.vVanilla;

public class chatCommands implements CommandExecutor {
    private final vVanilla plugin;

    public chatCommands(vVanilla plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String ced = plugin.getConfig().getString("chat_enable_message","");
        String cdd = plugin.getConfig().getString("chat_disable_message","");
        String cc = plugin.getConfig().getString("chat_clear_message","");
        String no_perm = plugin.getConfig().getString("no_permission","");

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("vanilla.chat")) {
            player.sendMessage(ChatFormat.color(no_perm));
            return true;
        }

        if (args.length == 0) {
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "on":
            case "off":
                boolean isChatEnabled = args[0].equalsIgnoreCase("on");
                plugin.setChatEnabled(isChatEnabled);
                String message = isChatEnabled ? ChatFormat.color(ced) : ChatFormat.color(cdd);
                player.sendMessage(message);
                break;
            case "cc":
                for (int i = 0; i < 75; i++) {
                    for (Player p : plugin.getServer().getOnlinePlayers()) {
                        p.sendMessage("");
                    }
                }
                player.sendMessage(ChatFormat.color(cc));
                break;
            default:
                return false;
        }
        return true;
    }

}
