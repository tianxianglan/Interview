package offer;

import java.util.Arrays;
import java.util.Queue;

/**
 * Created by tianxianglan on 2017/3/12.
 */
public class Algorithms {
    public static void main(String[] args){
//        int[] num = {1,34,5,557,23,78,9,4,0,234};
//        System.out.println(Arrays.toString(BubbleSort(num)));
        int[] num = {34,5,23,78,88,94};
        QuickSort(num,0,num.length- 1);
        System.out.println(Arrays.toString(num));
    }

    /*
    * 快速排序算法
    * */
    static int QuickPartition(int[] num, int head, int tail){
        int key = num[head];
        while (head< tail){
            while (key<= num[tail] && head< tail){
                tail--;
            }
            if (head< tail){
                num[head] = num[tail];
                num[tail] = key;
                head++;
            }
            while (key>= num[head] && head< tail){
                head++;
            }
            if (head< tail){
                num[tail] = num[head];
                num[head] = key;
                tail--;
            }
        }
        return head;
    }
    static void QuickSort(int[] num, int head, int tail){
        if (head>= tail)
            return;
        int separt = QuickPartition(num,head,tail);
        QuickSort(num, head, separt- 1);
        QuickSort(num, separt+ 1, tail);
    }

    /*
    * 冒泡排序算法
    * */
    static int[] BubbleSort(int[] num){
        for (int i = 1; i< num.length; i++){
            boolean falg = true;
            for (int j = 0; j< num.length- i; j++){
                if (num[j] > num[j+ 1]){
                    int temp = num[j];
                    num[j] = num[j+ 1];
                    num[j+ 1] = temp;
                    falg = false;
                }
            }
            if (falg)
                break;
        }
        return num;
    }
}
