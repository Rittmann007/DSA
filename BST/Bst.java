import java.util.*;
import java.util.stream.Gatherer.Integrator;

class Node {
int data;Node left;Node right;

Node(int data){
    this.data = data;
    left = null;
    right = null;
}}

class info {
boolean isBST;int size;
int max;int min;

info(boolean isBST,int size,int max,int min){
    this.isBST = isBST;
    this.size = size;
    this.max = max;
    this.min = min;
}}

class AvlNode {
int data;AvlNode left;AvlNode right;
int height;

AvlNode(int data){
    this.data = data;
    left = null;
    right = null;
    height = 0;
}}

// binary search trees
public class Bst {
// build a bst    
    public static Node insert(Node root,int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (root.data > data) {// update left
            root.left = insert(root.left, data);
        }
        if (root.data < data) {// update right
            root.right = insert(root.right, data);
        }
        return root;
    }
    public static Node BuildBst(int arr[],Node root) {
        // insert values one by one
        for (int i = 0; i < arr.length; i++) {
            root = insert(root, arr[i]);
        }
        return root;
    }
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

// search in a bst    
    public static boolean search(int key,Node root) {
        if (root == null) {
            return false;
        }
        if (root.data == key) {
            return true;
        }
        else if (root.data > key) {//search left
            return search(key, root.left);
        }
        else{//search right
            return search(key, root.right);
        }
    }

// delete a node in bst
    public static Node InorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }    
    public static Node Deletenode(Node root,int key) {
        if (root.data > key) {//assign to parent to make delete actually happen
            root.left =  Deletenode(root.left, key);
        }
        else if (root.data < key) {
            root.right = Deletenode(root.right, key);
        }
        else {// key to delete found!!
            // case 1: no child
            if (root.left == null && root.right == null) {
                return null;
            }
            // case 2: one child
            if (root.left == null) {
                return root.right;
            }
           else if (root.right == null) {
                return root.left;
            }
            
            // case 3: both child
            Node inorderS = InorderSuccessor(root.right);
            root.data = inorderS.data;
            root.right = Deletenode(root.right, inorderS.data);
            
            
        }
        return root;
    }

// print in range (between k1 and k2 including)
    public static void Prange(Node root,int k1,int k2) {
        if (root == null) {//base case
            return;
        }
        if (root.data>=k1 && root.data<=k2) {//case 1
            Prange(root.left, k1, k2);
            System.out.print(root.data+" ");//root lies in range
            Prange(root.right, k1, k2);
        }
        if (root.data > k2) {//case 2
            Prange(root.left, k1, k2);
        }
        else if (root.data < k1) {//case 3
            Prange(root.right, k1, k2);
        }
    }    

// print all paths from root to leaf
    public static void PrintA(java.util.ArrayList<Integer> a1) {
        for (int i = 0; i < a1.size(); i++) {
            System.out.print(a1.get(i)+" ");
        }
        System.out.println();
    }
    public static void root2leaf(Node root,java.util.ArrayList<Integer> a1) {
        if (root == null) {
            return;
        }
        a1.add(root.data);
        if (root.left == null && root.right == null) {//at leaf
            PrintA(a1);
        }
        root2leaf(root.left, a1);
        root2leaf(root.right, a1);
        a1.remove(a1.size()-1);//backtracking step
    }    

// is valid BST
    public static boolean isValidBst(Node root,Node max,Node min) {
        if (root == null) {
            return true;
        }
        if (min!=null && root.data<=min.data) {//duplicate vals are not allowed
            return false;
        }
        else if (max!=null && root.data>=max.data) {
            return false;
        }
        return isValidBst(root.left, root, min) && isValidBst(root.right, max, root);
    }    

// Mirror a BST
    public static Node Mirror(Node root) {
        if (root == null) {
            return null;
        }
        Node left = Mirror(root.left);// make each subtree a mirror
        Node right = Mirror(root.right);
        root.left = right;// swap subtrees
        root.right = left;
        return root;
    }    
    public static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }

// sorted array to a balanced BST
    public static Node ArrToBST(int arr[],int start,int end) {
        if (start > end) {// base case 
            return null;
        }
        int mid = start + (end-start)/2;
        Node root = new Node(arr[mid]);//mid node as root
        root.left = ArrToBST(arr, start, mid-1);//left subtree = left of mid
        root.right = ArrToBST(arr, mid+1, end);//right subtree = right of mid
        return root;
    }    

