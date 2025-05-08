package thread;

class ProducerConsumerTest {
    public static void main(String args[]) throws InterruptedException {
        CubbyHole c = new CubbyHole();
        Producer p1 = new Producer(c, 1);
        Consumer c1 = new Consumer(c, 1);

        p1.start();
        Thread.sleep(500); // Allow producer to get a head start
        c1.start();

        p1.join();
        c1.join();

        System.out.println("Production and consumption completed.");
    }
}