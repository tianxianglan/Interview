
/*Stack<>  HashMap<>    ArrayList<>     Character   String     */


package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class CalucateString {

//2+(6+3*6)/8   2736*-8/+  50 
//8-(5+4*5)/5   8545*+5/-  56
//7*6-(6-2*1)
//5+3
	
	/*
	 ˼��Ϊ��ȡ��׺���ʽ������ֱ�Ӽ�����ʽ�����������ջ�н��в������������Ϊ��������ֱ����ջ����������һֱ��ջֱ��ջ��Ԫ��Ϊ�����ţ������Ҫ��ջԪ�ص�
	 ���ȼ�����ջ��Ԫ�����ȼ���ֱ����ջ������һֱ��ջֵջ��Ԫ�����ȼ������ڸ�Ԫ��
	 */
	
	@SuppressWarnings("deprecation")
	public String CalucateExpression(String str) {
		/*�������������ȼ�*/
		HashMap<Character, Integer>priority=new HashMap<Character,Integer>();
		priority.put('+', 1);
		priority.put('-', 1);
		priority.put('*', 2);
		priority.put('/', 2);
		priority.put('(', 0);
		priority.put(')', 0);
		priority.put('#', 0);
		
//		priority plus=new priority('+',1);
//		priority reduce=new priority('-', 1);
//		priority muitiply=new priority('*', 2);
//		priority divide=new priority('/', 2);
//		priority left=new priority('(', 0);
//		priority right=new priority(')', 0);
//		priority stat=new priority('#', 0);
		
		
		
		ArrayList<Character> operation=new ArrayList<Character>();
		operation.add('+');
		operation.add('-');
		operation.add('*');
		operation.add('/');
		
		Stack<Character>stack=new Stack<>();//���ڴ洢����
		stack.push('#');//�����ջ�������
		String result="";
		
		for (int i = 0; i < str.length(); i++) {
			if (Character.isJavaLetterOrDigit(str.charAt(i))) {
				result+=str.charAt(i);
				continue;
			}
			if (str.charAt(i)=='(') {
				stack.push('(');
				continue;
			}
			if (str.charAt(i)==')') {
				while(stack.peek()!='('){
					result+=stack.pop();
				}
				stack.pop();
				continue;
			}
			if (operation.contains(str.charAt(i))) {
				if (priority.get(str.charAt(i))>priority.get(stack.peek())) {
					stack.push(str.charAt(i));
					continue;
				}else{
					while(stack.peek()!='('&&priority.get(str.charAt(i))<=priority.get(stack.peek())){
						result+=stack.pop();
					}
					stack.push(str.charAt(i));//������ջ�������ż���
					continue;
				}
			}
			
		}	
			while(stack.peek()!='#'){
				result+=stack.pop();
			}
		
		return result;
	}
	
	/*
	 ˼��Ϊ��ȡ��׺���ʽ������ֱ����ջ��������������ʱ�����ջ�ж�ȡ�������ֽ������㣬����������ѹ��ջ�У����������ֱ����ȡ���һ���ַ�
	 */
	
	public int CalucateFomular(String str){
		Stack<Integer> stack=new Stack<>();
		int i=0;
		while(i<str.length()){
			if (Character.isDigit(str.charAt(i))) {
				stack.push(Integer.parseInt(String.valueOf(str.charAt(i))));//��char������ת����String���ͣ�������ת��Ϊint����
				i++;
			}else{
				int first=stack.pop();
				int second=stack.pop();
				Character name=str.charAt(i);
				Integer now=0;
				i++;
				switch (name){
				case '+':
					now=first+second;
					break;
				case '-':
					now=second-first;
					break;
				case '*':
					now=first*second;
					break;
				case '/':
					try {
						now=second/first;
						break;
					} catch (ArithmeticException e) {
						e.printStackTrace();
					}
					
				default:
					break;
				}
				stack.push(now);
			}
		}
		return(stack.peek());
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("���룺");
		Scanner scanner=new Scanner(System.in);
		String str=scanner.nextLine();
		scanner.close();
		CalucateString cs=new CalucateString();
		String end=cs.CalucateExpression(str);
//		System.out.println(end);
		int m=cs.CalucateFomular(end);
		System.out.println("����Ϊ��"+m);
	}
	
}
