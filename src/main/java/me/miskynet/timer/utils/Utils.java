package me.miskynet.timer.utils;

import me.miskynet.timer.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;

public class Utils {

    // create a message component to use in #sendMessage();
    public static Component createComponentMessage(String string) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(string);
        return component;
    }

    // get the messages out of the config
    public static String getMessage(String string) {
        return Main.getInstance().getConfig().getString(string);
    }
}
