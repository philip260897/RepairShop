/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.RepairShop;

import de.bananaco.bpermissions.imp.Permissions;
import org.anjocaido.groupmanager.GlobalGroups;
import org.anjocaido.groupmanager.GroupManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 *
 * @author Philip
 */
public class PermissionsManager {
    private final RepairShop plugin;

    public PermissionsManager(final RepairShop plugin) {
        this.plugin = plugin;
    }    
    
    
    public boolean hasPermission(Player player, String permission)
    {
        Plugin bPermissions = plugin.getServer().getPluginManager().getPlugin("bPermissions");
        Plugin bPermissionsEx = plugin.getServer().getPluginManager().getPlugin("PermissionsEx");
        Plugin EssEco = plugin.getServer().getPluginManager().getPlugin("PermissionsEx");
        if(bPermissions != null)
        {
            boolean hasPermission = Permissions.hasPermission(player, permission);
            return hasPermission;
        }
        if(bPermissionsEx != null)
        {
            PermissionManager PEX = PermissionsEx.getPermissionManager();
            boolean hasPermission = PEX.has(player, permission);
            return hasPermission;
        }
        if(EssEco != null)
        {
            GlobalGroups gg = GroupManager.getGlobalGroups();
            boolean hasPermissions = gg.hasPermission(player.getName(), permission);
            if(hasPermissions == false)
            {
                
            }
            else
            {
                return hasPermissions;
            }
        }
        if(bPermissionsEx == null && bPermissions == null && EssEco == null)
        {
            if(player.hasPermission(permission))
            {
                return true;
            }
        }       
        return false;
    }
    
}
