import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data){
        this.data = data;
        next = null;
    }
    
}

// circular queue implementation with array
class ArrayCircularQueue {
    int size;
    int front = -1;
    int rear = -1;
    int arr[];

ArrayCircularQueue(int n){
    size = n;
    arr = new int[size];
}    

boolean isEmpty(){
    return (front == -1 && rear ==-1);
}

boolean isFull(){
    return (rear+1)%size == front;
}

void enqueue(int val){
    if (isFull()) {
        System.out.println("queue is full");
    }
    if (front == -1) { // for 1st element push
        front = 0;
    }
    rear = (rear+1)%size; // circular increment
    arr[rear] = val;
}

int dequeue(){
    if (isEmpty()) {
        System.out.println("queue is empty");
    }
    int val = arr[front];
    if (front == rear) {// for last element remove
        front = rear = -1;
    } else {
        front = (front+1)%size;
    }
    return val;

}

int peek(){
    if (isEmpty()) {
        System.out.println("queue is empty");
    }
    int val = arr[front];
    return val;
}
}

// queue implementation with Linkedlist
class LLqueue{
    Node front = null;
    Node rear = null;

boolean isEmpty(){
    return (front==null && rear==null);
}

void enqueue(int data){
    Node n1 = new Node(data);
    if (rear == null) {
        front = rear = n1;
        return;
    }
    rear.next = n1;
    rear = n1;
}

int dequeue(){
    if (isEmpty()) {
        System.out.println("queue is empty");
        return -1;
    }
    
    int val = front.data;
    if (front == rear) {
        front = rear = null;
    }
    else{
        front = front.next;
    }
    return val;
    
}

int peek(){
    if (isEmpty()) {
        System.out.println("queue is empty");
        return -1;
    }

        int val = front.data;
        return val;
}
}

// queue implementation with two stacks
class StackQueue{
    java.util.Stack<Integer> s1 = new java.util.Stack<>();
    java.util.Stack<Integer> s2 = new java.util.Stack<>();

    boolean isEmpty(){
        return s1.isEmpty();
    }

    void enqueue(int data){ // just enqueue takes O(n)
        if (isEmpty()) {
            s1.push(data);
        }
        else{
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(data);

            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
    }

    int dequeue(){
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }
        return s1.pop();
    }

    int peek(){
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }
        return s1.peek();
    }
}

// stack using 2 queues
class StackWQueue{
    java.util.Queue<Integer> q1 = new LinkedList<>();
    java.util.Queue<Integer> q2 = new LinkedList<>();

    boolean isEmpty(){
        return q1.isEmpty() && q2.isEmpty();
    }

    void push(int data){
        if (!q1.isEmpty()) {
            q1.add(data);
        }
        else{
            q2.add(data);
        }
    }

    int pop(){
        if (isEmpty()) {
            System.out.println("stack is empty");
            return -1;
        }
        int top = -1;
        if (!q1.isEmpty()) {
            while (!q1.isEmpty()) {
                top = q1.remove();
                if (q1.isEmpty()) {
                    break;
                }
                q2.add(top);
            }
        }
        else{
            while (!q2.isEmpty()) {
                top = q2.remove();
                if (q2.isEmpty()) {
                    break;
                }
                q1.add(top);
            }
        }
        return top;
    }

    int peek(){
        if (isEmpty()) {
            System.out.println("stack is empty");
            return -1;
        }
        int top = -1;
        if (!q1.isEmpty()) {
            while (!q1.isEmpty()) {
                top = q1.remove();
                q2.add(top);
            }
        }
        else{
            while (!q2.isEmpty()) {
                top = q2.remove();
                q1.add(top);
            }
        }
        return top;
    }
}

public class Queue {
    
//first non repeating letter in a stream of characters
public static void FirstR(String s) {
    java.util.Queue<Character> q = new LinkedList<>();
    int freq[] = new int[26];

    for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        q.add(ch);
        freq[ch - 'a']++;
        while (!q.isEmpty() && freq[q.peek()-'a'] != 1) {
            q.remove();
        }
        if (q.isEmpty()) {
            System.out.println("-1");
        }
        else{
            System.out.println(q.peek());
        }
    }
}

// interleave two halves of a queue(even length)
    public static java.util.Queue<Integer> interleave(java.util.Queue<Integer> q1) {
        java.util.Queue<Integer> q2 = new LinkedList<>();
        int size = q1.size();
        for (int i = 0; i < size/2; i++) {//seperating 1st halve
            q2.add(q1.remove());
        }
        while (!q2.isEmpty()) {// interleaving
            q1.add(q2.remove());
            q1.add(q1.remove());
        }
        return q1;
    }

//reverse a queue
    public static java.util.Queue<Integer> reverse(java.util.Queue<Integer> q) {
        java.util.Stack<Integer> s1 = new java.util.Stack<>();
        while (!q.isEmpty()) {
            s1.push(q.remove());
        }
        while (!s1.isEmpty()) {
            q.add(s1.pop());
        }
        return q;
    }    

