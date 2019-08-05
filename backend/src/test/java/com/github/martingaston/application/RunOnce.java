package com.github.martingaston.application;

public class RunOnce implements Runner {
    private int counter;
    private int timesRun;

    public RunOnce() {
        counter = 1;
        timesRun = 0;
    }

    @Override
    public boolean isRunning() {
        if(counter <= 0) {
            return false;
        }

        counter -= 1;
        timesRun += 1;
        return true;
    }

    public int timesRun() {
        return timesRun;
    }
}
