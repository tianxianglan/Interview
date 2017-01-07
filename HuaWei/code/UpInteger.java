package interview;

import java.util.Scanner;

/**
 * Created by tianxianglan on 2017/1/7.
 */
public class UpInteger {
    public static String[] PLUS = {"","拾","佰","扦","萬","拾萬","佰萬","扦萬","亿","拾亿","佰亿","扦亿","萬亿"};
    public static String[] NUMBER = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        int point = number.indexOf('.');
        String intPart = number.substring(0,point);
        String floatPart = number.substring(point+ 1);

        StringBuffer result = new StringBuffer();
        result.append(toUpOfInt(intPart));
        result.append("点");
        result.append(toUpOfFloat(floatPart));
        System.out.println(result);
    }

    public static StringBuffer toUpOfInt(String str){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i< str.length(); i++){
            int num = str.charAt(i)- '0';
            if (num == 0 && stringBuffer.charAt(stringBuffer.length()- 1) == '零')
                continue;
            else {
                if (num != 0){
                    stringBuffer.append(NUMBER[num]);
                    stringBuffer.append(PLUS[str.length()- i- 1]);
                }else
                    stringBuffer.append(NUMBER[num]);
            }
        }
        return stringBuffer;
    }

    public static StringBuffer toUpOfFloat(String str){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i< str.length(); i++){
            int num = str.charAt(i)- '0';
            if (num != 0){
                stringBuffer.append(NUMBER[num]);
                stringBuffer.append(PLUS[str.length()- i- 1]);
            }else
                stringBuffer.append(NUMBER[num]);
        }
        return stringBuffer;
    }
}
