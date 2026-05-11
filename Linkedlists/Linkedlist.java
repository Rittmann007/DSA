import java.util.List;

class Node {
    int data;
    Node next;

    Node(int data){
        this.data = data;
        next = null;
    }
    
}
// doubly LL
class Dnode {
    Dnode prev;
    int data;
    Dnode next;

    Dnode(int data){
        prev = null;
        this.data = data;
        next = null;
    }
    
}
public class Linkedlist {
    
    public static Node head;
    public static Node tail;
    public static int size;

    public static Dnode Dhead;
    public static Dnode Dtail;
    public static int Dsize;

// add to first of LL    
    public static void addfirst(int data) {
        Node n1 = new Node(data);
        size++;

        if (head == null) {
            head = tail = n1;
            return;
        }
        // n1.next will now point to head node
        n1.next = head;
        // n1 will now be called head
        head = n1;
    }

// add to last of LL    
    public static void addlast(int data) {
        Node n1 = new Node(data);
        size++;

        if (head == null) {
            head = tail = n1;
            return;
        }
        // tail.next will now point to n1
        tail.next = n1;
        // n1 will now be called tail
        tail = n1;
    }

// traverse the LL
    public static void traverseLL() {
        Node temp = head;
        // print untill null node is not reached
        while (temp != null) {
            System.out.print(temp.data+ " ");
            temp = temp.next;
        }
    }

// add node to the index of LL
    public static void middle(int idx,int data) {
        if (idx == 0) {// exception for first index
            addfirst(data);
            return;
        }

        Node n1 = new Node(data);
        size++;
        Node temp = head;
        int i = 0;
        while (i < idx-1) {
            temp = temp.next;
            i++;
        }
        // now i = idx-1 or the previous element

        n1.next = temp.next;
        temp.next = n1;
    }

// remove first ( just dereference so gc can do the rest)
    public static void removeF() {
        if (size == 0) {
            System.out.println("LL is empty");
            return;
        }
        else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            System.out.println(val);
            return;
        }
        int val = head.data;
        head = head.next; // main dereferencing step
        System.out.println(val);
        size--;
    }    

// remove last    
    public static void removeL() {
        if (size == 0) {
            System.out.println("LL is empty");
            return;
        }
        else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            System.out.println(val);
            return;
        }
        Node temp = head;
        for (int i = 0; i < size-2; i++) {
            temp = temp.next;
        }
        // now we have node size-2
        int val = temp.next.data;
        temp.next = null;
        tail = temp;
        System.out.println(val);
        size--;
    }    

// iterative search for a key
    public static void itrsearch(int key) {
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.data == key) {
                System.out.println(i);
                return;
            }
            temp = temp.next;
            i++;
        }
        System.out.println("nothing found");
    }   
// recursive search for a key
    public static void recSearch(Node temp,int i,int key) {
        if (temp.data == key) {
            System.out.println(i);
            return;
        }
        else if (temp == null) {
            System.out.println("nothing found");
            return;
        }
        else{
            recSearch(temp.next, i++, key);
        }
    }     

// reverse a LL
    public static void reverse() {
        // will use 3 var (prev,curr,next)
        Node prev = null;
        Node curr = tail = head;// updating tail to be head and assigning to curr
        Node next;
        while (curr != null) { // 4step process
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;// updating head
    }    

// find and remove nth element from end
    public static void nFend(int n) {
        if (n == size) {
            head = head.next;
            return;
        }
        Node prev = head;
        for (int i = 1; i < size-n; i++) {
            prev = prev.next;
        }
        prev.next = prev.next.next;
    }    

// check if LL is a palindrome
    public static Node middle() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null ) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }
        return slow;
    }    
    public static boolean Cpalindrome() {
        if (head == null || head.next == null) {// edge case
            return true;
        }
        // find middle
        Node middle = middle();

        // reverse right halve from middle
        Node prev = null;
        Node curr = middle;
        Node next;
        while (curr != null) { // 4step process
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev;
        Node left = head;

        // match right and left half
        while (right != null) {
            if (right.data != left.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }
// detect cycle in LL
    public static boolean Dcycle() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null ) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
            if (slow==fast) {
                return true;
            }
        }
        return false;
    }        
