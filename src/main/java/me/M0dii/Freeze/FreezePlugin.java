package me.m0dii.freeze;

import me.m0dii.freeze.commands.FreezeCommand;
import me.m0dii.freeze.commands.UnfreezeCommand;
import me.m0dii.freeze.utils.UpdateChecker;
import org.bstats.bukkit.Metrics;
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
import java.util.List;
import java.util.UUID;

public class FreezePlugin extends JavaPlugin
{
    private static FreezePlugin plugin;
    
    private final PluginManager manager;
    
    private final List<UUID> frozen = new ArrayList<>();
    
    public FreezePlugin()
    {
        this.manager = getServer().getPluginManager();
    }
    
    public List<UUID> getFrozenPlayers()
    {
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
    
    private Config cfg;
    
    public Config getCfg()
    {
        return this.cfg;
    }
    
    public void onEnable()
    {
        loadConfiguration();
        
        this.cfg = new Config(this);
    
        getCommand("freeze").setExecutor(new FreezeCommand(this));
        getCommand("unfreeze").setExecutor(new UnfreezeCommand(this));
        
        this.manager.registerEvents(new FreezeListener(this), this);
        
        setupMetrics();
        
        checkForUpdates();
    }
    
    private void setupMetrics()
    {
        Metrics metrics = new Metrics(this, 14656);
    }
    
    private void checkForUpdates()
    {
        new UpdateChecker(this, 86789).getVersion(ver ->
        {
            if (!this.getDescription().getVersion().equalsIgnoreCase(ver))
            {
                getLogger().info("You are running an outdated version of M0-Freeze");
                getLogger().info("You are using: " + getDescription().getVersion() + ".");
                getLogger().info("Latest version: " + ver + ".");
                getLogger().info("You can download the latest version on Spigot:");
                getLogger().info("https://www.spigotmc.org/resources/86789/");
            }
        });
    }
    
    private void loadConfiguration()
    {
        this.configFile = new File(getDataFolder(), "config.yml");
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
        
        if(!this.configFile.exists())
        {
            this.configFile.getParentFile().mkdirs();
        
            copy(getResource("config.yml"), configFile);
        }
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
