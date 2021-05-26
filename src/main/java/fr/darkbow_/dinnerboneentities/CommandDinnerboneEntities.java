package fr.darkbow_.dinnerboneentities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

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
                    main.getMessagesConfig().load(main.getMessagesFile());
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }

                sender.sendMessage(Objects.requireNonNull(main.getMessagesConfig().getString("PluginReloaded")).replace("&", "§"));
            } else {
                sender.sendMessage(Objects.requireNonNull(main.getMessagesConfig().getString("WrongCommand")).replace("&", "§"));
            }
        }

        return false;
    }
}
