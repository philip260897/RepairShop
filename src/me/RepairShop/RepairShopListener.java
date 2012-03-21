package me.RepairShop;

import com.iCo6.system.Account;
import com.iCo6.system.Accounts;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
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
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        Block b = event.getBlock();
        if ((b.getType().equals(Material.SIGN)) || (b.getType().equals(Material.SIGN_POST)) || (b.getType().equals(Material.WALL_SIGN)))
        {
            Sign sign = (Sign) b.getState();
            String Prefix = this.plugin.messages.Prefix();
            if(sign.getLine(0).equalsIgnoreCase(ChatColor.DARK_AQUA+"[repair]"))
            {
                if ((plugin.PM.hasPermission(player, "RepairShop.break")) || plugin.PM.hasPermission(player, "RepairShop.Admin") || (player.isOp())) {
                    //
                } else {
                    event.setCancelled(true);
                    player.sendMessage(this.plugin.messages.no_permission(Prefix));
                    sign.update();
                }
            }


        }
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
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

                if (sign.getLine(0).equals(ChatColor.DARK_AQUA+"[repair]"))
                {
                    String line1 = sign.getLine(1);
                    String line2 = sign.getLine(2);
                    String line3 = sign.getLine(3);
                    ItemStack i = event.getPlayer().getItemInHand();

                    if ((plugin.PM.hasPermission(player, "RepairShop.use") == true) || plugin.PM.hasPermission(player, "RepairShop.Admin") || (player.isOp()))
                    {
                      Tools(player, sign, line1, line2, line3, i);
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
    
public void Tools(Player player,Sign sign, String line1, String line2, String line3, ItemStack i)
  {
    if ((line2.equalsIgnoreCase("Diamond_Tools")))
    {
      if ((i.getType().equals(Material.DIAMOND_PICKAXE)) || (i.getType().equals(Material.DIAMOND_AXE)) || (i.getType().equals(Material.DIAMOND_HOE)) || (i.getType().equals(Material.DIAMOND_SPADE)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "tool";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }

    }

    if ((line2.equalsIgnoreCase("Gold_Tools")))
    {
      if ((i.getType().equals(Material.GOLD_PICKAXE)) || (i.getType().equals(Material.GOLD_AXE)) || (i.getType().equals(Material.GOLD_HOE)) || (i.getType().equals(Material.GOLD_SPADE)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "tool";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }
    }

    if ((line2.equalsIgnoreCase("Iron_Tools")))
    {
      if ((i.getType().equals(Material.IRON_PICKAXE)) || (i.getType().equals(Material.IRON_AXE)) || (i.getType().equals(Material.IRON_HOE)) || (i.getType().equals(Material.IRON_SPADE)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "tool";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }
    }

    if ((line2.equalsIgnoreCase("STONE_Tools")))
    {
      if ((i.getType().equals(Material.STONE_PICKAXE)) || (i.getType().equals(Material.STONE_AXE)) || (i.getType().equals(Material.STONE_HOE)) || (i.getType().equals(Material.STONE_SPADE)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "tool";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }
    }

    if ((line2.equalsIgnoreCase("WOOD_Tools")))
    {
      if ((i.getType().equals(Material.WOOD_PICKAXE)) || (i.getType().equals(Material.WOOD_AXE)) || (i.getType().equals(Material.WOOD_HOE)) || (i.getType().equals(Material.WOOD_SPADE)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "tool";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }

    }

    if ((line2.equalsIgnoreCase("DIAMOND_Weapon")))
    {
      if (i.getType().equals(Material.DIAMOND_SWORD))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }

    }

    if ((line2.equalsIgnoreCase("Gold_Weapon")))
    {
      if (i.getType().equals(Material.GOLD_SWORD))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }

    }

    if ((line2.equalsIgnoreCase("Iron_Weapon")))
    {
      if (i.getType().equals(Material.IRON_SWORD))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }

    }

    if ((line2.equalsIgnoreCase("Stone_Weapon")))
    {
      if (i.getType().equals(Material.STONE_SWORD))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }

    }

    if ((line2.equalsIgnoreCase("WOOD_Weapon")))
    {
      if (i.getType().equals(Material.WOOD_SWORD))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }
    }

    if ((line2.equalsIgnoreCase("DIAMOND_Armor")))
    {
      if ((i.getType().equals(Material.DIAMOND_CHESTPLATE)) || (i.getType().equals(Material.DIAMOND_HELMET)) || (i.getType().equals(Material.DIAMOND_BOOTS)) || (i.getType().equals(Material.DIAMOND_LEGGINGS)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "armor";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }

    }

    if ((line1.equalsIgnoreCase("IRON_Armor")))
    {
      if ((i.getType().equals(Material.IRON_CHESTPLATE)) || (i.getType().equals(Material.IRON_HELMET)) || (i.getType().equals(Material.IRON_BOOTS)) || (i.getType().equals(Material.IRON_LEGGINGS)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "armor";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }

    }

    if ((line2.equalsIgnoreCase("GOLD_Armor")))
    {
      if ((i.getType().equals(Material.GOLD_CHESTPLATE)) || (i.getType().equals(Material.GOLD_HELMET)) || (i.getType().equals(Material.GOLD_BOOTS)) || (i.getType().equals(Material.GOLD_LEGGINGS)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "armor";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }

    }

    if ((line2.equalsIgnoreCase("LEATHER_Armor")))
    {
      if ((i.getType().equals(Material.LEATHER_CHESTPLATE)) || (i.getType().equals(Material.LEATHER_HELMET)) || (i.getType().equals(Material.LEATHER_BOOTS)) || (i.getType().equals(Material.LEATHER_LEGGINGS)))
      {
        if (i.getDurability() >= 1)
        {
          String type = "armor";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }
    }

    if ((line2.equalsIgnoreCase("BOW_Weapon")))
    {
      if (i.getType().equals(Material.BOW))
      {
        if (i.getDurability() >= 1)
        {
          String type = "weapon";
          Repair(player, sign, line1, line2, line3, i, type);
        }
      }
    }
  }

    public void Repair(Player player, Sign sign, String line1, String line2, String line3, ItemStack i, String type) {
        if (line1.equalsIgnoreCase("adminshop")) {
            this.RepairAdmin(player, sign, line1, line2, line3, i, type);
        } else {
            OfflinePlayer oPlayer = plugin.getServer().getOfflinePlayer(line1);
            if (oPlayer != null) {
                this.RepairPlayer(player, sign, line1, line2, line3, i, type);
            } else {
                player.sendMessage(plugin.messages.shop_owner_no_exist(type));
            }
        }
    }
  
  public void RepairPlayer(Player player, Sign sign, String line1, String line2, String line3, ItemStack i, String type)
  {
      String Prefix = this.plugin.messages.Prefix();
      try{
          int cost = Integer.parseInt(line3);
          int Amount = plugin.config.getAmount(line2);
          if(Amount != 0)
          {
              short durability = 0;
              if(hasChest(sign.getBlock()))
              {
                  Chest chest = (Chest) sign.getBlock().getRelative(BlockFace.DOWN).getState();
                  Material mat = this.getMat(line2);
                  if(mat != null)
                  {
                      if(this.hasAmount(chest, mat, Amount))
                      {
                          this.removeItem(chest, mat, Amount);
                          if(plugin.EM.transaction(player,line1 ,cost, type) == true)
                          {
                              i.setDurability(durability);
                              this.NotifyOwner(player, sign);
                              return;
                          }
                          else
                          {
                              player.sendMessage(plugin.messages.not_enough_money(Prefix));
                              return;
                          }
                      }
                      else
                      {
                          player.sendMessage(plugin.messages.no_resources(Prefix));
                          return;
                      }
                  }
                  else
                  {
                      player.sendMessage(plugin.messages.invalid_repair_type(Prefix));
                      return;
                  }
              }
              if(hasDoubleChest(sign.getBlock()))
              {
                  DoubleChest chest = (DoubleChest) sign.getBlock().getRelative(BlockFace.DOWN).getState();
                  Material mat = this.getMat(line2);
                  if(mat != null)
                  {
                      if(this.hasDoubleAmount(chest, sign, mat, Amount))
                      {
                          this.removeDoubleItem(chest, mat, Amount);
                          if(plugin.EM.transaction(player,line1, cost, type) == true)
                          {
                              i.setDurability(durability);
                              this.NotifyOwner(player, sign);
                              return;
                          }
                          else
                          {
                              player.sendMessage(plugin.messages.not_enough_money(Prefix));
                              return;
                          }
                      } 
                      else
                      {
                          player.sendMessage(plugin.messages.no_resources(Prefix));
                          return;
                      }
                  }
                  else
                  {
                      player.sendMessage(plugin.messages.invalid_repair_type(Prefix));
                      return;
                  }
              }
              player.sendMessage(plugin.messages.no_chest_found(Prefix));
          }
      }catch(Exception ex){
          player.sendMessage(plugin.messages.error_occured(Prefix));
      }
  }
  
    public void RepairAdmin(Player player, Sign sign, String line1, String line2, String line3, ItemStack i, String type) {
        int cost = Integer.parseInt(line3);
        short durability = 0;
        if (plugin.EM.pay(player, cost, type) == true) {
            i.setDurability(durability);
        }
    }
    
    public void NotifyOwner(Player player, Sign sign)
    {
        String Prefix = this.plugin.messages.Prefix();
        String Currency = this.plugin.config.Currency();
        OfflinePlayer oPlayer = plugin.getServer().getOfflinePlayer(sign.getLine(1));
        if(oPlayer.isOnline())
        {
            Player target = (Player) oPlayer;
            target.sendMessage(plugin.messages.notify_player_bought(player.getName(), Prefix, sign.getLine(2), sign.getLine(3), Currency));
        }
    }
    
    public void NotifyOwnerLowResources(Sign sign)
    {
        String Prefix = this.plugin.messages.Prefix();
        OfflinePlayer oPlayer = plugin.getServer().getOfflinePlayer(sign.getLine(1));
        if(oPlayer.isOnline())
        {
            Player target = (Player) oPlayer;
            target.sendMessage(plugin.messages.running_out_of_resources(Prefix));
        }
    }
  
    public boolean hasChest(Block b) {
        if (b.getRelative(BlockFace.DOWN).getType().equals(Material.CHEST)) {
            return true;
        }
        return false;
    }
    
    public boolean hasDoubleChest(Block b){
        if(b.getRelative(BlockFace.DOWN) instanceof DoubleChest){
            return true;
        }
        return false;
    }

    public boolean hasAmount(Chest chest, Material mat, int sAmount) {
        int cAmount = 0;
        ItemStack[] cIs = chest.getInventory().getContents();
        for (int i = 0; i < cIs.length; i++) {
            if (cIs[i] != null) {
                if (cIs[i].getTypeId() == mat.getId()) {
                    cAmount += cIs[i].getAmount();
                }
            }
        }
        if (cAmount >= sAmount) {
            if(cAmount < 20 && cAmount > 14)
            {
                Sign sign = (Sign) chest.getBlock().getRelative(BlockFace.UP).getState();
                this.NotifyOwnerLowResources(sign);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean hasDoubleAmount(DoubleChest chest,Sign sign, Material mat, int sAmount) {
        int cAmount = 0;
        ItemStack[] cIs = chest.getInventory().getContents();
        for (int i = 0; i < cIs.length; i++) {
            if (cIs[i] != null) {
                if (cIs[i].getTypeId() == mat.getId()) {
                    cAmount += cIs[i].getAmount();
                }
            }
        }
        if (cAmount >= sAmount) {
            if(cAmount < 20 && cAmount > 14)
            {
                this.NotifyOwnerLowResources(sign);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean removeItem(Chest chest, Material material, int amount) {
        int x = amount;
        ItemStack[] iss = chest.getInventory().getContents();
        for (ItemStack it : iss) {
            try {
                if (it.getType() == material) {
                    if (it.getAmount() > x) {
                        it.setAmount(it.getAmount() - x);
                        x = 0;
                    } else {
                        x -= it.getAmount();
                        it.setAmount(0);
                    }
                }
            } catch (Exception localException) {
            }
        }
        if (x != 0) {
            return false;
        }
        chest.getInventory().setContents(iss);
        chest.update();
        return true;
    }
    
    public boolean removeDoubleItem(DoubleChest chest, Material material, int amount) {
        int x = amount;
        ItemStack[] iss = chest.getInventory().getContents();
        for (ItemStack it : iss) {
            try {
                if (it.getType() == material) {
                    if (it.getAmount() > x) {
                        it.setAmount(it.getAmount() - x);
                        x = 0;
                    } else {
                        x -= it.getAmount();
                        it.setAmount(0);
                    }
                }
            } catch (Exception localException) {
            }
        }
        if (x != 0) {
            return false;
        }
        chest.getInventory().setContents(iss);
        return true;
    }
    
    public Material getMat(String line2)
    {
        if(line2.startsWith("Diamond") || line2.startsWith("DIAMOND") || line2.startsWith("diamond"))
        {
            return Material.DIAMOND;
        }
        if(line2.startsWith("Gold") || line2.startsWith("GOLD") || line2.startsWith("gold"))
        {
            return Material.GOLD_INGOT;
        }
        if(line2.startsWith("Iron") || line2.startsWith("IRON") || line2.startsWith("iron"))
        {
            return Material.IRON_INGOT;
        }
        if(line2.startsWith("Stone") || line2.startsWith("STONE") || line2.startsWith("stone"))
        {
            return Material.STONE;
        }
        if(line2.startsWith("Wood") || line2.startsWith("WOOD") || line2.startsWith("wood") || line2.startsWith("Bow") || line2.startsWith("BOW") || line2.startsWith("Bow"))
        {
            return Material.WOOD;
        }
        if(line2.startsWith("Leather") || line2.startsWith("LEATHER") || line2.startsWith("leather"))
        {
            return Material.LEATHER;
        }
        return null;
    }
  
    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Server server = event.getPlayer().getServer();
        Player player = event.getPlayer();
        String Preis = event.getLine(2);
        String Art = event.getLine(1);
        String Prefix = this.plugin.messages.Prefix();
        String Currency = this.plugin.config.Currency();

        if (event.getLine(0).equals("[repair]")) {
            if (event.getLine(1).equalsIgnoreCase("adminshop")) {
                if (plugin.PM.hasPermission(player, "RepairShop.AdminShop") || plugin.PM.hasPermission(player, "RepairShop.Admin") || player.isOp()) {
                    try {
                        int price = Integer.parseInt(event.getLine(3));
                        Material mat = this.getMat(event.getLine(2));
                        if (mat != null) {
                            event.setLine(0, ChatColor.DARK_AQUA + "[repair]");
                            player.sendMessage(plugin.messages.repair_create_message(Prefix, Art, Preis, Currency));
                        } else {
                            event.setCancelled(true);
                            player.sendMessage(plugin.messages.invalid_repair_type(Prefix));
                        }
                    } catch (Exception ex) {
                        player.sendMessage(plugin.messages.wrong_format(Prefix));
                    }
                } else {
                    event.setCancelled(true);
                    player.sendMessage(plugin.messages.no_permission(Prefix));
                }
            } else {

                String smat = event.getLine(1);
                String price = event.getLine(2);
                try {
                    Material mat = this.getMat(event.getLine(1));
                    if (mat != null) {
                        if (smat.toLowerCase().contains("tools")) {
                            if (plugin.PM.hasPermission(player, "RepairShop.create.tools") || plugin.PM.hasPermission(player, "RepairShop.Admin") || player.isOp()) {
                                int iPrice = Integer.parseInt(price);
                                event.setLine(0, ChatColor.DARK_AQUA + "[repair]");
                                event.setLine(1, player.getName());
                                event.setLine(2, smat);
                                event.setLine(3, price);
                                player.sendMessage(plugin.messages.repair_create_message(Prefix, Art, Preis, Currency));
                                return;
                            } else {
                                player.sendMessage(plugin.messages.no_permission(Prefix));
                                event.setCancelled(true);
                            }
                        }
                        if (smat.toLowerCase().contains("armor")) {
                            if (plugin.PM.hasPermission(player, "RepairShop.create.armor") || plugin.PM.hasPermission(player, "RepairShop.Admin") || player.isOp()) {
                                int iPrice = Integer.parseInt(price);
                                event.setLine(0, ChatColor.DARK_AQUA + "[repair]");
                                event.setLine(1, player.getName());
                                event.setLine(2, smat);
                                event.setLine(3, price);
                                player.sendMessage(plugin.messages.repair_create_message(Prefix, Art, Preis, Currency));
                                return;
                            } else {
                                player.sendMessage(plugin.messages.no_permission(Prefix));
                                event.setCancelled(true);
                            }
                        }
                        if (smat.toLowerCase().contains("weapon")) {
                            if (plugin.PM.hasPermission(player, "RepairShop.create.weapon") || plugin.PM.hasPermission(player, "RepairShop.Admin") || player.isOp()) {
                                int iPrice = Integer.parseInt(price);
                                event.setLine(0, ChatColor.DARK_AQUA + "[repair]");
                                event.setLine(1, player.getName());
                                event.setLine(2, smat);
                                event.setLine(3, price);
                                player.sendMessage(plugin.messages.repair_create_message(Prefix, Art, Preis, Currency));
                                return;
                            } else {
                                player.sendMessage(plugin.messages.no_permission(Prefix));
                                event.setCancelled(true);
                            }
                        }
                    } else {
                        event.setCancelled(true);
                        player.sendMessage(plugin.messages.invalid_repair_type(Prefix));
                    }
                } catch (Exception ex) {
                    event.setCancelled(true);
                    player.sendMessage(plugin.messages.wrong_format(Prefix));
                }
            }
        }
    }
}
