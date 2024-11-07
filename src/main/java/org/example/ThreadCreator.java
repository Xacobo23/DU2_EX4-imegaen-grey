package org.example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCreator implements Runnable {

    public static final int NUM_THREADS = 5;

    private final List<Path> listPath;
    private final long countPath;

    public ThreadCreator(List<Path> listPath, long countPath) {
        this.listPath = listPath;
        this.countPath = countPath;
    }


    @Override
    public void run() {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

        int numThread=0;
        while (numThread<NUM_THREADS) {
            for (int n = 0; n < NUM_THREADS; n++) {

                List<Path> listaPequena = new ArrayList<>();

                        listaPequena.add(listPath.get(n+numThread*NUM_THREADS));

                Runnable rg = new ThreadGrey(listaPequena);
                pool.execute(rg);
            }
            numThread++;
        }
        pool.shutdown();
    }
}
