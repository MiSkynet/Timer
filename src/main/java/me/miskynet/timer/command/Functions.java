package me.miskynet.timer.command;

import me.miskynet.timer.Main;
import me.miskynet.timer.utils.Timer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Functions {

    static Timer timer = Main.getInstance().timer;

    public static void startTimer() {
        timer.setRunning(true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (timer.getTime() == 0) {
                player.sendMessage("The timer was started!");
            }else {
                player.sendMessage("The timer was continued!");
            }
        }
    }

    public static void pauseTimer() {
        timer.setRunning(false);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage("The timer was paused!");
        }
    }

    public static void resetTimer() {
        timer.setRunning(false);
        timer.setTime(0);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage("The timer was reseted!");
        }
    }

    public static void setTime(String[] string) {

        int time = 0;

        for (String currentTime : string) {

            if (currentTime.endsWith("w")) {
                currentTime = currentTime.replaceAll("w", "");

                time = Integer.parseInt(currentTime) * 60 * 60 * 24 * 7;
            }else if (currentTime.endsWith("d")) {
                currentTime = currentTime.replaceAll("d", "");

                time = Integer.parseInt(currentTime) * 60 * 60 * 24;
            } else if (currentTime.endsWith("h")) {
                currentTime = currentTime.replaceAll("h", "");

                time = Integer.parseInt(currentTime) * 60 * 60;
            } else if (currentTime.endsWith("m")) {
                currentTime = currentTime.replaceAll("m", "");

                time = Integer.parseInt(currentTime) * 60;
            } else if (currentTime.endsWith("s")) {
                currentTime = currentTime.replaceAll("s", "");

                time = Integer.parseInt(currentTime);

            }

        }
        timer.setTime(time);
    }



}
