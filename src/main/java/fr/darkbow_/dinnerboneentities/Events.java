package fr.darkbow_.dinnerboneentities;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class Events implements Listener {
    private DinnerboneEntities main;

    public Events(DinnerboneEntities sampleplugin){main = sampleplugin;}

    @EventHandler
    public void onEntitySpawning(EntitySpawnEvent event){
        if(main.getConfig().getBoolean("automatic") && main.getDinnerboneEntitiesConfig().getBoolean(event.getEntityType().name())){
            if(main.getConfig().getBoolean("RenameAlreadyRenamedEntities")){
                event.getEntity().setCustomName("Dinnerbone");
                event.getEntity().setCustomNameVisible(false);
            }
        }
    }
}