//Generate Binary Numbers
//Given a number N. The task is to generate and print all binary numbers with decimal values from
//1 to N.
    public static void GenerateBinary(int n) {
        java.util.Queue<StringBuilder> q = new LinkedList<>();
        q.add(new StringBuilder("1"));
        for (int i = 0; i < n; i++) {
            StringBuilder num = q.remove();
            System.out.println(num.toString());
            q.add(new StringBuilder(num).append("0"));
            q.add(new StringBuilder(num).append("1"));
        }
    }    
//Connect n ropes with minimum cost
// you are given N ropes of different lengths.
// You must connect them into one single rope.
// The cost of connecting two ropes = sum of their lengths.
// Goal: Minimize the total cost of connecting all ropes.  
    public static void Nropes(int arr[]) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();// uses priority queue
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]); // arranged in sorted order
        }
        int res = 0;
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            res += first + second;
            pq.add(first + second);
        }
        System.out.println(res);
    }  

// Reversing the first K elements of a Queue
// We have an integer k and a queue of integers, we need to reverse the order of the first k
// elements of the queue, leaving the other elements in the same relative order.   
    public static java.util.Queue<Integer> reverseK(java.util.Queue<Integer> q , int k) {
        java.util.Stack<Integer> s = new java.util.Stack<>();
        int remaining =  q.size()-k;
        for (int i = 0; i < k; i++) {//stack push
            s.push(q.remove());
        }
        while (!s.isEmpty()) {//get reverse from stack
            q.add(s.pop());
        }
        for (int i = 0; i < remaining; i++) {// adjust remaining elm
            q.add(q.remove());
        }
        return q;
    } 

//Job Sequencing Problem
// We have an array of jobs where every job has a deadline and associated profit if the job is
// finished before the deadline. It is also given that every job takes a single unit of time, so the
// minimum possible deadline for any job is 1. Maximize the total profit if only one job can be
// scheduled at a time. 
    static class job {
    char id;
    int deadline; int profit;
    job(char id,int deadline,int profit){
       this.id = id;
       this.deadline = deadline;
       this.profit = profit;
    }
        
    }
    public static void jobS(job jobs[]) {
    // descending order of profit
    // using lambda func
    // j1-j2 = ascending , j2-j1 = descending    
       Arrays.sort(jobs, (j1, j2) -> j2.profit - j1.profit);
       int maxdeadline = 0; int profit = 0;
       for (int i = 0; i < jobs.length; i++) {
        maxdeadline = Math.max(maxdeadline, jobs[i].deadline);
       }
       char arr[] = new char[maxdeadline];
       for (int i = 0; i < arr.length; i++) {
        arr[i] = '-';
       }
       for (int j = 0; j < jobs.length; j++) {
        for (int j3 = jobs[j].deadline-1; j3 >= 0; j3--) {//from curr deadline to 0
            if (arr[j3] == '-') { // if we find a place in between
                arr[j3] = jobs[j].id;  // occupy that place
                System.out.print(jobs[j].id+" ");
                profit+=jobs[j].profit; // collect the profit
                break; // avoid further iteration
            }
        }
       }
       System.out.println("profit: "+ profit);

    }

// sliding window Maximum
// We have an array arr[] of size N and an integer K. Find the maximum for each and every
// contiguous subarray of size K.    
    public static void window(int arr[] , int k) {
        Deque<Integer> d = new LinkedList<>(); // holds indices not values
        for (int i = 0; i < arr.length; i++) {
            if (!d.isEmpty() && d.getFirst() <= i-k) { 
                d.removeFirst(); // if some idx is not part of the current window
            }
            while (!d.isEmpty() && arr[i] >= arr[d.getLast()]) {
                d.removeLast();  // if the val of curr idx is greater than that of last of deque
            }
            d.addLast(i); // also add the current idx
            if (i >= k-1) { // starting from first window 
                System.out.print(arr[d.getFirst()]+",");
            }
        }
    }
        
    public static void main(String[] args) {
        // StackWQueue q = new StackWQueue();
        // q.push(1);
        // q.push(2);
        // q.push(3);
        // while (!q.isEmpty()) {
        //     System.out.println(q.peek());
        //     q.pop();
        // }

        // java.util.Queue<Integer> q = new LinkedList<>();
        // q.add(1);
        // q.add(2);
        // q.add(3);
        // q.add(4);
        // q.add(5);
        // q.add(6);
        // q.add(7);
        // q.add(8);
        // q.add(9);
        // q.add(10);
        // q = reverseK(q, 5);
        // while (!q.isEmpty()) {
        //     System.out.print(q.peek()+" ");
        //     q.remove();
        // }

        // int arr[] = {9, 8, 7, 6, 5};
        // Nropes(arr);

        // job jobs[] = {
        //     new job('a', 4, 20),
        //     new job('b', 1, 10),
        //     new job('c', 1, 40),
        //     new job('d', 1, 30),
        // };
        // jobS(jobs);

        int arr[] = {1,2,3,1,4,5,2,3,6};
        window(arr, 3);

    }
}

