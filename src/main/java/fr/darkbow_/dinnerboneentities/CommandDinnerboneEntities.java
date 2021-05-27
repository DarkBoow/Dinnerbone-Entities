package fr.darkbow_.dinnerboneentities;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Entity;

import java.io.IOException;
import java.util.Objects;

public class CommandDinnerboneEntities implements CommandExecutor {
    private final DinnerboneEntities main;

    public CommandDinnerboneEntities(DinnerboneEntities dinnerboneentities) {this.main = dinnerboneentities;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(args.length == 0){
            sender.sendMessage(Objects.requireNonNull(main.getMessagesConfig().getString("WrongCommand")).replace("&", "§"));
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("reload")){
                try {
                    main.getDinnerboneEntitiesConfig().load(main.getDinnerboneEntitiesFile());
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }

                try {
                    main.getGlobalEntitiesConfig().load(main.getGlobalEntitiesFile());
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }

                try {
                    main.getMessagesConfig().load(main.getMessagesFile());
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }

                sender.sendMessage(Objects.requireNonNull(main.getMessagesConfig().getString("PluginReloaded")).replace("&", "§"));
            } else if(args[0].equalsIgnoreCase("help")){
                sender.sendMessage(Objects.requireNonNull(main.getMessagesConfig().getString("HelpCommand")).replace("&", "§"));
            } else if(args[0].equalsIgnoreCase("auto") || args[0].equalsIgnoreCase("automatic") || args[0].equalsIgnoreCase("automatique")){
                main.getConfig().set("automatic", !main.getConfig().getBoolean("automatic"));
                main.saveDefaultConfig();

                if(main.getConfig().getBoolean("global_command_action")){
                    for(World world : Bukkit.getWorlds()){
                        for(Entity entity : world.getEntities()){
                            if(main.getGlobalEntitiesConfig().getBoolean(entity.getType().name())){
                                if(main.getConfig().getBoolean("automatic")){
                                    if(entity.getCustomName() == null || (entity.getCustomName() != null && !entity.getCustomName().equals("Dinnerbone") && main.getConfig().getBoolean("RenameAlreadyRenamedEntities"))){
                                        entity.setCustomNameVisible(false);
                                        entity.setCustomName("Dinnerbone");
                                    }
                                } else {
                                    if(Objects.equals(entity.getCustomName(), "Dinnerbone")){
                                        entity.setCustomNameVisible(false);
                                        entity.setCustomName(null);
                                    }
                                }
                            }
                        }
                    }
                }

                sender.sendMessage(Objects.requireNonNull(main.getMessagesConfig().getString("AutomaticToggle")).replace("%oldvalue%", Objects.requireNonNull(main.getMessagesConfig().getString("Boolean_" + !main.getConfig().getBoolean("auto")))).replace("%value%", Objects.requireNonNull(main.getMessagesConfig().getString("Boolean_" + !main.getConfig().getBoolean("auto")))).replace("&", "§"));
            } else {
                sender.sendMessage(Objects.requireNonNull(main.getMessagesConfig().getString("WrongCommand")).replace("&", "§"));
            }
        }

        if(args.length > 1){
            sender.sendMessage(Objects.requireNonNull(main.getMessagesConfig().getString("WrongCommand")).replace("&", "§"));
        }

        return false;
    }
}