// convert a BST to a balanced BST
    public static void InorderSequence(Node root,java.util.ArrayList<Integer> a1) {
        if (root == null) {
            return;
        }
        InorderSequence(root.left, a1);
        a1.add(root.data);
        InorderSequence(root.right, a1);
    }
    public static Node ArrToBSTHelper(java.util.ArrayList<Integer> a1,int start,int end) {
        if (start > end) {// base case 
            return null;
        }
        int mid = start + (end-start)/2;
        Node root = new Node(a1.get(mid));//mid node as root
        root.left = ArrToBSTHelper(a1, start, mid-1);//left subtree = left of mid
        root.right = ArrToBSTHelper(a1, mid+1, end);//right subtree = right of mid
        return root;
    }    
    public static Node BalancedBst(Node root) {
        java.util.ArrayList<Integer> a1 = new ArrayList<>();
        InorderSequence(root, a1);// get inorder
        return ArrToBSTHelper(a1, 0, a1.size()-1);//then balance
    }    

// size of largest BST in a binary tree
    public static int maxsize = 0;
    public static info LargestBST(Node root) {
        if (root == null) {
            return new info(true,0,Integer.MAX_VALUE,Integer.MIN_VALUE);
        }
        info right = LargestBST(root.right);
        info left = LargestBST(root.left);

        int size = left.size+right.size+1;
        int max = root.data;
        int min = root.data;
        if (left.size > 0) {// to tackle null nodes
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
        }
        if (right.size > 0) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
        }
        
        if ((left.size > 0 && root.data <= left.max) || 
            (right.size > 0 && root.data >= right.min)) {
            return new info(false, size, max, min);// not a Bst
        }
        if (left.isBST && right.isBST) {// valid BSt
            maxsize = Math.max(maxsize, size);
            return new info(true, size, max, min);
        }
        return new info(false, size, max, min);

    }   

// merge two BSTs
    public static Node MergeBST(Node root1,Node root2) {
        // step 1:
        java.util.ArrayList<Integer> a1 = new ArrayList<>();
        InorderSequence(root1, a1);
        // step 2:
        java.util.ArrayList<Integer> a2 = new ArrayList<>();
        InorderSequence(root2, a2);

        // step 3:
        java.util.ArrayList<Integer> Final = new ArrayList<>();
        int i = 0;int j = 0;
        while (i < a1.size() && j < a2.size()) {
            if (a1.get(i) < a2.get(j)) {
                Final.add(a1.get(i));
                i++;
            }
            else{
                Final.add(a2.get(j));
                j++;
            }
        }
        while (i < a1.size()) {
            Final.add(a1.get(i));
            i++;
        }
        while (j < a2.size()) {
            Final.add(a2.get(j));
            j++;
        }

        // step 4:
        return ArrToBSTHelper(Final, 0, Final.size()-1);

    }    

// avl tree insertion
    public static int getHeight(AvlNode root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }
    public static int Bf(AvlNode root) {
        if (root == null) {
            return 0;
        }
        return getHeight(root.left)-getHeight(root.right);
    }
    public static AvlNode rightrotate(AvlNode x) {
        AvlNode y = x.left;
        AvlNode t2 = y.right;

        // rotate
        y.right = x;
        x.left = t2;

        // update height
        x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;

        // return updated root
        return y;
    }
    public static AvlNode leftrotate(AvlNode x) {
        AvlNode y = x.right;
        AvlNode t2 = y.left;

        // rotate
        y.left = x;
        x.right = t2;

        // update height
        x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;

        // return updated root
        return y;
    }
    public static void Avlpreorder(AvlNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data+" ");
        Avlpreorder(root.left);
        Avlpreorder(root.right);
    }
    public static AvlNode Avlinsert(AvlNode root,int key) {
        if (root == null) {
            return new AvlNode(key);
        }
        if (key < root.data) {
            root.left = Avlinsert(root.left, key);
        }
        else if (key > root.data) {
            root.right = Avlinsert(root.right, key);
        }
        else{ // duplicates are not allowed
            return root;
        }
        
        // update height
        root.height = getHeight(root.left)+getHeight(root.right)+1;
        int balanceFactor = Bf(root);

        // LL case
        if (balanceFactor > 1 && key < root.left.data) {
            return rightrotate(root);
        }
        // LR case
        if (balanceFactor > 1 && key > root.left.data) {
            root.left = leftrotate(root.left);
            return rightrotate(root);
        }
        // RR case
        if (balanceFactor < 1 && key > root.right.data) {
            return leftrotate(root);
        }
        // RL case
        if (balanceFactor < 1 && key < root.right.data) {
            root.right = rightrotate(root.right);
            return leftrotate(root);
        }
        return root;
    }

    public static void main(String[] args) {
        // int arr[] = {8,5,3,6,10,11};
        // Node root = BuildBst(arr, null);
        // preorder(root);
        // System.out.println();
        AvlNode root = null;
        root = Avlinsert(root, 10);
        root = Avlinsert(root, 20);
        root = Avlinsert(root, 30);
        root = Avlinsert(root, 40);
        root = Avlinsert(root, 50);
        root = Avlinsert(root, 25);

        Avlpreorder(root);
        
    }
}

