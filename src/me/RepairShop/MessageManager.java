/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.RepairShop;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Philip
 */
public class MessageManager {
    public final RepairShop plugin;
    public MessageManager(final RepairShop plugin)
    {
        this.plugin = plugin;
    }  
    private static String ordner = "plugins/RepairShop";
    private static File configFile = new File(ordner + File.separator + "messages.yml");
    private static YamlConfiguration config;
    public double version = 1.0;

    
    private YamlConfiguration loadConfig()
    {
        try
        {
            YamlConfiguration config = new YamlConfiguration();
            config.load(configFile);
            return config;
        }catch (Exception e)
                {
                    e.printStackTrace();
                    return null;
                }
    }
    
    public void createConfig()
    {
        new File(ordner).mkdir();
        if(!configFile.exists())
        {
            try
            {
                configFile.createNewFile();
                config = loadConfig();
                config.set("Prefix", ("&b[Repair]"));
                config.save(configFile);
                config = loadConfig();
                config.set("version", version);
                config.save(configFile);
                config = loadConfig();
                config.set("Messages.not_enough_money", ("<Prefix> &7You don't have enough money!"));
                config.set("Messages.repair_message", ("<Prefix> &7Your <type> has been repaired for <cost> <currency>!"));
                config.set("Messages.create_successfull", ("<Prefix> &7The repair shop has been created successfully!"));
                config.set("Messages.repair_create_message", ("<Prefix> &7In this shop you can repair <type> for <cost> <currency>!"));
                config.set("Messages.no_permission", ("<Prefix> &7You don't have permission to do that!"));
                config.save(configFile);
               
            }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
        }
       
        config = loadConfig();
        
        if(isNewVersion())
        {
            try {
                config = loadConfig();
                config.set("version", version);
                config.set("Messages.no_permission", ("<Prefix> &7You don't have permission to do that!"));
                config.save(configFile);
            } catch (IOException ex) {
                Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public String not_enough_money (String Prefix)
    {
        String Money = config.getString("Messages.not_enough_money").replace("&", "§");
        return Money;
    }
    
    public String repair_message (String Prefix, String type, String cost, String currency)
    {
        String Money = config.getString("Messages.repair_message").replace("&", "§").replace("<type>", type).replace("<cost>", cost).replace("<currency>", currency).replace("<Prefix>", Prefix);
        return Money;
    }
    
    public String create_successfull (String Prefix)
    {
        String Money = config.getString("Messages.create_successfull").replace("&", "§").replace("<Prefix>", Prefix);
        return Money;
    }
    
    public String repair_create_message (String Prefix, String type, String cost, String currency)
    {
        String Money = config.getString("Messages.repair_create_message").replace("&", "§").replace("<type>", type).replace("<cost>", cost).replace("<currency>", currency).replace("<Prefix>", Prefix);
        return Money;
    }
    
    public String no_permission (String Prefix)
    {
        String Money = config.getString("Messages.no_permission").replace("&", "§").replace("<Prefix>", Prefix);
        return Money;
    } 
    
    public String Prefix ()
    {
        String Money = config.getString("Prefix").replace("&", "§");
        return Money;
    } 
    
    public boolean isNewVersion()
    {
        config = loadConfig();
        if(config.contains("version"))
        {
            double v = config.getDouble("version");
            if(v<version)
            {
                return true;
            }
        }
        else
        {
            return true;
        }
        return false;
    }
}
