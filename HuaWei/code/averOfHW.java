package interview;

import java.util.Scanner;

/**
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
