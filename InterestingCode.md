```
public static void main(String[] args) {
        FutureTask task = new FutureTask(() -> {
            Thread.sleep(200);
            return "test";
        });
        new Thread(() -> {
            try {
                System.out.println("11");
                task.get();
                System.out.println("11");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            System.out.println("333");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.run();
            System.out.println("44");
        }).start();

    }
```
解释：
只有当FutureTask执行了 run() 方法后，FutureTask.get() 方法才能从阻塞中恢复过来，这其中涉及到 FutureTask 的一个状态问题，如果不执行 run() 方法，那么 FutureTask.get() 将一直阻塞下去
