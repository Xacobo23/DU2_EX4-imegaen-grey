package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        try (Stream<Path> paths = Files.list(Paths.get("./src/main/resources/originals"))) {
            List<Path> listPaths = paths.toList();
            long i = listPaths.size();

            ThreadCreator threadCreator = new ThreadCreator(listPaths, i);
            Thread t = new Thread(threadCreator);
            t.start();
            t.join();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}