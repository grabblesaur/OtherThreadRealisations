package task_relations.lesson21;

import java.util.concurrent.TimeUnit;

/**
 * Увеличивает x на 1.
 */
public class Incrementer implements Runnable {

    private ExperimentalSubject es;

    public Incrementer(ExperimentalSubject es) {
        this.es = es;
    }

    @Override
    public void run() {
        System.out.println("Incrementer.run: start task");
        try {
            while (!Thread.interrupted()) {
                System.out.println("Incrementer.run: working");
                es.waitForSubtracter();

                es.increment();
                System.out.println("Incrementer.run: " + es.getX());
                TimeUnit.MILLISECONDS.sleep(200);

                es.setReadyForSubtract();
                es.waitForSubtracter();
            }
        } catch (InterruptedException e) {
            System.out.println("Incrementer.run: exit via IE");
        }
        System.out.println("Incrementer.run: end task");
    }
}
