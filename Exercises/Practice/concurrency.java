package Practice;

public class concurrency {

    public static class MyRunnable implements Runnable {
        public static int[] array = new int[10];
        public void run() {
            System.out.println("Hello, I am " + Thread.currentThread().getName());
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] + i;
            }
            System.out.println( java.util.Arrays.toString(array) );
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new MyRunnable();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(runnable, "Thread ".concat(Integer.toString(i)));
            thread.start();
        }
    }
}