// remove cycle in LL (code is not for full cycles)
    public static void Rcycle() {
        //detect cycle
         Node slow = head;
        Node fast = head;
        boolean cycle = false;
        while (fast != null && fast.next != null ) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
            if (slow==fast) {
                cycle = true;
                break;
            }
        }
        if (cycle == false) {
            return;
        }
        //find 2nd meeting point
        Node prev = null;
        slow = head;

        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
        //remove cycle
        prev.next = null;
    }    

// merge sort with LL
    public static Node Mergemiddle(Node heading) {
        Node slow = heading;
        Node fast = heading.next;// for taking 1st half's last node as our middle
        while (fast != null && fast.next != null ) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }
        return slow;
    }    
    public static Node merge(Node left , Node right) {
        Node MergeLL = new Node(-1); // new sorted LL
        Node temp = MergeLL;

        while (left != null && right != null) {
            if (left.data < right.data) {
                temp.next = left;
                left = left.next;
                temp = temp.next;
            }
            else{
                temp.next = right;
                right = right.next;
                temp = temp.next;
            }
        }

        while (left != null) {
            temp.next = left;
            left = left.next;
            temp = temp.next;
        }
        while (right != null) {
            temp.next = right;
            right = right.next;
            temp = temp.next;
        }

        return MergeLL.next;// return the sorted head
    }     
    public static Node mergeSort(Node heading) {
    // after repeated breaking one or zero nodes remain
        if (heading == null || heading.next == null) {
            return heading;
        }
        Node middle = Mergemiddle(heading);
        Node rightHead = middle.next;
        middle.next = null;

        Node newleft  = mergeSort(heading);//for left halve
        Node newright = mergeSort(rightHead);//for right halve

        return merge(newleft,newright);//return the sorted head
    }
// zigzag LL
    public static void zigzag() {
        // find middle
         Node slow = head;
        Node fast = head.next;// for taking 1st half's last node as our middle
        while (fast != null && fast.next != null ) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }
        Node middle = slow;

        // reverse
        Node prev = null;
        Node curr = middle.next;
        middle.next = null;
        Node next;
        while (curr != null) { // 4step process
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev;
        Node left = head;

        // make zigzag
        Node nextL; Node nextR;
        while (right != null && left != null) {
            nextL = left.next; // main steps
            left.next = right; //
            nextR = right.next;//
            right.next = nextL;//

            left = nextL;// pointer updation
            right = nextR;//
        }
    }    
// addfirst of doubly LL
    public static void Daddfirst(int data) {
        Dnode n1 = new Dnode(data);
        Dsize++;

        if (Dhead == null) {
            Dhead = Dtail = n1;
            return;
        }
        
        n1.next = Dhead;
        Dhead.prev = n1;
        Dhead = n1;
    }    

// remove first of doubly LL
    public static void DremoveF() {
         if (Dsize == 0) {
            System.out.println("LL is empty");
            return;
        }
        else if (size == 1) {
            int val = Dhead.data;
            Dhead = Dtail = null;
            Dsize = 0;
            System.out.println(val);
            return;
        }
        int val = Dhead.data;
        Dhead = Dhead.next; 
        Dhead.prev = null;
        System.out.println(val);
        Dsize--;
    }    

// traverse the Doubly LL    
    public static void traverseDLL() {
        Dnode temp = Dhead;
        // print untill null node is not reached
        while (temp != null) {
            System.out.print(temp.data+ " ");
            temp = temp.next;
        }
    }

// reverse a doubly LL
    public static void Dreverse() {
        // will use 3 var (prev,curr,next)
        Dnode prev = null;
        Dnode curr = Dtail = Dhead;// updating tail to be head and assigning to curr
        Dnode next;
        while (curr != null) { // 4step process
            next = curr.next;
            curr.next = prev;
            curr.prev = next; // extra step
            prev = curr;
            curr = next;
        }
        Dhead = prev;// updating head
    }        

// Intersection of Two Linked Lists    
// In a system there are two singly linked list.
// , forming an inverted Y-shaped list. Write a
// program to get the point where two linked lists merge.
// We have to find the intersection part in this system   
    public static int intersection(Node head1,Node head2) {
        Node t1 = head1;
        Node t2 = head2;

        while (t1 != t2) {
            if (t1 == null) {// if 1st ptr reaches null then we point it to the 2nd head
                t1 = head2;
            }
            else if (t2 == null) {// same for 2nd
                t2 = head1;
            }
            else{
                t1 = t1.next;
                t2 = t2.next;
            }
        }
        return t1.data;
    } 

