package task_relations.lesson21;

import java.util.Random;

public class ExperimentalSubject {
    private int x;
    private boolean flag;
    private Random random = new Random(47);

    public ExperimentalSubject(int x) {
        this.x = x;
    }

    public synchronized void waitForSubtracter() throws InterruptedException {
        while (flag) {
            wait();
        }
    }

    public synchronized void waitForIncrementer() throws InterruptedException {
        while (!flag) {
            wait();
        }
    }

    public synchronized void setReadyForSubtract() {
        flag = true; // Готово для уменьшения
        notifyAll();
    }

    public synchronized void setReadyForIncrement() {
        flag = false; // готово для увеличения
        notifyAll();
    }

    public void increment() {
        x += random.nextInt(10);
    }

    public void subtract() {
        x -= random.nextInt(10);
    }

    public int getX() {
        return x;
    }
}
