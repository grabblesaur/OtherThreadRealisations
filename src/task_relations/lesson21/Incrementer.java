package task_relations.lesson21;

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
                es.setFlag(false);
                es.increment();
                es.waitForSubtracter();
            }
        } catch (InterruptedException e) {
            System.out.println("Incrementer.run: exit via IE");
        }
        System.out.println("Incrementer.run: end task");
    }
}
