package interview;

import java.util.Scanner;

/**
*给出一个字符串，将字符串的字母按ASCII码顺序排序，其余的非字母的字符位置不变
*
 * Created by tianxianglan on 2017/1/6.
 */
public class RankByAsciiOfHW {
    public static void main(String[] args){
        System.out.print("please input a string: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] chars = str.toCharArray();
        int[] indexOfChar = new int[str.length()];
        int num = 0;
        for (int i = 0; i< str.length(); i++){
            char letter = str.charAt(i);
            if ((letter> 'A' && letter< 'Z') || (letter> 'a' && letter< 'z')){
                indexOfChar[num] = i;
                num++;
            }
        }
        num--;
        //閲囩敤鍐掓场鎺掑簭瀵瑰瓧姣嶈繘琛屽崌搴忔帓搴?
        for (int j = 0; j< num; j++){
            for (int k = 0; k< num- j; k++){
                int swap = 0;
                int kIndex = indexOfChar[k];
                if (chars[kIndex] > chars[indexOfChar[k+ 1]]){
                    char ch = chars[indexOfChar[k+ 1]];
                    chars[indexOfChar[k+ 1]] = chars[kIndex];
                    chars[kIndex] = ch;

                    swap = 1;
                }
                if (swap == 1) break;
            }
        }

        System.out.print("string after rank: ");
        for (int h = 0; h< str.length(); h++){
            System.out.print(chars[h]);
        }
    }
}
