public class IntThread extends Thread {
    int number;

    public IntThread(int number){
        this.number = number;
    }

    public void run() {
        for (int i = 0; i < 15; i++) {
            System.out.println(number);
        }
    }
}
