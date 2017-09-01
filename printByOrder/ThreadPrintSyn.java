package meiTuan;

/**
 * Created by tianxianglan on 2017/9/1.
 */

/**
 * 多线程情况下实现顺序输出
 */
public class ThreadPrintSyn {
    public static int _I = 0;
    private static int _NUM = 10;

    public static void main(String[] args) {
        Thread a = new ThreadA();
        Thread b = new ThreadB();
        Thread c = new ThreadC();

        a.start();
        b.start();
        c.start();
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < _NUM; ) {
                synchronized (this) {
                    if (_I % 3 == 0) {
                        System.out.print("A");
                        _I++;
                        i++;
                    }
                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < _NUM;) {
                synchronized (this) {
                    if (_I % 3 == 1) {
                        System.out.print("B");
                        _I++;
                        i++;
                    }
                }
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < _NUM;) {
                synchronized (this) {
                    if (_I % 3 == 2) {
                        System.out.print("C");
                        _I++;
                        i++;
                    }
                }
            }

        }
    }
}
