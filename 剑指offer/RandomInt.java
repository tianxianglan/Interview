package offer;

import java.util.*;

/**
 * Created by tianxianglan on 2017/3/22.
 */
/*
* 该题目为对许多数进行一个排序以及查重的题，共给出了两种解法
* 总结：TreeSet既有Set的性质，可以查重。同时也可以对其中的数进行排序
*       Arrays.sort（）方法可以实现对数组进行排序
* */
public class RandomInt {
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            TreeSet<Integer> set = new TreeSet<>();
            int num = scanner.nextInt();
            for (int i = 0; i< num; i++)
                set.add(scanner.nextInt());
            for (Integer i : set)
                System.out.println(i);
        }
    }*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int num = scanner.nextInt();
            int[] array = new int[num];
            for (int i = 0; i< num; i++){
                array[i] = scanner.nextInt();
            }
            Arrays.sort(array);
            System.out.println(array[0]);
            for (int i = 1; i< num; i++){
                if (array[i] != array[i- 1])
                    System.out.println(array[i]);
            }
        }
    }
}
