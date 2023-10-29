public class TestThread2 {
    public static void main(String args[]) {
        Thread firstThread = new IntThread(1);
        Thread secondThread = new IntThread(2);
        Thread thirdThread = new IntThread(3);

        firstThread.setPriority(Thread.MIN_PRIORITY);
        secondThread.setPriority(Thread.MIN_PRIORITY + 1);
        thirdThread.setPriority(Thread.MIN_PRIORITY + 2);

        firstThread.start();
        secondThread.start();
        thirdThread.start();
    }
}
