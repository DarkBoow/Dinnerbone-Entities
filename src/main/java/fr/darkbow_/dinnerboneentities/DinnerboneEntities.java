package fr.darkbow_.dinnerboneentities;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class DinnerboneEntities extends JavaPlugin {

    private DinnerboneEntities instance;

    public DinnerboneEntities getInstance() {
        return instance;
    }

    private File messagesfile;
    private FileConfiguration messagesconfig;

    private File dinnerboneentitiesfile;
    private FileConfiguration dinnerboneentitiesconfig;

    private File globalentitiesfile;
    private FileConfiguration globalentitiesconfig;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        createDinnerboneEntitiesFile();
        createMessagesFile();
        createGlobalEntitiesFile();

        getServer().getPluginManager().registerEvents(new Events(this), this);

        if(getConfig().getBoolean("automatic")){
            for(World world : Bukkit.getWorlds()){
                for(Entity entity : world.getEntities()){
                    if(!(entity instanceof Player)){
                        if(entity.getCustomName() == null || (entity.getCustomName() != null && !entity.getCustomName().equals("Dinnerbone") && getConfig().getBoolean("RenameAlreadyRenamedEntities") && getDinnerboneEntitiesConfig().getBoolean(entity.getType().name()))){
                            entity.setCustomNameVisible(false);
                            entity.setCustomName("Dinnerbone");
                        }
                    }
                }
            }
        }

        Objects.requireNonNull(getCommand("dinnerboneentities")).setExecutor(new CommandDinnerboneEntities(this));

        System.out.println(Objects.requireNonNull(getMessagesConfig().getString("PluginOn")).replace("&", "§"));
    }

    @Override
    public void onDisable() {
        System.out.println(Objects.requireNonNull(getMessagesConfig().getString("PluginOff")).replace("&", "§"));
    }

    public FileConfiguration getDinnerboneEntitiesConfig(){
        return this.dinnerboneentitiesconfig;
    }

    public File getDinnerboneEntitiesFile(){
        return this.dinnerboneentitiesfile;
    }

    private void createDinnerboneEntitiesFile(){
        dinnerboneentitiesfile = new File(getDataFolder(), "entities.yml");
        if(!dinnerboneentitiesfile.exists()){
            dinnerboneentitiesfile.getParentFile().mkdirs();
            saveResource("entities.yml", false);
        }

        dinnerboneentitiesconfig = new YamlConfiguration();
        try {
            dinnerboneentitiesconfig.load(dinnerboneentitiesfile);
        } catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }

    public FileConfiguration getGlobalEntitiesConfig(){
        return this.globalentitiesconfig;
    }

    public File getGlobalEntitiesFile(){
        return this.globalentitiesfile;
    }

    private void createGlobalEntitiesFile(){
        globalentitiesfile = new File(getDataFolder(), "globalentities.yml");
        if(!globalentitiesfile.exists()){
            globalentitiesfile.getParentFile().mkdirs();
            saveResource("globalentities.yml", false);
        }

        globalentitiesconfig = new YamlConfiguration();
        try {
            globalentitiesconfig.load(globalentitiesfile);
        } catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }

    public FileConfiguration getMessagesConfig(){
        return this.messagesconfig;
    }

    public File getMessagesFile(){
        return this.messagesfile;
    }

    private void createMessagesFile(){
        messagesfile = new File(getDataFolder(), "messages.yml");
        if(!messagesfile.exists()){
            messagesfile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }

        messagesconfig = new YamlConfiguration();
        try {
            messagesconfig.load(messagesfile);
        } catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }
}