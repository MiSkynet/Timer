package me.miskynet.timer.utils;

import me.miskynet.timer.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TimerManager {

    static Timer timer = Main.getInstance().timer;

    static List<String> uniqFormats = Arrays.asList("weeks", "days", "hours", "minutes", "seconds");

    static final int MINUTE = 60;
    static final int HOUR   = 60 * MINUTE;
    static final int DAY    = 24 * HOUR;
    static final int WEEK   = 7 * DAY;

    // send the timer to every player
    private static void sendTimerToPlayers() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            Component actionbar = LegacyComponentSerializer.legacyAmpersand().deserialize(timer.getTimerColor() + getTimerFormat());
            player.sendActionBar(actionbar);
        });
    }

    // get the uniq format of each timer section
    private static String getTimerFormat() {

        // get the time that has already passed
        HashMap<String, Integer> times = calculateTimes();

        // get the "master format" from the config
        // this will be the format that will be shown in the actionbar
        String masterFormat = Main.getInstance().getConfig().getString("display.master-format");

        // this variable say if before a number was set, if yes, set all following numbers, if not, check if
        // they have to be set or if they are empty
        Boolean numberAlreadySet = false;

        for (String uniqFormat : uniqFormats) {

            // the "uniq string" from the config
            String uniqConfigFormat = Main.getInstance().getConfig().getString("display." + uniqFormat + ".format");

            // the boolean weather there should be an additional zero or not
            Boolean uniqZeroBoolean = Main.getInstance().getConfig().getBoolean("display." + uniqFormat + ".zero");

            // if the uniq time is greater than 0
            if (times.get(uniqFormat) > 0 || numberAlreadySet) {

                if (uniqZeroBoolean && times.get(uniqFormat) <= 9) {
                    uniqConfigFormat = uniqConfigFormat.replace("%" + uniqFormat + "%", "0" + times.get(uniqFormat));
                }else {
                    uniqConfigFormat = uniqConfigFormat.replace("%" + uniqFormat + "%", "" + times.get(uniqFormat));
                }

                numberAlreadySet = true;

            }else {
                uniqConfigFormat = "";
            }

            // return the "master format" that will be used in the actionbar
            masterFormat = masterFormat.replace("%" + uniqFormat + "%", uniqConfigFormat);

        }

        return masterFormat;
    }

    // calculate the weeks, days, hours etc. that has already passed
    private static HashMap<String, Integer> calculateTimes() {
        HashMap<String, Integer> times = new HashMap<>();

        int time = timer.getTime();

        times.put("weeks", time / WEEK);
        time %= WEEK;

        times.put("days", time / DAY);
        time %= DAY;

        times.put("hours", time / HOUR);
        time %= HOUR;

        times.put("minutes", time / MINUTE);
        time %= MINUTE;

        times.put("seconds", time);

        return times;
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

                sendTimerToPlayers();
                timer.saveTime();
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L);

    }

}
