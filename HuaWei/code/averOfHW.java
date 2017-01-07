package interview;

import java.util.Scanner;

/**
*给出五门课程的成绩，求平均值。如果输入的成绩不足五个则按五个计算。如果输入的多余五个
* 则取前五个成绩
*
 * Created by tianxianglan on 2017/1/5.
 */
public class averOfHW {
    public static void main(String[] args){
        System.out.println("please input scores:");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        String[] s = number.split(" ");
        int sum = 0;
        double aver = 0.0;
        int time = 0;
        for (int i = 0; i< s.length; i++){
            int score = Integer.parseInt(s[i]);
            if (score>= 0 && score<= 100){
                sum += score;
                time++;
            }
            if (time>= 5)
                break;
        }
        aver = (double)sum / 5;
        System.out.println(aver);
    }
}
