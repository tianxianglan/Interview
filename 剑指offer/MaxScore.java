package offer;

import java.util.Scanner;

/**
 * Created by tianxianglan on 2017/3/20.
 */
public class MaxScore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int stuNum = scanner.nextInt();
        int ordNum = scanner.nextInt();
        int[] score = new int[stuNum];
        String[] order = new String[ordNum];
        //读取学生成绩
        for (int i = 0;    i< stuNum; i++)
            score[i] = scanner.nextInt();
        //如果没有这行，循环中第一个读取一行就将会独到null，这里将其向下移一行
        scanner.nextLine();
        //读取指令
        for (int i = 0; i< ordNum; i++)
            order[i] = scanner.nextLine();
        solve(score,order);
    }
    public static void solve(int[] score, String[] order){
        for (int i = 0; i< order.length; i++){
            String[] command = order[i].split(" ");
            String orderType = command[0];
            int stuId = Integer.parseInt(command[1]);
            int stuScore = Integer.parseInt(command[2]);
            if (orderType.equals("U")){
                score[stuId- 1] = stuScore;
            }else if (orderType.equals("Q")){
                int maxScore = findMax(score,stuId-1,stuScore-1);
                System.out.println(maxScore);
            }
        }
    }
    public static int findMax(int[] score, int begin, int end){
        int max = score[begin];
        for (int i = begin; i<= end; i++){
            if (max< score[i])
                max = score[i];
        }
        return max;
    }
}
