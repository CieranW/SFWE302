package thread;

class Consumer extends Thread {
    private CubbyHole cubbyhole;
    private int number;

    public Consumer(CubbyHole c, int number) {
        cubbyhole = c;
        this.number = number;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            int value = cubbyhole.get();
            System.out.println("Consumer #" + this.number + " got: " + value + " :: " + System.currentTimeMillis());
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}