import java.util.*;

// stack using Arraylist
class ArraylistStack {
    ArrayList<Integer> S = new ArrayList<>();

    boolean isEmpty() {
        return S.size()==0;
    }

    void push(int data){
        S.add(data);
    }

    int pop(){
        if (isEmpty()) {
            return -1;
        }
        else{
            return S.removeLast();

        }
    }

    int peek(){
        if (isEmpty()) {
            return -1;
        }
        else{
            return S.getLast();
        }
    }
    
}

class Node {
    int data;
    Node next;

    Node(int data){
        this.data = data;
        next = null;
    }
    
}

//stack using LinkedList
class LinkedlistStack {
    static Node head = null;

    boolean isEmpty(){
        return head==null;
    }

    void push(int data){
        Node n1 = new Node(data);

        if (isEmpty()) {
            head = n1;
            return;
        }
        n1.next = head;
        head = n1;
    }

    int pop(){
        if (isEmpty()) {
            return -1;
        }
        int val = head.data;
        head = head.next;
        return val;
    }

    int peek(){
        if (isEmpty()) {
            return -1;
        }
        return head.data;
    }
    
}

public class Stack {

//push at bottom of a stack
    public static void Pbottom(java.util.Stack<Integer> s,int num) {
        if (s.isEmpty()) {
            s.push(num);
            return;
        }
        int val = s.pop();//first pop
        Pbottom(s,num);
        s.push(val);//then push
    }    

//reverse a string using stack
    public static void reverseStr(String str) {
        java.util.Stack<Character> s = new java.util.Stack<>();
        for (int i = 0; i < str.length(); i++) {//push
            s.push(str.charAt(i));
        }
        StringBuilder st = new StringBuilder();
        while (!s.isEmpty()) {//then pop
            st.append(s.pop());
        }
        System.out.println(st.toString());
    }   
    
//reverse a stack
    public static void Rstack(java.util.Stack<Integer> s) {
        if (s.isEmpty()) {
            return;
        }
        int val = s.pop();//pop val
        Rstack(s);
        Pbottom(s,val);//then push it in bottom
    }    

//stock span problem
    public static void StockSpan(int stock[],int span[]) {
        java.util.Stack<Integer> s = new java.util.Stack<>();
        span[0] = 1; // adjust for first idx
        s.push(0);
        for (int i = 1; i < stock.length; i++) {
            int curr = stock[i]; //curr price
            while (!s.isEmpty() && curr >= stock[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) { // for highest price
                span[i] = i+1;
            }
            else{
                span[i] = i-s.peek();
            }
            s.push(i); // push curr idx
        }
        for (int i = 0; i < span.length; i++) {
            System.out.print(span[i]+",");
        }
    }    
//next greater element of each element
    public static void nextGreater(int arr[]) {
        java.util.Stack<Integer> s = new java.util.Stack<>();
        int nextG[] = new int[arr.length]; //new array for storing
        for (int i = arr.length-1; i >=0; i--) { //right to left(to already scan the next greater)
            while (!s.isEmpty() && arr[i]>=arr[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) { //no next greater exists
                nextG[i] = -1;
            }
            else{  // if bigger element found
                nextG[i] = arr[s.peek()];
            }
            s.push(i);
        }
        for (int i = 0; i < nextG.length; i++) {
            System.out.print(nextG[i]+",");
        }
      // 4 variations of the problem
      // 1. next greater right (curr problem)
      // 2. next greater left
      //    (we'll just loop from left to right)
      // 3. next smaller right
      //    (we'll change while loop cond to -> (arr[i]<=arr[s.peek()]) )  
      // 4. next smaller left
      //    (we'll do the changes from 2 and 3 both)
    }    

// check valid parenthesis
    public static boolean isPair(char c1,char c2) {
        if (c1 == '(' && c2 == ')') {
            return true;
        }
        if (c1 == '{' && c2 == '}') {
            return true;
        }
        if (c1 == '[' && c2 == ']') {
            return true;
        }
        return false;
    }    
    public static boolean validParenthesis(String str) {
        java.util.Stack<Character> s = new java.util.Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (curr == '(' || curr == '{' || curr == '[') { //if opening
                s.push(curr);
            }
            else{ //if closing
                if (s.isEmpty()) {// if closing bracket appears at first directly
                    return false;
                }
                if (!isPair(s.peek(), curr)) { //is making pair
                    return false;
                }
                else{ //if yes then pop
                    
                    s.pop();
                }
            }
        }
        if (s.isEmpty()) { // at last check the stack
            return true;
        }
        else{
            return false;
        }
    }

// check duplicate parenthesis
    public static boolean duplicateP(String str) {
         java.util.Stack<Character> s = new java.util.Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (curr != ')') { //if opening
                s.push(curr);
            }
            else{ //if closing
                int count = 0;
                while (s.peek()!='(') {
                    s.pop();
                    count++;
                }
                if (count<1) {// if nothing exists in between
                    return true;
                }else{
                    s.pop();

                }
            }
        }
        return false;
    }    
// max rectangular area in a histogram
    public static int[] nextSmallerLeft(int arr[]) {
         java.util.Stack<Integer> s = new java.util.Stack<>();
        int nextG[] = new int[arr.length]; //new array for storing
        for (int i = 0; i < arr.length; i++) { 
            while (!s.isEmpty() && arr[i]<=arr[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) { 
                nextG[i] = arr.length-1;
            }
            else{  
                nextG[i] = s.peek();
            }
            s.push(i);
        }
        return nextG;
    }    
    public static int[] nextSmallerRight(int arr[]) {
         java.util.Stack<Integer> s = new java.util.Stack<>();
        int nextG[] = new int[arr.length]; //new array for storing
        for (int i = arr.length-1; i >=0; i--) { 
            while (!s.isEmpty() && arr[i]<=arr[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) { //no next greater exists
                nextG[i] = arr.length-1;
            }
            else{  
                nextG[i] = s.peek();
            }
            s.push(i);
        }
        return nextG;
    }    
    public static void Maxhistogram(int height[]) {
        int nextSmallerLeft[] = nextSmallerLeft(height);
        int nextSmallerRight[] = nextSmallerRight(height);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            int idxNextSmallerL = nextSmallerLeft[i];
            int idxNextSmallerR = nextSmallerRight[i];
            int width = idxNextSmallerR-idxNextSmallerL-1;
            int currarea = height[i] * width;
            max = Math.max(max, currarea);
        }
        
        System.out.println(max);
    }

// Palindrome Linked List
// We have a singly linked list of characters, write a function that returns true if the given list is a
// palindrome, else false.    
    public static void isPalindromeLL(LinkedList<Character> ll) {
        java.util.Stack<Character> s = new java.util.Stack<>();
        StringBuilder stri = new StringBuilder();
        for (Character c : ll) { //push in stack
            s.push(c);
            stri.append(c);
        }
        StringBuilder strl = new StringBuilder();
        while (!s.isEmpty()) { //then pop
            strl.append(s.pop());
        }
        if (stri.toString().equals(strl.toString())) {
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }

// Simplify Path
// We hava an absolute path for a file (Unix-style), simplify it. Note that absolute path always begin
// with ‘/’ ( root directory ), a dot in path represent current directory and double dot represents
// parent directory  
    public static String[] split(String str, char ch) {
    ArrayList<String> arr = new ArrayList<>();
    StringBuilder current = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == ch) {
            if (current.length() > 0) {  // only add non-empty strings
                arr.add(current.toString());
                current.setLength(0);
            }
        } else {
            current.append(str.charAt(i));
        }
    }

    if (current.length() > 0) {  // add last part if not empty
        arr.add(current.toString());
    }
    return arr.toArray(new String[0]);
}
    public static void SimplifyPath(String str) {
        String arr[] = split(str, '/');
        java.util.Stack<String> s = new java.util.Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("..") && !s.isEmpty()) {//pop when .. comes
                s.pop();
            }
            if (!arr[i].equals(".") && !arr[i].equals("..") ) {// else push
                s.push(arr[i]);
            }
        }
        java.util.Stack<String> s2 = new java.util.Stack<>();
        while (!s.isEmpty()) { // reversing the stack to get ans
            s2.push(s.pop());
        }
        StringBuilder st = new StringBuilder();
        while (!s2.isEmpty()) { // then appending in string
            st.append("/");
            st.append(s2.pop());
        }
        if (st.length() == 0) {
            st.append("/");
        }
        System.out.println(st.toString());
    }  

