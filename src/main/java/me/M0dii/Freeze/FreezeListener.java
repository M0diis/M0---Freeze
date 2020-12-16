package me.M0dii.Freeze;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.UUID;

public class FreezeListener implements Listener
{
    private final Main plugin;
    
    
    
    public FreezeListener(Main plugin)
    {
        this.plugin = plugin;
    }
    
    private boolean has(Player p)
    {
        return this.plugin.getFrozenPlayers().contains(p.getUniqueId());
    }
    
    @EventHandler
    public void cancelOnMoveEvent(PlayerMoveEvent e)
    {
        if(has(e.getPlayer()))
        {
            e.setTo(e.getFrom());
            
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void cancelOnJumpEvent(PlayerJumpEvent e)
    {
        if(has(e.getPlayer()))
        {
            if(Config.DENY_JUMP)
            {
                e.setCancelled(true);
            }
        }
    }
}
