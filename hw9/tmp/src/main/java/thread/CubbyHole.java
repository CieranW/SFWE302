package thread;

import java.util.LinkedList;
import java.util.Queue;

class CubbyHole {
    private final int maxProducts = 10;
    private Queue<Integer> buffer = new LinkedList<>();

    public synchronized int get() {
        while (buffer.isEmpty()) {
            try { wait(); } catch (InterruptedException e) {}
        }
        int value = buffer.poll();
        notifyAll(); // Notify producers
        return value;
    }

    public synchronized void put(int value) {
        while (buffer.size() >= maxProducts) {
            try { wait(); } catch (InterruptedException e) {}
        }
        buffer.offer(value);
        notifyAll(); // Notify consumers
    }
}