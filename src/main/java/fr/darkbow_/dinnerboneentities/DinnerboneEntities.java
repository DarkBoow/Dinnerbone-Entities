package fr.darkbow_.dinnerboneentities;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class DinnerboneEntities extends JavaPlugin {

    private DinnerboneEntities instance;

    public DinnerboneEntities getInstance() {
        return instance;
    }

    private File dinnerboneentitiesfile;
    private FileConfiguration dinnerboneentitiesconfig;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        createDinnerboneEntitiesFile();

        getServer().getPluginManager().registerEvents(new Events(this), this);

        for(EntityType etype : EntityType.values()){
            if(etype != EntityType.PLAYER){
                getDinnerboneEntitiesConfig().set(etype.name(), true);
            }
        }

        try {
            dinnerboneentitiesconfig.save(dinnerboneentitiesfile);
        } catch (IOException e){
            e.printStackTrace();
        }

        if(getConfig().getBoolean("automatic")){
            for(World world : Bukkit.getWorlds()){
                for(Entity entity : world.getEntities()){
                    if(!(entity instanceof Player)){
                        if(entity.getCustomName() == null || (entity.getCustomName() != null && !entity.getCustomName().equals("Dinnerbone") && getConfig().getBoolean("RenameAlreadyRenamedEntities"))){
                            entity.setCustomName("Dinnerbone");
                            entity.setCustomNameVisible(false);
                        }
                    }
                }
            }
        }

        System.out.println("[Dinnerbone Entities] Plugin ON!");
    }

    @Override
    public void onDisable() {
        System.out.println("[Dinnerbone Entities] Plugin OFF!");
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
}