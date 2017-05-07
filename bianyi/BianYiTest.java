import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tianxianglan on 2017/5/7.
 * 编译原理实验
 */
public class BianYiTest {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\tianxianglan\\Desktop\\test.txt")), "UTF-8"));
            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\tianxianglan\\Desktop\\write.txt"));

            String lineText = null;
            while ((lineText = bufferedReader.readLine()) != null){
                lineText = lineText.trim();
                String[] part = lineText.split(" ");
                for (String str : part){
                    if (checkWords(str))
                        writeFile(str, fileOutputStream);
                    else {
                        String[] strArray = doIllegal(str);
                        for (String strIllegal : strArray){
                            if (strIllegal != null)
                                writeFile(strIllegal, fileOutputStream);
                            else
                                break;
                        }
                    }
                }
                byte[] bytesOfEndLine = "             EOLN 24".getBytes();
                fileOutputStream.write(bytesOfEndLine);
                fileOutputStream.write("\r\n".getBytes());
            }
            byte[] bytesOfEndFile = "             EOF 25".getBytes();
            fileOutputStream.write(bytesOfEndFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //判断字符是否可以直接处理
    public static boolean checkWords(String str){
        HashMap hashMap = getHashMap();
        boolean flag = true;
        for (int i = 0; i< str.length(); i++){
            if (hashMap.containsKey(String.valueOf(str.charAt(i)))){
                flag = false;
                break;
            }
        }
        return flag;
    }

    //将处理后的字符串写入文件中
    public static void writeFile(String str, FileOutputStream fileOutputStream){
        try {
            str = transfer(str);
            byte[] bytes = str.getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.write("\r\n".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //对不合规的字符串进行分开处理
    public static String[] doIllegal(String str){
        Queue<String> queue = new LinkedList<>();
        String[] strArray = new String[10];
        HashMap hashMap = getHashMap();
        int index = 0;

        for (int i = 0; i< str.length(); i++){
            String singleWord = String.valueOf(str.charAt(i));
            if (hashMap.containsKey(singleWord)){
                StringBuffer sb = new StringBuffer();
                String headString = null;
                while ((headString = queue.poll()) != null)
                    sb.append(headString);
                if (!sb.toString().equals(""))
                    strArray[index] = sb.toString();
                if (singleWord.equals(":") || singleWord.equals(">") || singleWord.equals("<")){
                    if (i != (str.length()- 1) && hashMap.containsKey(String.valueOf(str.charAt(i+ 1)))){
                        singleWord += String.valueOf(str.charAt(i+ 1));
                        i++;
                    }
                }
                strArray[++index] = singleWord;
                index++;
            }else {
                queue.add(singleWord);
            }
        }
        return strArray;
    }

    //将字符串处理成正确的格式
    public static String transfer(String str){
        HashMap<String, Integer> hashMap = getHashMap();
        StringBuffer sb = new StringBuffer();
        int index;
        int length;

        length = str.length();
        for (int i = 0; i<= 16- length; i++){
            sb.append(" ");
        }
        sb.append(str);
        sb.append(" ");

        if (hashMap.containsKey(str))
            index = hashMap.get(str);
        else {
            if (Character.isDigit(str.charAt(0)))
                index = 11;
            else
                index = 10;
        }
        sb.append(index);
        return sb.toString();
    }

    public static HashMap<String, Integer> getHashMap(){
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("begin", 1);
        hashMap.put("end", 2);
        hashMap.put("integer", 3);
        hashMap.put("if", 4);
        hashMap.put("then", 5);
        hashMap.put("else", 6);
        hashMap.put("function", 7);
        hashMap.put("read", 8);
        hashMap.put("write", 9);
        hashMap.put("=", 12);
        hashMap.put("<>", 13);
        hashMap.put("<=", 14);
        hashMap.put("<", 15);
        hashMap.put(">=", 16);
        hashMap.put(">", 17);
        hashMap.put("-", 18);
        hashMap.put("*", 19);
        hashMap.put(":=", 20);
        hashMap.put("(", 21);
        hashMap.put(")", 22);
        hashMap.put(";", 23);
        hashMap.put(":", 100);

        return hashMap;
    }
}