// Delete N Nodes After M Nodes of a Linked List
// We have a linked list and two integers M and N. Traverse the linked list such that you retain M
// nodes then delete next N nodes, continue the same till end of the linked list. Difficulty Level:
// Rookie
    public static void NM(int m,int n) {
        Node temp = head;
        Node p1 = head;
        Node p2 = head;
        while (temp != null) {
            for (int i = 0; i < m-1; i++) {//for even traversal
                p1 = p1.next;
                p2 = p2.next;
                temp = temp.next;
            }
            for (int i = 0; i < n+1; i++) {//for even traversal
                p2 = p2.next;
                temp = temp.next;
                
            }
            p1.next = p2;//skipping n nodes
            p1 = p2;
           
        }
    }    

// Swapping Nodes in a Linked List
// We have a linked list and two keys in it, swap nodes for two given keys. Nodes should be
// swapped by changing links. Swapping data of nodes may be expensive in many situations when
// data contains many fields. It may be assumed that all keys in the linked list are distinct    
    public static void swap(int key1,int key2) {
        if (key1 == key2) return;

        Node prev1 = null, curr1 = head;
        while (curr1 != null && curr1.data != key1) {
            prev1 = curr1;
            curr1 = curr1.next;
        }

        Node prev2 = null, curr2 = head;
        while (curr2 != null && curr2.data != key2) {
            prev2 = curr2;
            curr2 = curr2.next;
        }

        // if either key not found, do nothing
        if (curr1 == null || curr2 == null) return;

        // update previous pointers (handle head cases)
        if (prev1 != null) prev1.next = curr2; else head = curr2;
        if (prev2 != null) prev2.next = curr1; else head = curr1;

        // swap next pointers
        Node temp = curr1.next;
        curr1.next = curr2.next;
        curr2.next = temp;
    
    }

// segregate Odd Even Linked List
// We have a Linked List of integers, write a function to modify the linked list such that all even
// numbers appear before all the odd numbers in the modified linked list. Also, keep the order of
// even and odd numbers same    
    public static void segregate() {
        Node odd = new Node(-1);//ptr of odd list
        Node even = new Node(-1);//ptr of even list
        Node temp = head;
        Node oddhead = odd;
        Node evenhead = even;

        while (temp != null) {
            if (temp.data%2 != 0) {
                odd.next = temp;
                odd = odd.next;
            }
            else{
                even.next = temp;
                even = even.next;
            }
            temp = temp.next;
        }
        odd.next = null;
        even.next = oddhead.next;//joining odd to even
        head = evenhead.next;

    }
// Merge k Sorted Lists
// We have K sorted linked lists of size N each, merge them and print the sorted output.   
// inefficient one 
    public static void kSorted(Node arr[],int k,int n) {
        Node heading = arr[0];
        for (int i = 1; i < k; i++) {
            
                heading = merge(heading, arr[i]);
        
        }
        head = heading;
    }
// mergeKLists(arr, last) repeatedly merges pairs of lists from the two ends of
// arr until only one sorted list remains; it returns the merged head in arr[0].
// Time: O(N * k * log k) (divide-and-conquer pairwise merging).
// efficient one
    public static Node mergeKLists(Node arr[], int last) {
        while (last != 0) {
            int i = 0, j = last;
            while (i < j) {
                arr[i] = merge(arr[i], arr[j]);
                i++;
                j--;
                if (i >= j)
                    last = j;
            }
        }
        return arr[0];
    }
    public static void main(String[] args) {
        int k = 3;int n=3;
       Node arr[] = new Node[k];

       arr[0] = new Node(9);
       arr[0].next = new Node(8);
       arr[0].next.next = new Node(7);
       arr[1] = new Node(6);
       arr[1].next = new Node(5);
       arr[1].next.next = new Node(4);
       arr[2] = new Node(3);
       arr[2].next = new Node(2);
       arr[2].next.next = new Node(1);
       head = mergeKLists(arr, k-1);
       traverseLL();

    }
}
