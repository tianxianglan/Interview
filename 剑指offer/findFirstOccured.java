package offer;

import java.util.*;

/**
 * Created by tianxianglan on 2017/3/16.
 */
/*
* 在字符串中查找第一个只出现一次的字符，如输入“abaccdeff”，则输出‘b’
* 分析：这道题目在剑指offer面试题35，p186
*       书中给出的解法为c语言的解法，书中解法有一个可取之处就是哈希表的思想，char类型数据共有256个，就建立一个长度为256的数组，
*       并把数组的值初始化为0，之后数组下标为char对应的ASCII码，有重复的时候讲数组值++便可。
*       这种思想在算法中比较常见，要掌握。
* */
public class findFirstOccured {
    public static Object firstOccur(char[] chArray){
        if (chArray == null)
            return "\0";
        /*之所以使用LinkedHahMap是因为LinkedHashMap取出数据时的顺序与存入的顺序是一样的，这点属性HashMap不具有*/
        LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap<Character, Integer>();
        for (char ch : chArray){
            if (linkedHashMap.containsKey(ch)){
                int count = linkedHashMap.get(ch);
                int newCount = count+ 1;
                linkedHashMap.put(ch, newCount);
            }else
                linkedHashMap.put(ch,1);
        }
        Iterator iterator = linkedHashMap.keySet().iterator();
        while (iterator.hasNext()){
            Object object = iterator.next();
            if (linkedHashMap.get(object).equals(1))
                return object;
        }
        return "\0";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] chArray = str.toCharArray();
        System.out.println(firstOccur(chArray));
    }
}
