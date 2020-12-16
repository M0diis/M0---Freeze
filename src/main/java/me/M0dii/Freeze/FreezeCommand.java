package me.M0dii.Freeze;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class FreezeCommand implements CommandExecutor
{
    public final Main plugin;
    
    public FreezeCommand(Main plugin)
    {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command,
                             @Nonnull String label, @Nonnull String[] args)
    {
        if(sender instanceof Player)
        {
            Player s = (Player)sender;
    
            if(args.length == 1)
            {
                Player p = Bukkit.getPlayer(args[0]);
                
                if(p != null)
                {
                    if(!this.plugin.getFrozenPlayers().contains(p.getUniqueId()))
                    {
                        this.plugin.freezePlayer(p);
                        
                        s.sendMessage(Config.TO_STAFF_FREEZE);
                        p.sendMessage(Config.TO_PLAYER_FREEZE);
                    }
                    else
                    {
                        this.plugin.unfreezePlayer(p);
    
                        s.sendMessage(Config.TO_STAFF_UNFREEZE);
                        p.sendMessage(Config.TO_PLAYER_UNFREEZE);
                    }
                }
            }
        }

        return false;
    }
}
