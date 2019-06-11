package com.nitrr.ecell.esummit.ecellapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.misc.threads.Priority;
import com.nitrr.ecell.esummit.ecellapp.misc.threads.PriorityRunnable;
import com.nitrr.ecell.esummit.ecellapp.misc.threads.ThreadPoolExecutorSupplier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //performBackgroundTasks();
    }

    private void performBackgroundTasks() {
        ThreadPoolExecutorSupplier.getInstance().getDoBackgroundTasksThreadPoolExecutor().submit(new PriorityRunnable(Priority.HIGH) {
            @Override
            public void run() {
                // Perform task here.
            }
        });
    }
}
