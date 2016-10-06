package task_relations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Простейшее взаимодействие между задачами.
 * Автомат WaxOMatic содержит два процесса: один наносит воск
 * на кузов машины, а другой полирует его. Задача полировки не
 * может быть выполнена, пока не будет завершена задача
 * нанесения, а задача наненсения должна дождаться завершения
 * полировки, прежде чем наносить другой слой воска.
 */
class Car {
    private boolean flag = false;

    public synchronized void readyForPolirovka() {
        flag = true; // Готово к полировке
        notifyAll();
    }

    public synchronized void readyForVosk() {
        flag = false; // Готово к нанесению слоя воска
        notifyAll();
    }

    public synchronized void waitForUseVosk() throws InterruptedException {
        while (flag != true) {
            wait();
        }
    }

    public synchronized void waitForUsePolirovka() throws InterruptedException {
        while (flag == true) {
            wait();
        }
    }
}

class Vosk implements Runnable {

    private Car car;

    public Vosk(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Vosk begins!");
                car.waitForUsePolirovka();

                TimeUnit.MILLISECONDS.sleep(200);
                car.readyForPolirovka();
                car.waitForUsePolirovka();
            }
        } catch (InterruptedException e) {
            System.out.println("Vosk.run: exit via IE");
        }
        System.out.println("Vosk.run: end");
    }
}

class Polirovka implements Runnable {

    private Car car;

    public Polirovka(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForUseVosk();
                System.out.println("Polirovka begins!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.readyForVosk();
            }
        } catch (InterruptedException e) {
            System.out.println("Polirovka.run: exit via IE");
        }
        System.out.println("Polirovka.run: end");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Polirovka(car));
        exec.execute(new Vosk(car));

        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
