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

        // Start: Code by ChatGPT
        int totalTime = timer.getTime();
        int time = totalTime;

        final int MINUTE = 60;
        final int HOUR   = 60 * MINUTE;
        final int DAY    = 24 * HOUR;
        final int WEEK   = 7 * DAY;

        int weeks = time / WEEK;
        time %= WEEK;

        int days = time / DAY;
        time %= DAY;

        int hours = time / HOUR;
        time %= HOUR;

        int minutes = time / MINUTE;
        int seconds = time % MINUTE;
        // End: Code by ChatGPT

        for (Player player : Bukkit.getOnlinePlayers()) {

            // Start: Code by ChatGPT
            StringBuilder sb = new StringBuilder();

            if (totalTime >= WEEK)   sb.append(weeks).append("w ");
            if (totalTime >= DAY)    sb.append(days).append("d ");
            if (totalTime >= HOUR)   sb.append(hours).append("h ");
            if (totalTime >= MINUTE) sb.append(minutes).append("m ");

            sb.append(seconds).append("s");
            // End: Code by ChatGPT

            Component actionbar = LegacyComponentSerializer.legacyAmpersand().deserialize(sb.toString());
            player.sendActionBar(actionbar);
        }
    }

    // start the counting and the displaying
    public static void start() {

        new BukkitRunnable() {
            @Override
            public void run() {
                if (timer.running) {

                    if (timer.getCountDirection().equals(Timer.countDirection.UP)) {
                        timer.setTime(timer.getTime() + 1);
                    }

                    else if (timer.getCountDirection().equals(Timer.countDirection.DOWN)) {
                        if (!(timer.getTime() <= 0)) {
                            timer.setTime(timer.getTime() - 1);
                        }else {
                            timer.setRunning(false);
                        }
                    }
                }

                displayTimer();
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L);

    }

}
