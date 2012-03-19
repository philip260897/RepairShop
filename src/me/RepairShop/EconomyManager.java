/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.RepairShop;

import com.iCo6.system.Account;
import com.iCo6.system.Accounts;
import cosine.boseconomy.BOSEconomy;
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
        System.out.println("blub");
        Plugin iConomy = plugin.getServer().getPluginManager().getPlugin("iConomy");
        Plugin bEconomy = plugin.getServer().getPluginManager().getPlugin("BOSEconomy");
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
        return false;
    }
}
