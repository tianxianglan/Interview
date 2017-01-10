package leetCode;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by tianxianglan on 2017/1/4.
 * 牛客网网易2017秋招编程题
 * 如果一个数字序列逆置之后跟原生序列一样的就称这样的数字序列为回文序列
 */
public class HuiWenXuLie {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] inputArr = new int[num];
        for (int i = 0;i< num;i++){
            inputArr[i] = scanner.nextInt();
        }
        int head = 0;
        int times = 0;
        int tail = num- 1;
        while ((tail- head)>= 1){
            if (inputArr[head] < inputArr[tail]){
                int newHead = head+ 1;
                inputArr[newHead] = inputArr[head] + inputArr[newHead];
                head = newHead;
                times++;
            } else if (inputArr[head] > inputArr[tail]){
                int newTail = tail- 1;
                inputArr[newTail] = inputArr[tail]+ inputArr[newTail];
                tail = newTail;
                times++;
            }else{
                head++;
                tail--;
                continue;
            }
        }
        System.out.println(times);
    }
}
