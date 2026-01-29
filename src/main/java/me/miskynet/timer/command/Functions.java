package me.miskynet.timer.command;

import me.miskynet.timer.Main;
import me.miskynet.timer.utils.Timer;
import me.miskynet.timer.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Functions {

    static Timer timer = Main.getInstance().timer;

    // start the timer
    public static void startTimer(CommandSender commandSender) {

        if (timer.getCountDirection().equals(Timer.countDirection.DOWN)) {
            if (!(timer.getTime() >= 1)) {
                String string = "The time on the timer must be at least on second! Please set it with /timer set 1s";
                commandSender.sendMessage(Utils.createMessage(string));
                return;
            }
        }

        timer.setRunning(true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (timer.getTime() == 0) {
                player.sendMessage("The timer was started!");
            }else {
                player.sendMessage("The timer was continued!");
            }
        }
    }

    // pause the timer
    public static void pauseTimer() {
        timer.setRunning(false);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage("The timer was paused!");
        }
    }

    // reset the timer
    public static void resetTimer() {
        timer.setRunning(false);
        timer.setTime(0);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage("The timer was reseted!");
        }
    }

    // set or add a specific time to the timer
    public static void setAddTime(String[] string) {

        int time = 0;

        for (int i = 1; i < string.length; i++) {
            if (string[i].endsWith("w")) {
                string[i] = string[i].replaceAll("w", "");

                time += Integer.parseInt(string[i]) * 60 * 60 * 24 * 7;
            }else if (string[i].endsWith("d")) {
                string[i] = string[i].replaceAll("d", "");

                time += Integer.parseInt(string[i]) * 60 * 60 * 24;
            } else if (string[i].endsWith("h")) {
                string[i] = string[i].replaceAll("h", "");

                time += Integer.parseInt(string[i]) * 60 * 60;
            } else if (string[i].endsWith("m")) {
                string[i] = string[i].replaceAll("m", "");

                time += Integer.parseInt(string[i]) * 60;
            } else if (string[i].endsWith("s")) {
                string[i] = string[i].replaceAll("s", "");

                time += Integer.parseInt(string[i]);
            }
        }

        if (string[0].equalsIgnoreCase("set")) {
            timer.setTime(time);
        }else if (string[0].equalsIgnoreCase("add")) {
            timer.setTime(timer.getTime() + time);
        }

    }

    public static void setCountingDirection(String[] strings) {

        if (strings[1].toUpperCase().equals(String.valueOf(Timer.countDirection.UP))) {
            timer.setCountDirection(Timer.countDirection.UP);
        }else if (strings[1].toUpperCase().equals(String.valueOf(Timer.countDirection.DOWN))) {
            timer.setCountDirection(Timer.countDirection.DOWN);
        }

    }

}
