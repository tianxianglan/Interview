package offer;

import java.util.Scanner;

/**
 * Created by tianxianglan on 2017/3/21.
 */
/*
* 题目描述：计算字符串最后一个单词的长度，单词以空格隔开
* 输入描述：一行字符串，非空，长度小于5000
* 输出描述：整数N，最后一个单词的长度
* 输入例子：hello，world
* 输出例子：5
* */
public class lengthOfSentence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine();
        int length = sentence.length();
        int output = 0;
        if (sentence == null || length > 5000)
            return;
        if ( !sentence.contains(" "))
            System.out.println(length);
        else {
            for (int i = length- 1; i>= 0; i--){
                if (sentence.charAt(i) != ' ')
                    output++;
                else
                    break;
            }
            System.out.println(output);
        }

    }
}
