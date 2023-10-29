public class TestThread {
    public static void main(String args[]) {
        Thread firstThread = new IntThread(1);
        Thread secondThread = new IntThread(2);
        Thread thirdThread = new IntThread(3);

        firstThread.start();
        secondThread.start();
        thirdThread.start();
    }
}
