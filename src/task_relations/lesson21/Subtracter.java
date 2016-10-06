package task_relations.lesson21;

/**
 * Уменьшает х на 1.
 */
public class Subtracter implements Runnable {

    ExperimentalSubject es;

    public Subtracter(ExperimentalSubject es) {
        this.es = es;
    }

    @Override
    public void run() {
        System.out.println("Incrementer.run: start task");
        try {
            while (!Thread.interrupted()) {
                es.setFlag(true);
                es.subtract();
                es.waitForIncrementer();
            }
        } catch (InterruptedException e) {
            System.out.println("Subtracter.run: exit via IE");
        }
        System.out.println("Subtracter.run: end of task");
    }
}
