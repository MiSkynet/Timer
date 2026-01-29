package me.miskynet.timer.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        ArrayList<String> completion = new ArrayList<>();

        if (strings.length == 1) {
            if ("start".startsWith(strings[0].toLowerCase())) {
                completion.add("start");
            }
            if ("pause".startsWith(strings[0].toLowerCase())) {
                completion.add("pause");
            }
            if ("reset".startsWith(strings[0].toLowerCase())) {
                completion.add("reset");
            }
            if ("set".startsWith(strings[0].toLowerCase())) {
                completion.add("set");
            }
            if ("add".startsWith(strings[0].toLowerCase())) {
                completion.add("add");
            }
            if ("direction".startsWith(strings[0].toLowerCase())) {
                completion.add("direction");
            }
        }else if (strings.length == 2) {
            if (strings[0].equalsIgnoreCase("direction")) {
                if ("up".startsWith(strings[1].toLowerCase())) {
                    completion.add("UP");
                }
                if ("down".startsWith(strings[1].toLowerCase())) {
                    completion.add("DOWN");
                }
            }
        }

        return completion;
    }
}
