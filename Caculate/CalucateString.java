
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
	 思想为读取中缀表达式，数字直接加入表达式，符号需加入栈中进行操作。如果符号为左括号则直接入栈，右括号则一直出栈直至栈顶元素为左括号，如果将要入栈元素的
	 优先级大于栈顶元素优先级亦直接入栈，否则一直出栈值栈顶元素优先级不大于该元素
	 */
	
	@SuppressWarnings("deprecation")
	public String CalucateExpression(String str) {
		/*各算术符的优先级*/
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
		
		Stack<Character>stack=new Stack<>();//用于存储符号
		stack.push('#');//避免空栈情况出现
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
					stack.push(str.charAt(i));//将已入栈的左括号挤出
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
	 思想为读取后缀表达式，数字直接入栈，当读到算术符时，则从栈中读取两个数字进行运算，并将运算结果压入栈中，如此往复，直到读取最后一个字符
	 */
	
	public int CalucateFomular(String str){
		Stack<Integer> stack=new Stack<>();
		int i=0;
		while(i<str.length()){
			if (Character.isDigit(str.charAt(i))) {
				stack.push(Integer.parseInt(String.valueOf(str.charAt(i))));//将char类型先转换成String类型，而后再转换为int类型
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
		System.out.println("输入：");
		Scanner scanner=new Scanner(System.in);
		String str=scanner.nextLine();
		scanner.close();
		CalucateString cs=new CalucateString();
		String end=cs.CalucateExpression(str);
//		System.out.println(end);
		int m=cs.CalucateFomular(end);
		System.out.println("其结果为："+m);
	}
	
}
