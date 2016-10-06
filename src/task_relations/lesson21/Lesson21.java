package task_relations.lesson21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lesson21 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ExperimentalSubject es = new ExperimentalSubject(0);

        exec.submit(new Incrementer(es));
        exec.submit(new Subtracter(es));

        TimeUnit.MILLISECONDS.sleep(5000);
        exec.shutdownNow();
    }
}
