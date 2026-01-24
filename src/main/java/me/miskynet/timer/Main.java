package me.miskynet.timer;

import me.miskynet.timer.utils.Timer;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Timer timer = new Timer();

    @Override
    public void onEnable() {
        // Plugin startup logic

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
