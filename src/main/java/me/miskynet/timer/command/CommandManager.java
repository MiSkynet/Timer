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

        if (strings.length >= 1) {

            // /timer start
            if (strings[0].equalsIgnoreCase("start")) {
                Functions.startTimer();
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

            // try to set the time to a valid value
            else {
                Functions.setTime(strings);
            }

        }else {
            Component message = Utils.createMessage("&cSorry, but please us the correct command form!");
            commandSender.sendMessage(message);
        }


        return false;
    }
}
