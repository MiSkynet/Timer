package me.miskynet.timer;

import me.miskynet.timer.command.CommandManager;
import me.miskynet.timer.command.TabCompleter;
import me.miskynet.timer.utils.Timer;
import me.miskynet.timer.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public Timer timer = new Timer();

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("timer").setExecutor(new CommandManager());
        getCommand("timer").setTabCompleter(new TabCompleter());

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Utils.start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // return the main
    public static Main getInstance() {
        return Main.getPlugin(Main.class);
    }

}
