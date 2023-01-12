package io.paper.report;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {
    FileConfiguration config = Main.getInstance().getConfig();
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        boolean selfreport = false;
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.BLACK + "Not a player");
            return true;
        }
        Player player = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("report")){
            if (args.length <= 1) {
                player.sendMessage(config.getString("reportsystem.howtouse").replaceAll("&", "§"));
            }
            if (args.length >= 2) {
                String deportato = args[0];
                Player target = Bukkit.getPlayer(deportato);
                StringBuilder reason = new StringBuilder();
                if (target == null) {
                    player.sendMessage(config.getString("reportsystem.playernotonline").replaceAll("&", "§"));
                    return true;
                }
                if (target.getName() == player.getName()) {
                    player.sendMessage(config.getString("reportsystem.selfreport").replaceAll("&", "§"));
                    selfreport = true;
                } else {
                    player.sendMessage(config.getString("reportsystem.playerreported").replaceAll("&", "§"));
                }
                for (int i = 1; i < args.length; i++) {
                    reason.append(args[i]);
                    if (i < args.length) {
                        reason.append("");
                    }
                }
                Player[] players = Bukkit.getServer().getOnlinePlayers().toArray(new Player[0]);
                for (int i = 0; i < players.length; i++) {
                    if (players[i].hasPermission("reportsystem.report")) {
                        players[i].sendMessage(config.getString("reportsystem.reported").replaceAll("&", "§") + deportato);
                        players[i].sendMessage(config.getString("reportsystem.whoreported").replaceAll("&", "§") + player.getName());
                        players[i].sendMessage(config.getString("reportsystem.reason").replaceAll("&", "§") + reason.toString());
                    }
                }
            }
        }
        return false;
    }
}
