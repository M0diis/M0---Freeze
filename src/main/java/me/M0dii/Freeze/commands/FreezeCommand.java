package me.m0dii.freeze.commands;

import me.m0dii.freeze.Config;
import me.m0dii.freeze.FreezePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class FreezeCommand implements CommandExecutor
{
    private final FreezePlugin plugin;
    private final Config cfg;
    
    public FreezeCommand(FreezePlugin plugin)
    {
        this.plugin = plugin;
        this.cfg = plugin.getCfg();
    }
    
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command,
                             @Nonnull String label, @Nonnull String[] args)
    {
        if(sender instanceof Player)
        {
            Player s = (Player)sender;
    
            if(!s.hasPermission("m0freeze.command.freeze"))
            {
                s.sendMessage(cfg.getNoPermission());
        
                return true;
            }
    
            if(args.length != 1)
            {
                s.sendMessage(cfg.getUsageFreeze());
                
                return true;
            }
            
            Player p = Bukkit.getPlayer(args[0]);
    
            if(p == null)
            {
                return true;
            }
            
            if(p.hasPermission("m0freeze.bypass"))
            {
                s.sendMessage(cfg.getCantFreeze());
                
                return true;
            }
    
            if(!this.plugin.getFrozenPlayers().contains(p.getUniqueId()))
            {
                this.plugin.freezePlayer(p);
                
                s.sendMessage(cfg.getToStaffFreeze());
                p.sendMessage(cfg.getToPlayerFreeze());
            }
            else
            {
                this.plugin.unfreezePlayer(p);

                s.sendMessage(cfg.getToStaffUnfreeze());
                p.sendMessage(cfg.getToPlayerUnfreeze());
            }
        }

        return true;
    }
}
