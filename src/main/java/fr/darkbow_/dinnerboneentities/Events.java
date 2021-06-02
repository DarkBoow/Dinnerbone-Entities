package fr.darkbow_.dinnerboneentities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class Events implements Listener {
    private DinnerboneEntities main;

    public Events(DinnerboneEntities dinnerboneentities){main = dinnerboneentities;}

    @EventHandler
    public void onEntitySpawning(EntitySpawnEvent event){
        if(main.getConfig().getBoolean("automatic") && main.getDinnerboneEntitiesConfig().getBoolean(event.getEntityType().name())){
            event.getEntity().setCustomNameVisible(false);
            event.getEntity().setCustomName("Dinnerbone");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(event.getItem() != null){
            if(event.getItem().getType() == Material.STICK && event.getItem().hasItemMeta() && event.getItem().getItemMeta() != null && event.getItem().getItemMeta().hasDisplayName() && event.getItem().getItemMeta().getDisplayName().equals(Objects.requireNonNull(main.getConfig().getString("ToggleStick_Name")).replace("&", "§"))){
                if(event.getPlayer().hasPermission("dinnerboneentities.admin")){
                    if(event.getAction().name().contains("LEFT")){
                        for(World world : Bukkit.getWorlds()){
                            for(Entity entity : world.getEntities()){
                                if(entity != event.getPlayer()){
                                    if(main.getGlobalEntitiesConfig().contains(entity.getType().name()) && main.getGlobalEntitiesConfig().getBoolean(entity.getType().name())){
                                        entity.setVelocity(entity.getVelocity().setY(entity.getVelocity().getY() + 0.8));
                                    }
                                }
                            }
                        }
                    }

                    if(event.getAction().name().contains("RIGHT")){
                        event.getPlayer().performCommand("dinnerboneentities auto");
                    }
                }
            }
        }
    }
}