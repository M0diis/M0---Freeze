package me.m0dii.freeze;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config
{
    private String noPermission;
    
    private String toStaffFreeze;
    private String toStaffUnfreeze;
    
    private String toPlayerFreeze;
    private String toPlayerUnfreeze;
    private String notFrozen;
    private String cantFreeze;
    
    private String denyMessage;
    private List<String> blockedCmds;
    
    private boolean denyJump;
    private boolean blockCmds;
    private boolean blockIsWhitelist;
    
    private String usageFreeze;
    private String usageUnfreeze;
    
    public Config(FreezePlugin plugin)
    {
        load(plugin);
    }

    public void load(FreezePlugin plugin)
    {
        FileConfiguration cfg = plugin.getConfig();
    
        noPermission = format(cfg.getString("no-permission"));
        toStaffFreeze = format(cfg.getString("M0-Freeze.ToStaffFreeze"));
        toStaffUnfreeze = format(cfg.getString("M0-Freeze.ToStaffUnfreeze"));
        toPlayerFreeze = format(cfg.getString("M0-Freeze.ToPlayerFreeze"));
        toPlayerUnfreeze = format(cfg.getString("M0-Freeze.ToPlayerUnfreeze"));
        notFrozen = format(cfg.getString("not-frozen"));
        cantFreeze = format(cfg.getString("cant-freeze"));
        denyJump = cfg.getBoolean("deny-jump");
        denyMessage = format(cfg.getString("deny-message"));
        
        blockedCmds = cfg.getStringList("block-commands.commands");
        blockCmds = cfg.getBoolean("block-commands.enabled");
        blockIsWhitelist = cfg.getBoolean("block-commands.whitelist");
        
        usageFreeze = format(cfg.getString("usage.freeze"));
        usageUnfreeze = format(cfg.getString("usage.unfreeze"));
    }
    
    private static String format(String text)
    {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public String getNoPermission()
    {
        return noPermission;
    }
    public String getToStaffFreeze()
    {
        return toStaffFreeze;
    }
    public String getToStaffUnfreeze()
    {
        return toStaffUnfreeze;
    }
    public String getToPlayerFreeze()
    {
        return toPlayerFreeze;
    }
    public String getToPlayerUnfreeze()
    {
        return toPlayerUnfreeze;
    }
    public String getNotFrozen()
    {
        return notFrozen;
    }
    public String getCantFreeze()
    {
        return cantFreeze;
    }
    public String getDenyMessage()
    {
        return denyMessage;
    }
    public List<String> getBlockedCmds()
    {
        return blockedCmds;
    }
    public boolean isDenyJump()
    {
        return denyJump;
    }
    public boolean isBlockCmds()
    {
        return blockCmds;
    }
    public String getUsageFreeze()
    {
        return usageFreeze;
    }
    public String getUsageUnfreeze()
    {
        return usageUnfreeze;
    }
    public boolean isBlockWhitelist()
    {
        return blockIsWhitelist;
    }
}
