/**
* 要求：给定一个链表，按从尾到头打印链表
* 思路： 从尾到头打印，首先想到的可能是利用栈先入后出的特性去解决，这样确实能够解决，但有一种更好的解决方法，
* 递归。递归其实也类似于栈，解决这个问题也省略了额外的内存开销
**/
class Node{
  Integer val;
  Node next;
  
  //Getter and Setter
}
class Solution{
  public void printFromTailToHead(Node head){
    if(null != head){
       if(null != head.next){
        printFromTailToHead(head.next);
       }
    }
    System.out.println(head.val);
  }
}
