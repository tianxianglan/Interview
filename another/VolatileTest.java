package thread;

/**
 * Created by tianxianglan on 2017/4/19.
 */
public class VolatileTest {
    public static volatile int count = 0;
    public static volatile boolean shutdownRequested;

    public static void shutdown(){
        shutdownRequested = true;
    }

    public static void dowork(){
        while (!shutdownRequested){
            count++;
            System.out.println("   "+ Thread.currentThread().getName()+ "  "+ count+ shutdownRequested);
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[4];
        for (int i = 0; i< 4; i++){
            if (i == 2){
                threads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("changed the shutdownRequested");
                        shutdown();
                    }
                });
            }else {
                threads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dowork();
                    }
                });
            }
            threads[i].start();
        }
    }
}
