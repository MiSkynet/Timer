package me.miskynet.timer.utils;

import me.miskynet.timer.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Utils {

    static Timer timer = Main.getInstance().timer;

    // create a message component to use in #sendMessage();
    public static Component createMessage(String string) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(string);
        return component;
    }

    // format and display the timer
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

            // Start: Code by ChatGPT (and my shit mixed in)
            StringBuilder sb = new StringBuilder();

            String week = weeks <= 9 ? "0" + weeks + "w " : weeks + "w ";
            if (totalTime >= WEEK)   sb.append(week);

            String day = days <= 9 ? "0" + days + "d " : days + "d ";
            if (totalTime >= DAY)    sb.append(day);

            String hour = hours <= 9 ? "0" + hours + "h " : hours + "h ";
            if (totalTime >= HOUR)   sb.append(hour);

            String minute = minutes <= 9 ? "0" + minutes + "m " : minutes + "m ";
            if (totalTime >= MINUTE) sb.append(minute);

            String second = seconds <= 9 ? "0" + seconds + "s " : seconds + "s ";
            sb.append(second);
            // End: Code by ChatGPT

            Component actionbar = LegacyComponentSerializer.legacyAmpersand().deserialize(timer.getTimerColor() + sb);
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
