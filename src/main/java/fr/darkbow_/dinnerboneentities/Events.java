package fr.darkbow_.dinnerboneentities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
            if(event.getItem().getType() == Material.STICK && event.getItem().hasItemMeta() && event.getItem().getItemMeta() != null && event.getItem().getItemMeta().hasDisplayName() && event.getItem().getItemMeta().getDisplayName().equals(main.getConfig().getString("ToggleStick_Name"))){
                if(event.getAction().name().contains("RIGHT") && event.getPlayer().hasPermission("dinnerboneentities.admin")){
                    Bukkit.broadcastMessage("Yes");
                    event.getPlayer().performCommand("dinnerboneentities auto");
                }
            }
        }
    }
}