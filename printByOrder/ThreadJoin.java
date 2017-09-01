package meiTuan;

/**
 * Created by tianxianglan on 2017/9/1.
 */
public class ThreadJoin {
    public static void main(String[] args) {
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB(a);
        ThreadC c = new ThreadC(b);

        a.start();
        b.start();
        c.start();
    }
}

class ThreadA extends Thread {
    @Override
    public void run() {
        System.out.print("A");
    }
}

class ThreadB extends Thread {
    private ThreadA a;

    ThreadB(ThreadA a) {
        this.a = a;
    }

    @Override
    public void run() {
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("B");
    }
}

class ThreadC extends Thread {
    private ThreadB b;

    ThreadC(ThreadB b) {
        this.b = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                b.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("C");
        }
    }
}