// Decode a string
// We have an encoded string s and the task is to decode it. The pattern in which the strings are
// encoded is as follows.
    public static void DecodeStr(String str) {
        java.util.Stack<Integer> sint = new java.util.Stack<>();
        java.util.Stack<StringBuilder> schar = new java.util.Stack<>();
        StringBuilder tempstr = new StringBuilder();
        int tempint = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) <= '9' && str.charAt(i) >= '0') {// can use Character.isDigit(str.charAt(i))
                // to take numbers of more than one digit
                tempint = tempint*10 +(str.charAt(i) - '0');
            }
            else if (str.charAt(i) == '[') { // stack push
                schar.push(tempstr);
                sint.push(tempint);
                tempint = 0;
                tempstr = new StringBuilder();
            }
            else if (str.charAt(i) == ']') { // real computaion
                StringBuilder temp = tempstr;
                tempstr = schar.pop();
                int num = sint.pop();
                while (num > 0) {
                    tempstr.append(temp);
                    num--;
                }
            }
            else{ // collecting in between strings
                tempstr.append(str.charAt(i));
            }
        }
        System.out.println(tempstr.toString());
    }    
    public static void main(String[] args) {
        // java.util.Stack<Integer> s = new java.util.Stack<>();
        // s.push(0);
        // s.push(1);
        // s.push(2);
        // s.push(3);
        // Rstack(s);
        // while (!s.isEmpty()) {
        //     System.out.println(s.peek());
        //     s.pop();
        // }
        DecodeStr("3[ab2[cd]]4[rt]");
    }
}

