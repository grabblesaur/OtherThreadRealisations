package task_relations.lesson21;

import java.util.concurrent.TimeUnit;

/**
 * Уменьшает х на 1.
 */
public class Subtracter implements Runnable {

    private ExperimentalSubject es;

    public Subtracter(ExperimentalSubject es) {
        this.es = es;
    }

    @Override
    public void run() {
        System.out.println("Incrementer.run: start task");
        try {
            while (!Thread.interrupted()) {
                es.waitForIncrementer();

                System.out.println("Subtracter.run: working");
                es.subtract();
                System.out.println("Subtracter.run: " + es.getX());
                TimeUnit.MILLISECONDS.sleep(200);

                es.setReadyForIncrement();
            }
        } catch (InterruptedException e) {
            System.out.println("Subtracter.run: exit via IE");
        }
        System.out.println("Subtracter.run: end of task");
    }
}
