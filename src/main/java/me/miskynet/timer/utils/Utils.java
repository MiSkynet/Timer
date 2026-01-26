package me.miskynet.timer.utils;

import me.miskynet.timer.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Utils {

    static Timer timer = Main.getInstance().timer;

    public static Component createMessage(String string) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(string);
        return component;
    }

    public static void displayTimer() {

        int time = timer.getTime();

        int weeks = 0;
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        while (time / 60 / 60 / 24 / 7 >= 1) {
            weeks++;
            time = time - (60 * 60 * 24 * 7);
        }

        while (time / 60 / 60 / 24 >= 1) {
            days++;
            time = time - (60 * 60 * 24);
        }

        while (time / 60 / 60 >= 1) {
            hours++;
            time = time - (60 * 60);
        }

        while (time / 60  >= 1) {
            minutes++;
            time = time - (60);
        }

        seconds = time;

        for (Player player : Bukkit.getOnlinePlayers()) {

            String string = seconds + " " + minutes + " " + hours + " " + " " + days + " " + " " + weeks;
            Component actionbar = LegacyComponentSerializer.legacyAmpersand().deserialize(string);
            player.sendActionBar(actionbar);
        }

    }

    public static void startDisplaying() {
        new BukkitRunnable() {
            @Override
            public void run() {
                displayTimer();
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L);
    }

}
