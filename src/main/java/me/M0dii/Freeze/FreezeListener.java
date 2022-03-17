package me.m0dii.freeze;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener implements Listener
{
    private final FreezePlugin plugin;
    private final Config cfg;
    
    public FreezeListener(FreezePlugin plugin)
    {
        this.plugin = plugin;
        this.cfg = plugin.getCfg();
    }
    
    private boolean isFrozen(Player p)
    {
        return this.plugin.getFrozenPlayers().contains(p.getUniqueId());
    }
    
    @EventHandler
    public void onMoveEvent(PlayerMoveEvent e)
    {
        if(isFrozen(e.getPlayer()))
        {
            e.setTo(e.getFrom());
            
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onJumpEvent(PlayerJumpEvent e)
    {
        if(isFrozen(e.getPlayer()))
        {
            if(cfg.isDenyJump())
            {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void cancelCommands(PlayerCommandPreprocessEvent e)
    {
        if(!cfg.isBlockCmds())
        {
            return;
        }
        
        String cmd = e.getMessage().split(" ")[0];
        
        if(cfg.isBlockWhitelist())
        {
            for(String blocked : cfg.getBlockedCmds())
            {
                if(cmd.equalsIgnoreCase(blocked))
                {
                    e.setCancelled(true);
            
                    e.getPlayer().sendMessage(cfg.getDenyMessage());
            
                    break;
                }
            }
        }
        else
        {
            boolean block = true;
            
            for(String blocked : cfg.getBlockedCmds())
            {
                if(cmd.equalsIgnoreCase(blocked))
                {
                    block = false;
                    
                    break;
                }
            }
            
            if(block)
            {
                e.setCancelled(true);
                
                e.getPlayer().sendMessage(cfg.getDenyMessage());
            }
        }

    }
}
