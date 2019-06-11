package com.nitrr.ecell.esummit.ecellapp.misc.threads;

public class PriorityRunnable implements Runnable {

    private final Priority priority;

    protected PriorityRunnable(Priority priority){
        this.priority = priority;
    }

    Priority getPriority() {
        return priority;
    }

    @Override
    public void run() {

    }
}
