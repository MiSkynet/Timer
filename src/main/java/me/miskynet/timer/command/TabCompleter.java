package me.miskynet.timer.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    ArrayList<String> completion = new ArrayList<>();

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        completion = new ArrayList<>();

        // check if there are currently 0 args
        // /timer </>
        if (strings.length == 1) {

            checkCompletion(strings[0], "start");
            checkCompletion(strings[0], "pause");
            checkCompletion(strings[0], "reset");
            checkCompletion(strings[0], "set");
            checkCompletion(strings[0], "add");
            checkCompletion(strings[0], "direction");
            checkCompletion(strings[0], "remove");

            // check if there is one arg
            // /timer <XY> </>
        }else if (strings.length == 2) {
            if (strings[0].equalsIgnoreCase("direction")) {
                checkCompletion(strings[1], "up");
                checkCompletion(strings[1], "down");
            }
        }

        return completion;
    }

    private void checkCompletion(String userInput, String comparison) {
        if (comparison.startsWith(userInput.toLowerCase())) {
            completion.add(comparison);
        }
    }
}
