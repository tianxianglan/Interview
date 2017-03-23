package offer;

import java.util.Scanner;

/**
 * Created by tianxianglan on 2017/3/23.
 */
/*
* 问题描述：连续输入字符串，按长度为8拆分每个字符串输出到新的字符串数组，长度不足8整数倍的在后面补0
* 输入描述：连续输入两次字符串，每个字符串长度小于100
* 输出描述：输出长度为8的新字符串数组
* 输入样例：abc
*          123456789
* 输出例子：abc00000
*           12345678
*           90000000
* 写在后面：这是一种很鸡贼的方法，首先判断字符串长度是否为8的整数倍，如果是则直接模8输出
*           如果不是的话则现在字符串的尾部加8个0，之后每次取字符串的前8个，之后在截取该字符串第9个直到最后
* */
public class Sep8Word {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                String str = scanner.nextLine();
                if(str.length() % 8 != 0)
                    str = str + "00000000";
                while(str.length()>= 8){
                    System.out.println(str.substring(0,8));
                    str = str.substring(8);
                }
            }
    }
    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        if (str1.length()> 100 || str2.length()> 100 || str1 == null || str2 == null || str1.trim().isEmpty() || str2.trim().isEmpty())
            return;
        seprate(str1);
        seprate(str2);
    }
    public static void seprate(String str){
        int lenth = str.length();
        char[] ch = str.toCharArray();
        int times = lenth / 8;
        for (int i = 0; i< times; i++){
            for (int j = 8* i; j< 8* i+ 8; j++)
                System.out.print(ch[j]);
            System.out.println();
        }
        for (int i = 8* times; i< lenth; i++)
            System.out.print(ch[i]);
        for (int i = 0; i< 8- lenth% 8; i++)
            System.out.print("0");
        System.out.println();
    }*/
}
