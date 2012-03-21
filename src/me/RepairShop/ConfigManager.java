/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.RepairShop;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    public double version = 2.0;
    public String M = "Resources.";
    public Map<String, Object> TempPlayerList;
    public HashMap<String, String> TypeList = new HashMap<String, String>();

    
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
                config = loadConfig();
                config.set(M+"DIAMOND_TOOLS"+".Amount", 3);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"GOLD_TOOLS"+".Amount", 3);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"IRON_TOOLS"+".Amount", 3);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"STONE_TOOLS"+".Amount", 3);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"WOOD_TOOLS"+".Amount", 3);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"DIAMOND_WEAPON"+".Amount", 4);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"GOLD_WEAPON"+".Amount", 4);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"IRON_WEAPON"+".Amount", 4);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"STONE_WEAPON"+".Amount", 4);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"WOOD_WEAPON"+".Amount", 4);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"BOW_WEAPON"+".Amount", 4);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"DIAMOND_ARMOR"+".Amount", 5);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"GOLD_ARMOR"+".Amount", 5);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"IRON_ARMOR"+".Amount", 5);
                config.save(configFile);
                config = loadConfig();
                config.set(M+"LEATHER_ARMOR"+".Amount", 5);
                config.save(configFile);
                
            }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
        }
        config = loadConfig();
        if(isNewVersion())
        {
            configFile.delete();
            System.out.println("[RepairShop] Updating configuraiotn...");
            this.createConfig();
        } 
        
        TempPlayerList = config.getValues(true);
        for(String s:TempPlayerList.keySet())
        {
            if(s.contains(M))
            {
                String type = s.replace(M, "").replace(".Amount", "");
                this.TypeList.put(type, type);
            }
        }
    }
    
    public int getAmount(String type)
    {
        config = loadConfig();
        int Amount = config.getInt(M+type.toUpperCase()+".Amount");
        return Amount;
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
