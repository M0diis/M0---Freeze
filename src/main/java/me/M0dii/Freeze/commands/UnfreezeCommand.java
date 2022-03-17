package me.m0dii.freeze.commands;

import me.m0dii.freeze.Config;
import me.m0dii.freeze.FreezePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class UnfreezeCommand implements CommandExecutor
{
    private final FreezePlugin plugin;
    private final Config cfg;
    
    public UnfreezeCommand(FreezePlugin plugin)
    {
        this.plugin = plugin;
        this.cfg = plugin.getCfg();
    }
    
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command,
                             @Nonnull String label, @Nonnull String[] args)
    {
        if(!(sender instanceof Player))
        {
            return true;
        }
        
        Player s = (Player)sender;
    
        if(!s.hasPermission("m0freeze.unfreeze"))
        {
            s.sendMessage(cfg.getNoPermission());
    
            return true;
        }
    
        if(args.length != 1)
        {
            s.sendMessage(cfg.getUsageUnfreeze());
            
            return true;
        }
        
        Player p = Bukkit.getPlayer(args[0]);
    
        if(p != null)
        {
            if(this.plugin.getFrozenPlayers().contains(p.getUniqueId()))
            {
                this.plugin.unfreezePlayer(p);
                
                s.sendMessage(cfg.getToStaffUnfreeze());
                p.sendMessage(cfg.getToPlayerUnfreeze());
            }
            else
            {
                s.sendMessage(cfg.getNotFrozen());
            }
        }
    
        return true;
    }
}
