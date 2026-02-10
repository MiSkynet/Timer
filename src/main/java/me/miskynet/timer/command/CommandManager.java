package me.miskynet.timer.command;

import me.miskynet.timer.Main;
import me.miskynet.timer.utils.Timer;
import me.miskynet.timer.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        Timer timer = Main.getInstance().timer;

        if (!(commandSender.hasPermission("timer.timer"))) {
            String message = "&Sorry, but you don't have permission to use this command!";
            commandSender.sendMessage(Utils.createComponentMessage(message));
            return true;
        }

        if (strings.length >= 1) {

            // /timer start
            if (strings[0].equalsIgnoreCase("start")) {
                Functions.startTimer(commandSender);

                // message
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (timer.getTime() == 0) {
                        player.sendMessage(Utils.createComponentMessage(Utils.getMessage("messages.started")));
                    }else {
                        player.sendMessage(Utils.createComponentMessage(Utils.getMessage("messages.continued")));
                    }
                }
                return true;
            }
            // /timer pause
            else if (strings[0].equalsIgnoreCase("pause")) {
                Functions.pauseTimer();

                // message
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(Utils.createComponentMessage(Utils.getMessage("messages.paused")));
                }
                return true;
            }
            // /timer reset
            else if (strings[0].equalsIgnoreCase("reset")) {
                Functions.resetTimer();

                // message
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(Utils.createComponentMessage(Utils.getMessage("messages.reset")));
                }
                return true;
            }
            // /timer <set|add> <time>
            else if (strings[0].equalsIgnoreCase("set") || strings[0].equalsIgnoreCase("add")) {
                Functions.setAddTime(strings);

                // message
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(Utils.createComponentMessage(Utils.getMessage("messages." + strings[0].toLowerCase())));
                }
                return true;
            }
            // /timer direction <UP|DOWN>
            else if (strings[0].equalsIgnoreCase("direction")) {
                Functions.setCountingDirection(strings);

                // message
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(Utils.createComponentMessage(Utils.getMessage("messages.counting-direction." + String.valueOf(Timer.countDirection.UP).toLowerCase())));
                }
                return true;
            }
            // /timer remove <time>
            else if (strings[0].equalsIgnoreCase("remove")) {
                Functions.removeTime(strings);

                // message
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(Utils.createComponentMessage(Utils.getMessage("messages.remove")));
                }
                return true;
            }
            // /timer reload
            else if (strings[0].equalsIgnoreCase("reload")) {
                Functions.reload();

                //message
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(Utils.createComponentMessage(Utils.getMessage("messages.reload")));
                }
                return true;
            }

        }else {
            Component message = Utils.createComponentMessage("&cSorry, but please us the correct command form!");
            commandSender.sendMessage(message);
            return true;
        }

        return false;
    }
}
