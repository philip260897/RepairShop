/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.RepairShop;

import java.io.Console;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Philip
 */
public class CommandListener implements CommandExecutor{
    public final RepairShop plugin;
    public CommandListener(final RepairShop plugin)
    {
        this.plugin = plugin;
    }
    
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String commandLabel1, String[] args) {
        if (commandLabel1.equalsIgnoreCase("rp")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (cs instanceof Player) {
                        Player player = (Player) cs;
                        if (plugin.PM.hasPermission(player, "RepairShop.Admin") || player.isOp()) {
                            plugin.config.createConfig();
                            plugin.messages.createConfig();
                            player.sendMessage(ChatColor.AQUA + "[Repair] " + ChatColor.GRAY + "Reload complete!");
                        }
                    } else {
                        plugin.config.createConfig();
                        plugin.messages.createConfig();
                        cs.sendMessage(ChatColor.AQUA + "[Repair] " + ChatColor.GRAY + "Reload complete! " + cs.getName());
                    }
                }
                if (args[0].equalsIgnoreCase("?")) {
                    cs.sendMessage(ChatColor.AQUA + "RepairShop-Help:");
                    cs.sendMessage(ChatColor.AQUA + "/rp playershop ? " + ChatColor.GRAY + "- View help how to create a Player-RepairShop");
                    cs.sendMessage(ChatColor.AQUA + "/rp adminshop ? " + ChatColor.GRAY + "- View help how to create a Admin-RepairShop");
                    if (cs instanceof Player) {
                        Player player = (Player) cs;
                        if (plugin.PM.hasPermission(player, "RepairShop.Admin") || player.isOp()) {
                            player.sendMessage(ChatColor.AQUA + "/rp reload " + ChatColor.GRAY + "- Reload the RepairShop plugin.");
                        }
                    }
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("playershop") && args[1].equalsIgnoreCase("?")) {
                    cs.sendMessage(ChatColor.AQUA + "Player-RepairShop setup:");
                    cs.sendMessage(ChatColor.AQUA + "Line1: " + ChatColor.GRAY + "[repair]");
                    cs.sendMessage(ChatColor.AQUA + "Line2: " + ChatColor.GRAY + "<type>");
                    cs.sendMessage(ChatColor.AQUA + "Line3: " + ChatColor.GRAY + "<price>");
                    cs.sendMessage(ChatColor.GRAY + "'<' and '>' indicate a variable.");
                    cs.sendMessage(ChatColor.AQUA + "/rp type list " + ChatColor.GRAY + "- View a list of all types.");
                }
                if (args[0].equalsIgnoreCase("adminshop") && args[1].equalsIgnoreCase("?")) {
                    cs.sendMessage(ChatColor.AQUA + "Player-RepairShop setup:");
                    cs.sendMessage(ChatColor.AQUA + "Line1: " + ChatColor.GRAY + "[repair]");
                    cs.sendMessage(ChatColor.AQUA + "Line2: " + ChatColor.GRAY + "adminshop");
                    cs.sendMessage(ChatColor.AQUA + "Line3: " + ChatColor.GRAY + "<type>");
                    cs.sendMessage(ChatColor.AQUA + "Line4: " + ChatColor.GRAY + "<price>");
                    cs.sendMessage(ChatColor.GRAY + "'<' and '>' indicate a variable.");
                    cs.sendMessage(ChatColor.AQUA + "/rp type list " + ChatColor.GRAY + "- View a list of all types.");
                }
                if (args[0].equalsIgnoreCase("type") && args[1].equalsIgnoreCase("list")) {
                    cs.sendMessage(ChatColor.AQUA + "RepairShop-types:");
                    for (String s : plugin.config.TypeList.keySet()) {
                        if (s.contains("TOOLS")) {
                            cs.sendMessage(ChatColor.GRAY + s);
                        }
                    }
                    cs.sendMessage("");
                    for (String s : plugin.config.TypeList.keySet()) {
                        if (s.contains("ARMOR")) {
                            cs.sendMessage(ChatColor.GRAY + s);
                        }
                    }
                    cs.sendMessage("");
                    for (String s : plugin.config.TypeList.keySet()) {
                        if (s.contains("WEAPON")) {
                            cs.sendMessage(ChatColor.GRAY + s);
                        }
                    }
                }
            }
        }
        return true;
    }
}
