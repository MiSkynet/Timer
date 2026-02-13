package me.miskynet.timer.utils;

import me.miskynet.timer.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomConfig {

    // create the maps with the configs and the files
    private static final Map<String, FileConfiguration> configs = new HashMap<>();
    private static final Map<String, File> files = new HashMap<>();

    // this method sets up a config file with a name
    public static void setup(String fileName) {

        // get the file from the plugin
        File file = new File(Main.getInstance().getDataFolder(), fileName);

        // create the file in case it does not exist already
        if (!file.exists()) {
            Main.getInstance().saveResource(fileName, false);
        }

        // put the settings from the plugin config into the user config
        files.put(fileName, file);

        // put the config into the map
        configs.put(fileName, YamlConfiguration.loadConfiguration(file));
    }

    // get a config
    public static FileConfiguration get(String fileName) {
        return configs.get(fileName);
    }

    // save a config when something was changed
    public static void save(String fileName) {
        try {
            configs.get(fileName).save(files.get(fileName));
        } catch (IOException e) {
            Bukkit.getLogger().warning("Could not save " + fileName);
        }
    }

    // reload a config (used for the reload command in general)
    public static void reload(String fileName) {
        configs.put(fileName, YamlConfiguration.loadConfiguration(files.get(fileName)));
    }
}