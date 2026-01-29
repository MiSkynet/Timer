package me.miskynet.timer.command;

import me.miskynet.timer.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (!(commandSender.hasPermission("timer.timer"))) {
            String message = "&Sorry, but you don't have permission to use this command!";
            commandSender.sendMessage(Utils.createMessage(message));
            return true;
        }

        if (strings.length >= 1) {

            // /timer start
            if (strings[0].equalsIgnoreCase("start")) {
                Functions.startTimer(commandSender);
                return true;
            }

            // /timer pause
            else if (strings[0].equalsIgnoreCase("pause")) {
                Functions.pauseTimer();
                return true;
            }

            // /timer reset
            else if (strings[0].equalsIgnoreCase("reset")) {
                Functions.resetTimer();
                return true;
            }

            // /timer <set|add> <time>
            else if (strings[0].equalsIgnoreCase("set") || strings[0].equalsIgnoreCase("add")) {
                Functions.setAddTime(strings);
                return true;
            }

            // /timer reset
            else if (strings[0].equalsIgnoreCase("reset")) {
                Functions.resetTimer();
                return true;
            }

            // /timer direction <UP|DOWN>
            else if (strings[0].equalsIgnoreCase("direction")) {
                Functions.setCountingDirection(strings);
                return true;
            }

        }else {
            Component message = Utils.createMessage("&cSorry, but please us the correct command form!");
            commandSender.sendMessage(message);
            return true;
        }

        return false;
    }
}
