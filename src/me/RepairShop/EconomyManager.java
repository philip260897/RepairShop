/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.RepairShop;

import com.earth2me.essentials.api.Economy;
import com.iCo6.system.Account;
import com.iCo6.system.Accounts;
import cosine.boseconomy.BOSEconomy;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Philip
 */
public class EconomyManager {
    private final RepairShop plugin;

    public EconomyManager(final RepairShop plugin) {
        this.plugin = plugin;
    }
    
    public boolean pay(Player player, int cost, String type)
    {
        Plugin iConomy = plugin.getServer().getPluginManager().getPlugin("iConomy");
        Plugin bEconomy = plugin.getServer().getPluginManager().getPlugin("BOSEconomy");
        Plugin EssEco = plugin.getServer().getPluginManager().getPlugin("Essentials");
        if(iConomy != null)
        {
            String sCost = Integer.toString(cost);
            String Prefix = this.plugin.messages.Prefix();
            String Currency = this.plugin.config.Currency();
            Accounts accounts = new Accounts();
            Account account = accounts.get(player.getName());
            if (account.getHoldings().hasEnough(cost))
            {
                account.getHoldings().subtract(cost);

              player.sendMessage(this.plugin.messages.repair_message(Prefix, type, sCost, Currency));
              return true;
            }
            else
            {
              player.sendMessage(this.plugin.messages.not_enough_money(Prefix));
            }
        }
        if(bEconomy != null)
        {
            String sCost = Integer.toString(cost);
            String Prefix = this.plugin.messages.Prefix();
            String Currency = this.plugin.config.Currency();
            BOSEconomy economy = (BOSEconomy)bEconomy;
            boolean isRegistered = economy.playerRegistered(player.getName(), true);
            if(isRegistered)
            {
                int money = economy.getPlayerMoney(player.getName());
                if(cost <= money)
                {
                    economy.setBankMoney(player.getName(), money-cost, true);
                    player.sendMessage(this.plugin.messages.repair_message(Prefix, type, sCost, Currency));
                    return true;
                }
            }
            else
            {
                player.sendMessage(this.plugin.messages.not_enough_money(Prefix));
            }
        }
        if(EssEco != null)
        {
            String sCost = Integer.toString(cost);
            String Prefix = this.plugin.messages.Prefix();
            String Currency = this.plugin.config.Currency();
            boolean hasAccount = Economy.playerExists(player.getName());
            if(hasAccount)
            {
                try {
                    double money = Economy.getMoney(player.getName());
                    if( cost <= money)
                    {
                        try {
                            Economy.subtract(player.getName(), cost);
                            player.sendMessage(this.plugin.messages.repair_message(Prefix, type, sCost, Currency));
                            return true;
                        } catch (Exception ex) {
                            Logger.getLogger(EconomyManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(EconomyManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
    
    public boolean transaction(Player player, String target, int cost, String type)
    {
        Plugin iConomy = plugin.getServer().getPluginManager().getPlugin("iConomy");
        Plugin bEconomy = plugin.getServer().getPluginManager().getPlugin("BOSEconomy");
        Plugin EssEco = plugin.getServer().getPluginManager().getPlugin("Essentials");
        if(iConomy != null)
        {
            String sCost = Integer.toString(cost);
            String Prefix = this.plugin.messages.Prefix();
            String Currency = this.plugin.config.Currency();
            Accounts accounts = new Accounts();
            Account account = accounts.get(player.getName());
            Account taccount = accounts.get(target);
            if (account.getHoldings().hasEnough(cost))
            {
                account.getHoldings().subtract(cost);
                taccount.getHoldings().add(cost);
                player.sendMessage(this.plugin.messages.repair_message(Prefix, type, sCost, Currency));
              return true;
            }
            else
            {
              player.sendMessage(this.plugin.messages.not_enough_money(Prefix));
            }
        }
        if(bEconomy != null)
        {
            String sCost = Integer.toString(cost);
            String Prefix = this.plugin.messages.Prefix();
            String Currency = this.plugin.config.Currency();
            BOSEconomy economy = (BOSEconomy)bEconomy;
            boolean isRegistered = economy.playerRegistered(player.getName(), true);
            boolean istRegistered = economy.playerRegistered(target, true);
            if(isRegistered)
            {
                int money = economy.getPlayerMoney(player.getName());
                int tmoney = economy.getPlayerMoney(target);
                if(cost <= money)
                {
                    economy.setBankMoney(player.getName(), money-cost, true);
                    economy.setBankMoney(target, tmoney + cost, true);
                    player.sendMessage(this.plugin.messages.repair_message(Prefix, type, sCost, Currency));
                    return true;
                }
                else
                {
                  player.sendMessage(this.plugin.messages.not_enough_money(Prefix));
                }
            }
            else
            {
                player.sendMessage(this.plugin.messages.error_occured(Prefix));
            }
        }
        if(EssEco != null)
        {
            String sCost = Integer.toString(cost);
            String Prefix = this.plugin.messages.Prefix();
            String Currency = this.plugin.config.Currency();
            boolean hasAccount = Economy.playerExists(player.getName());
            boolean hasTaccount = Economy.playerExists(target);
            if(hasAccount && hasTaccount)
            {
                try {
                    double money = Economy.getMoney(player.getName());
                    if(cost <= money)
                    {
                        Economy.subtract(player.getName(), cost);
                        Economy.add(target, cost);
                        player.sendMessage(this.plugin.messages.repair_message(Prefix, type, sCost, Currency));
                        return true;
                    }
                } catch (Exception ex) {
                    Logger.getLogger(EconomyManager.class.getName()).log(Level.SEVERE, null, ex);
                    player.sendMessage(this.plugin.messages.error_occured(Prefix));
                }

            }
            else
            {
                player.sendMessage(this.plugin.messages.error_occured(Prefix));
            }
        }
        return false;
    }
}
