package com.bayarbogdanov.critical_sections;

/**
 * Потоково-небезопасный класс
 */
public class Pair {

    private int x, y;

    public Pair() {
        this.x = 0;
        this.y = 0;
    }

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX() {x++;}

    public void incrementY() {y++;}

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }

    public class PairValuesNotEqualException
            extends RuntimeException {
        public PairValuesNotEqualException() {
            super("Pair values not equal: " + Pair.this);
        }
    }

    // Произвольный инвариант - переменные должны быть равны:
    public void checkState() {
        if (x != y) {
            throw new PairValuesNotEqualException();
        }
    }
}
