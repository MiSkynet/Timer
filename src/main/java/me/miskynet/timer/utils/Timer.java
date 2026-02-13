package me.miskynet.timer.utils;

import me.miskynet.timer.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class Timer {

    private int seconds;

    boolean running = false;

    public enum countDirection {
        UP,
        DOWN
    }

    public Timer() {
        this.seconds = CustomConfig.get("timer.yml").getInt("time");
        // check if a time already exists in the config.yml
        int configTime = CustomConfig.get("timer.yml").getInt("time");
        if (configTime > 0) {
            seconds = configTime;
        }
    }

    static countDirection countDirection = Timer.countDirection.UP;

    // return if the timer is running or not
    public Boolean isRunning() {
        return running;
    }

    // set the timer in the running state / put it out of the running state (pause it)
    public void setRunning(boolean setRunning) {
        running = setRunning;
    }

    // set the time
    public void setTime(int value) {
        seconds = value;
    }

    // get the time
    public int getTime() {
        return seconds;
    }

    // set and get the direction the timer is currently counting in
    public void setCountDirection(countDirection countDirectionDirection) {
        countDirection = countDirectionDirection;
    }

    // get the direction the timer is counting in
    public countDirection getCountDirection() {
        return countDirection;
    }

    // get the color that the timer should have
    // if the timer is paused, it will be red
    // if the timer is running, it will be white
    public String getTimerColor() {
        String color = "&6";
        if (running) {
            color = Main.getInstance().getConfig().getString("colors.running");
        }else {
            color = Main.getInstance().getConfig().getString("colors.paused");
        }
        return color;
    }

    // save the time in the config.yml
    public void saveTime() {
        CustomConfig.get("timer.yml").set("time", seconds);
        CustomConfig.save("timer.yml");
    }

}
