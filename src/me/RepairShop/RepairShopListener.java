/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.RepairShop;

import com.iCo6.system.Account;
import com.iCo6.system.Accounts;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class RepairShopListener implements Listener{
    private final RepairShop plugin;

    public RepairShopListener(final RepairShop plugin) {
        this.plugin = plugin;
    } 
    Plugin permissionsPlugin;
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        permissionsPlugin = plugin.getServer().getPluginManager().getPlugin("PermissionsEx");
        Player player = event.getPlayer();
        Block b = event.getBlock();
        if ((b.getType().equals(Material.SIGN)) || (b.getType().equals(Material.SIGN_POST)) || (b.getType().equals(Material.WALL_SIGN)))
        {
            Sign sign = (Sign) b.getState();
            String Prefix = this.plugin.messages.Prefix();

                if ((plugin.PM.hasPermission(player, "RepairShop.break")) || (player.isOp())) {
                    //
                } else {
                    event.setCancelled(true);
                    player.sendMessage(this.plugin.messages.no_permission(Prefix));
                    sign.update();
                }


        }
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        permissionsPlugin = plugin.getServer().getPluginManager().getPlugin("PermissionsEx");
        Player player = event.getPlayer();
        String action = plugin.config.getAction();
        if(action.equalsIgnoreCase("RIGHT_CLICK")){
            if(event.getAction() != Action.RIGHT_CLICK_BLOCK){
                return;
            }
        }
        if(action.equalsIgnoreCase("LEFT_CLICK")){
            if(event.getAction() != Action.LEFT_CLICK_BLOCK){
                return;
            }
        }
        if(!action.equalsIgnoreCase("LEFT_CLICK") && !action.equalsIgnoreCase("RIGHT_CLICK")){
            player.sendMessage(ChatColor.RED+"Error in Config. Set 'Action' to RIGHT_CLICK or LEFT_CLICK.");
        }

            Block b = event.getClickedBlock();
            if (b != null)
            {
              if ((b.getType().equals(Material.SIGN)) || (b.getType().equals(Material.SIGN_POST)) || (b.getType().equals(Material.WALL_SIGN)))
              {
                Sign sign = (Sign)b.getState();
                Server server = event.getPlayer().getServer();

                if (sign.getLine(0).equals("[repair]"))
                {
                  String line1 = sign.getLine(1);
                  String line2 = sign.getLine(2);
                  String line3 = sign.getLine(3);
                  ItemStack i = event.getPlayer().getItemInHand();


                    if ((plugin.PM.hasPermission(player, "RepairShop.use") == true) || (player.isOp()))
                    {
                      Tools(player, line1, line2, line3, i);
                    }
                    else
                    {
                      String Prefix = this.plugin.messages.Prefix();
                      player.sendMessage(this.plugin.messages.no_permission(Prefix));
                    }
                }
              }
            }
        }
    
