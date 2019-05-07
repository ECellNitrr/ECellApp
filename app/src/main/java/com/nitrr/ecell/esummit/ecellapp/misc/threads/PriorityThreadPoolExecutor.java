package com.nitrr.ecell.esummit.ecellapp.misc.threads;

import android.support.annotation.NonNull;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {

    PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new PriorityBlockingQueue<>(), threadFactory);
    }

    @Override
    public Future<?> submit(Runnable task) {
        PriorityFutureTask priorityFutureTask = new PriorityFutureTask(task);
        execute(priorityFutureTask);

        return priorityFutureTask;
    }

    private final class PriorityFutureTask extends FutureTask<PriorityRunnable> implements Comparable<PriorityFutureTask> {

        private final PriorityRunnable priorityRunnable;

        PriorityFutureTask(@NonNull Runnable runnable) {
            super(runnable, null);

            priorityRunnable = (PriorityRunnable) runnable;
        }

        @Override
        public int compareTo(@NonNull PriorityFutureTask o) {
            Priority p0 = priorityRunnable.getPriority();
            Priority p1 = o.priorityRunnable.getPriority();

            return p1.ordinal() - p0.ordinal();
        }
    }
}
