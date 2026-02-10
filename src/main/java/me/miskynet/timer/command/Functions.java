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
            player.sendMessage("The timer has been reset!");
        }
    }

    // set or add a specific time to the timer
    public static void setAddTime(String[] string) {

        int time = 0;

        for (int i = 1; i < string.length; i++) {

            // unit represents w, d, h, m, s
            char unit = string[i].charAt(string[i].length() - 1);

            // numb is the value the user has put in front of the unit
            int numb = Integer.parseInt(string[i].substring(0, string[i].length() - 1));

            switch (unit) {
                case 'w':
                    time += numb * 60 * 60 * 24 * 7;
                    break;
                case 'd':
                    time += numb * 60 * 60 * 24;
                    break;
                case 'h':
                    time += numb * 60 * 60;
                    break;
                case 'm':
                    time += numb * 60;
                    break;
                case 's':
                    time += numb;
                    break;
            }
        }

        if (string[0].equalsIgnoreCase("set")) {
            timer.setTime(time);
        }else if (string[0].equalsIgnoreCase("add")) {
            timer.setTime(timer.getTime() + time);
        }

    }

    // set the counting direction of the timer
    public static void setCountingDirection(String[] strings) {

        if (strings[1].toUpperCase().equals(String.valueOf(Timer.countDirection.UP))) {
            timer.setCountDirection(Timer.countDirection.UP);
        }else if (strings[1].toUpperCase().equals(String.valueOf(Timer.countDirection.DOWN))) {
            timer.setCountDirection(Timer.countDirection.DOWN);
        }

    }

    // remove a specific time off the timer
    public static void removeTime(String[] string) {

        int time = timer.getTime();

        for (int i = 1; i < string.length; i++) {

            // unit represents w, d, h, m, s
            char unit = string[i].charAt(string[i].length() - 1);

            // numb is the value the user has put in front of the unit
            int numb = Integer.parseInt(string[i].substring(0, string[i].length() - 1));

            Bukkit.getLogger().info("Current Time: " + timer.getTime());

            switch (unit) {
                case 'w':
                    time -= numb * 60 * 60 * 24 * 7;
                    break;
                case 'd':
                    time -= numb * 60 * 60 * 24;
                    break;
                case 'h':
                    time -= numb * 60 * 60;
                    break;
                case 'm':
                    time -= numb * 60;
                    break;
                case 's':
                    time -= numb;
                    break;
            }
        }

        Bukkit.getLogger().info("New Time: " + time);

        timer.setTime(time);

    }

}