public void Tools(Player player, String line1, String line2, String line3, ItemStack i)
  {
    if ((line1.equalsIgnoreCase("Diamond_Tools")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if ((i.getType().equals(Material.DIAMOND_PICKAXE)) || (i.getType().equals(Material.DIAMOND_AXE)) || (i.getType().equals(Material.DIAMOND_HOE)) || (i.getType().equals(Material.DIAMOND_SPADE)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "tool";
          Repair(player, line1, line2, line3, i, type);
        }
      }

    }

    if ((line1.equalsIgnoreCase("Gold_Tools")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if ((i.getType().equals(Material.GOLD_PICKAXE)) || (i.getType().equals(Material.GOLD_AXE)) || (i.getType().equals(Material.GOLD_HOE)) || (i.getType().equals(Material.GOLD_SPADE)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "tool";
          Repair(player, line1, line2, line3, i, type);
        }
      }
    }

    if ((line1.equalsIgnoreCase("Iron_Tools")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if ((i.getType().equals(Material.IRON_PICKAXE)) || (i.getType().equals(Material.IRON_AXE)) || (i.getType().equals(Material.IRON_HOE)) || (i.getType().equals(Material.IRON_SPADE)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "tool";
          Repair(player, line1, line2, line3, i, type);
        }
      }
    }

    if ((line1.equalsIgnoreCase("STONE_Tools")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if ((i.getType().equals(Material.STONE_PICKAXE)) || (i.getType().equals(Material.STONE_AXE)) || (i.getType().equals(Material.STONE_HOE)) || (i.getType().equals(Material.STONE_SPADE)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "tool";
          Repair(player, line1, line2, line3, i, type);
        }
      }
    }

    if ((line1.equalsIgnoreCase("WOOD_Tools")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if ((i.getType().equals(Material.WOOD_PICKAXE)) || (i.getType().equals(Material.WOOD_AXE)) || (i.getType().equals(Material.WOOD_HOE)) || (i.getType().equals(Material.WOOD_SPADE)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "tool";
          Repair(player, line1, line2, line3, i, type);
        }
      }

    }

    if ((line1.equalsIgnoreCase("DIAMOND_Weapon")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if (i.getType().equals(Material.DIAMOND_SWORD))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, line1, line2, line3, i, type);
        }
      }

    }

    if ((line1.equalsIgnoreCase("Gold_Weapon")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if (i.getType().equals(Material.GOLD_SWORD))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, line1, line2, line3, i, type);
        }
      }

    }

    if ((line1.equalsIgnoreCase("Iron_Weapon")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if (i.getType().equals(Material.IRON_SWORD))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, line1, line2, line3, i, type);
        }
      }

    }

    if ((line1.equalsIgnoreCase("Stone_Weapon")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if (i.getType().equals(Material.STONE_SWORD))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, line1, line2, line3, i, type);
        }
      }

    }

    if ((line1.equalsIgnoreCase("WOOD_Weapon")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if (i.getType().equals(Material.WOOD_SWORD))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, line1, line2, line3, i, type);
        }
      }
    }

    if ((line1.equalsIgnoreCase("DIAMOND_Armor")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if ((i.getType().equals(Material.DIAMOND_CHESTPLATE)) || (i.getType().equals(Material.DIAMOND_HELMET)) || (i.getType().equals(Material.DIAMOND_BOOTS)) || (i.getType().equals(Material.DIAMOND_LEGGINGS)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "armor";
          Repair(player, line1, line2, line3, i, type);
        }
      }

    }

    if ((line1.equalsIgnoreCase("IRON_Armor")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if ((i.getType().equals(Material.IRON_CHESTPLATE)) || (i.getType().equals(Material.IRON_HELMET)) || (i.getType().equals(Material.IRON_BOOTS)) || (i.getType().equals(Material.IRON_LEGGINGS)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "armor";
          Repair(player, line1, line2, line3, i, type);
        }
      }

    }

    if ((line1.equalsIgnoreCase("GOLD_Armor")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if ((i.getType().equals(Material.GOLD_CHESTPLATE)) || (i.getType().equals(Material.GOLD_HELMET)) || (i.getType().equals(Material.GOLD_BOOTS)) || (i.getType().equals(Material.GOLD_LEGGINGS)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "armor";
          Repair(player, line1, line2, line3, i, type);
        }
      }

    }

    if ((line1.equalsIgnoreCase("LEATHER_Armor")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if ((i.getType().equals(Material.LEATHER_CHESTPLATE)) || (i.getType().equals(Material.LEATHER_HELMET)) || (i.getType().equals(Material.LEATHER_BOOTS)) || (i.getType().equals(Material.LEATHER_LEGGINGS)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "armor";
          Repair(player, line1, line2, line3, i, type);
        }
      }
    }

    if ((line1.equalsIgnoreCase("BOW_Weapon")) && (line3.equalsIgnoreCase(ChatColor.GREEN + "[Active]")))
    {
      if (i.getType().equals(Material.BOW))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, line1, line2, line3, i, type);
        }
      }
    }
  }

  public void Repair(Player player, String line1, String line2, String line3, ItemStack i, String type)
  {
    int cost = Integer.parseInt(line2);
    short durability = 0;
    if(plugin.EM.pay(player, cost, type) == true)
    {
        i.setDurability(durability);
    }
  }

  @EventHandler
  public void onSignChange(SignChangeEvent event) {
    Server server = event.getPlayer().getServer();
    Player player = event.getPlayer();
    String Preis = event.getLine(2);
    String Art = event.getLine(1);
    String Prefix = this.plugin.messages.Prefix();
    String Currency = this.plugin.config.Currency();

    if (event.getLine(0).equals("[repair]"))
    {
      permissionsPlugin = server.getPluginManager().getPlugin("PermissionsEx");
      if (permissionsPlugin == null)
      {
        if (player.isOp())
        {
          event.setLine(3, ChatColor.GREEN + "[Active]");

          player.sendMessage(this.plugin.messages.create_successfull(Prefix));
          player.sendMessage(this.plugin.messages.repair_create_message(Prefix, Art, Preis, Currency));
        }
        else
        {
          event.setLine(3, ChatColor.RED + "[Inactive]");

          player.sendMessage(this.plugin.messages.no_permission(Prefix));
        }

      }
      else if ((plugin.PM.hasPermission(player, "RepairShop.create")) || (player.isOp()))
      {
        event.setLine(3, ChatColor.GREEN + "[Active]");

        player.sendMessage(this.plugin.messages.create_successfull(Prefix));
        player.sendMessage(this.plugin.messages.repair_create_message(Prefix, Art, Preis, Currency));
      }
      else
      {
        event.setLine(3, ChatColor.RED + "[Inactive]");

        player.sendMessage(this.plugin.messages.no_permission(Prefix));
      }
    }
  }

    
}
