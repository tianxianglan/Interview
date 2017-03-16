package offer;

/**
 * Created by tianxianglan on 2017/3/12.
 */
/*
* 找出数组中第k大的数，当面试官说出这个题目时，自己首先想到的应该是k相对于n的大小一个问题
* */
public class MaxCount {
    public static void main(String[] args){
        int[] num = {1,34,5,7,23,78,9,4,0,234,56,18,41,82,84,233,232,237};
        System.out.println(findMax(num, 3));
    }
    static int findMax(int[] num, int k){
        int[] max = new int[k];
        for (int i = 0; i< num.length; i++){
            if (num[i] < max[k- 1])
                continue;
            else {
                max[k- 1] = num[i];
                for (int j = k- 1; j> 0; j--){
                    if (max[j] > max[j -1]){
                        int temp = max[j];
                        max[j] = max[j- 1];
                        max[j- 1] = temp;
                    }else
                        break;
                }
            }
        }
        return max[k- 1];
    }
}
