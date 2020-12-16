package me.M0dii.Freeze;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Config
{
    public static String NO_PERMISSION;
    public static String TO_STAFF_FREEZE;
    public static String TO_STAFF_UNFREEZE;
    public static String TO_PLAYER_FREEZE;
    public static String TO_PLAYER_UNFREEZE;
    public static String NOT_FROZEN;
    public static String CANT_FREEZE;
    public static boolean DENY_JUMP;
    
    public static void load(Main plugin)
    {
        FileConfiguration cfg = plugin.getConfig();
    
        NO_PERMISSION = format(cfg.getString("M0-Freeze.NoPermission"));
        TO_STAFF_FREEZE = format(cfg.getString("M0-Freeze.ToStaffFreeze"));
        TO_STAFF_UNFREEZE = format(cfg.getString("M0-Freeze.ToStaffUnfreeze"));
        TO_PLAYER_FREEZE = format(cfg.getString("M0-Freeze.ToPlayerFreeze"));
        TO_PLAYER_UNFREEZE = format(cfg.getString("M0-Freeze.ToPlayerUnfreeze"));
        NOT_FROZEN = format(cfg.getString("M0-Freeze.NotFrozen"));
        CANT_FREEZE = format(cfg.getString("M0-Freeze.CantFreeze"));
        DENY_JUMP = cfg.getBoolean("M0-Freeze.DenyJump");
    }
    
    private static String format(String text)
    {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
