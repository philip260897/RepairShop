package me.RepairShop;

import com.iCo6.system.Account;
import com.iCo6.system.Accounts;
import cosine.boseconomy.BOSEconomy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;


public class RepairShop extends JavaPlugin 
{
    private final RepairShopListener Listener = new RepairShopListener(this);
    public final EconomyManager EM = new EconomyManager(this);
    public final PermissionsManager PM = new PermissionsManager(this);
    public final ConfigManager config = new ConfigManager(this);
    public final MessageManager messages = new MessageManager(this);


    /*To Do:
     * Reload befehl
     * Help liste
     * Vielleicht lizenz
     * Vielleicht blacklist
     * 
     */
    
    @Override
    public void onDisable() 
    {
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println("[RepairShop] version " + pdfFile.getVersion() + " is disabled!" );
    }
    
    @Override
    public void onEnable() 
    {       
        
        config.createConfig();
        messages.createConfig();
        
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this.Listener, this);
        getCommand("rp").setExecutor(new CommandListener(this));
        
        setupiConomy();
        setupPermissions();

        
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println("[RepairShop] version " + pdfFile.getVersion() + " is enabled!" );
    }

    
    private void setupPermissions()
    {
        Plugin PEX = this.getServer().getPluginManager().getPlugin("PermissionsEx");
        Plugin bPermissions = this.getServer().getPluginManager().getPlugin("bPermissions");
        Plugin Essentials = this.getServer().getPluginManager().getPlugin("Essentials");
        if (PEX != null)
        {
            System.out.println("[RepairShop] found "+((PermissionsEx)PEX).getDescription().getName()+" Version "+PEX.getDescription().getVersion());
            return;
        }
        if(bPermissions != null)
        {
            System.out.println("[RepairShop] found bPermissions Version "+bPermissions.getDescription().getVersion());
            return;
        }
        if(Essentials != null)
        {
            System.out.println("[RepairShop] found Essentials Version "+Essentials.getDescription().getVersion());
            return;
        }
        
        System.out.println("[RepairShop] No permissions-system found. Using Bukkit default permissions.");
        
    }
    
    private void setupiConomy()
    {
        Plugin iConomy = this.getServer().getPluginManager().getPlugin("iConomy");
        Plugin bEcon = this.getServer().getPluginManager().getPlugin("BOSEconomy");
        Plugin Essentials = this.getServer().getPluginManager().getPlugin("Essentials");
        
        if(iConomy != null)
        {
             System.out.println("[RepairShop] hooked into iConomy version "+iConomy.getDescription().getVersion());
             return;
        }
        if(bEcon != null)
        {
            System.out.println("[RepairShop] hooked into BOSEconomy version "+bEcon.getDescription().getVersion());
            return;
        }
        if(Essentials != null)
        {
            System.out.println("[RepairShop] found "+Essentials.getDescription().getName()+" Version "+Essentials.getDescription().getVersion());
            return;
        }
        System.out.println("[RepairShop] No economy-system found. Disabling now.");
        getPluginLoader().disablePlugin(this);
        
    }
}