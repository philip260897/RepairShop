/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.RepairShop;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;


/**
 *
 * @author Philip
 */
public class ConfigManager {
    public final RepairShop plugin;
    public ConfigManager(final RepairShop plugin)
    {
        this.plugin = plugin;
    }  
    private static String ordner = "plugins/RepairShop";
    private static File configFile = new File(ordner + File.separator + "config.yml");
    private static YamlConfiguration config;
    public double version = 1.1;

    
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
                config.set("currency", ("Dollars"));
                config.save(configFile);
                config = loadConfig();
                config.set("Action", "RIGHT_CLICK");
                config.save(configFile);
                config = loadConfig();
                config.set("version", version);
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
                config.set("version", null);
                config.save(configFile);
                config = loadConfig();
                if(!config.contains("Action"))
                {
                    System.out.println("[RepairShop] Updating Config...");
                    config.set("Action", "RIGHT_CLICK");
                    config.save(configFile);
                }
                config = loadConfig();
                config.set("version", version);
                config.save(configFile);
            } catch (IOException ex) {
                Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    public String Currency()
    {
        String Money = config.getString("currency");
        return Money;
    }
    
    public String getAction()
    {
        String Action = config.getString("Action");
        return Action;
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
