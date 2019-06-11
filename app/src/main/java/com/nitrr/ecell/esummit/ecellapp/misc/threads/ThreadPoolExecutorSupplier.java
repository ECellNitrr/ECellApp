package com.nitrr.ecell.esummit.ecellapp.misc.threads;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorSupplier {

    private static ThreadPoolExecutorSupplier threadPoolExecutorSupplierInstance;
    private final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private final ThreadPoolExecutor doBackgroundTasksThreadPoolExecutor;
    private final Executor mainThreadExecutor;

    private ThreadPoolExecutorSupplier() {

        ThreadFactory doBackgroundTaskThreadFactory = new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        doBackgroundTasksThreadPoolExecutor = new PriorityThreadPoolExecutor(NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                Integer.MAX_VALUE, TimeUnit.SECONDS, doBackgroundTaskThreadFactory);

        mainThreadExecutor = new MainThreadExecutor();
    }

    public static ThreadPoolExecutorSupplier getInstance() {
        if (threadPoolExecutorSupplierInstance == null) {
            synchronized (ThreadPoolExecutorSupplier.class) {
                threadPoolExecutorSupplierInstance = new ThreadPoolExecutorSupplier();
            }
        }

        return threadPoolExecutorSupplierInstance;
    }

    public Executor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

    public ThreadPoolExecutor getDoBackgroundTasksThreadPoolExecutor(){
        return doBackgroundTasksThreadPoolExecutor;
    }
}