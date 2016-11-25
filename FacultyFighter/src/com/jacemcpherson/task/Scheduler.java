package com.jacemcpherson.task;

import java.util.ArrayList;

public class Scheduler {

    private static ArrayList<Thread> mThreads = new ArrayList<>();

    /**
     * Schedules a thread with the given Runnable. Returns the Thread's ID for this scheduler.
     * @param runnable
     * @return
     */
    public static int schedule(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
        return add(thread);
    }

    public static int schedule(int priority, Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setPriority(priority);
        thread.start();
        return add(thread);
    }

    private static synchronized void remove(Thread thread) {
        mThreads.remove(thread);
    }

    private static synchronized int add(Thread thread) {
        mThreads.add(thread);
        return mThreads.size() - 1;
    }

    public static synchronized Thread.State getState(int threadId) {
        return mThreads.get(threadId).getState();
    }



}
