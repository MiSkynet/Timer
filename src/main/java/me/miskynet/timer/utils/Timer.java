package me.miskynet.timer.utils;

public class Timer {

    static int seconds = 0;

    // set and get the time
    public void setTime(int value) {
        seconds = value;
    }

    public int getTime() {
        return seconds;
    }

    boolean running = false;

    // set if the timer is running or not
    public void setRunning(boolean setRunning) {
        running = setRunning;
    }

    public enum countDirection {
        UP,
        DOWN
    }

    static countDirection countDirection = Timer.countDirection.UP;

    // set and get the direction the timer is currently counting in
    public void setCountDirection(countDirection countDirectionDirection) {
        countDirection = countDirectionDirection;
    }

    public countDirection getCountDirection() {
        return countDirection;
    }

}
