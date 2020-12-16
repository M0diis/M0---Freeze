package me.M0dii.Freeze;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin
{
    private static Main plugin;
    
    private PluginManager manager;
    
    private boolean loaded;
    
    private final ArrayList<UUID> frozen;
    
    public Main()
    {
        this.manager = getServer().getPluginManager();
        
        this.frozen = new ArrayList<>();
    }
    
    public ArrayList<UUID> getFrozenPlayers() {
        return this.frozen;
    }
    
    public void freezePlayer(Player p)
    {
        this.frozen.add(p.getUniqueId());
    }
    
    public void unfreezePlayer(Player p)
    {
        this.frozen.remove(p.getUniqueId());
    }
    
    FileConfiguration config = null;
    File configFile = null;
    
    public void onEnable()
    {
        this.configFile = new File(getDataFolder(), "config.yml");
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    
        if(!this.configFile.exists())
        {
            this.configFile.getParentFile().mkdirs();
        
            copy(getResource("config.yml"), configFile);
        }
        
        Config.load(this);
    
        getCommand("freeze").setExecutor(new FreezeCommand(this));
        
        this.manager.registerEvents(new FreezeListener(this), this);
    }
    
    private void copy(InputStream in, File file)
    {
        if(in == null)
        {
            this.getLogger().warning("Cannot copy, resource null");
            
            return;
        }
        
        try
        {
            OutputStream out = new FileOutputStream(file);
            
            byte[] buf = new byte[1024];
            
            int len;
            
            while((len = in.read(buf)) > 0)
            {
                out.write(buf, 0, len);
            }
            
            out.close();
            in.close();
        }
        catch(Exception e)
        {
            this.getLogger().warning("Error copying resource: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public void onDisable()
    {
        this.manager.disablePlugin(this);
    }
}
