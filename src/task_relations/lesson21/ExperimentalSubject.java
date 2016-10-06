package task_relations.lesson21;

public class ExperimentalSubject {
    private int x;
    private boolean flag;
    private int counter = 0;

    public ExperimentalSubject(int x) {
        this.x = x;
    }

    public synchronized void waitForIncrementer()
            throws InterruptedException {
        while (flag) {
            wait();
        }
    }

    public synchronized void waitForSubtracter()
            throws InterruptedException {
        while (!flag) {
            wait();
        }
    }

    public synchronized void setFlag(boolean flag) {
        counter++;
        this.flag = flag;
        notifyAll();
    }

    public synchronized void increment() {
        x++;
    }

    public synchronized void subtract() {
        x--;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized boolean getFlag() {
        return flag;
    }

    public int getCounter() {
        return counter;
    }
}
