/*
 * Copyright (c) 2026. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

import java.util.ArrayList;
import java.util.List;

// test class for course task
public class MultiExecutor {

    List<Thread> threads;

    public MultiExecutor (List<Runnable> tasks) {
        threads = new ArrayList<>();

        for (Runnable task : tasks) {
            threads.add(new Thread(task));
        }
    }

    public void executeAll() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

}
