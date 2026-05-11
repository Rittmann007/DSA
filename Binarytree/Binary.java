import java.util.*;

class Node {
int data;
Node right; Node left;

Node(int data){
    this.data = data;
    left = null;
    right = null;
}}

//for diameter of a tree(optimized problem)
class info {
int dia ; int height;

info(int dia , int height){
    this.dia = dia;
    this.height = height;
}
}

//for topview of a tree problem
class TopviewInfo {
Node n1; int HorizontalDistance;
TopviewInfo(Node n1,int HorizontalDistance){
    this.n1 = n1;
    this.HorizontalDistance = HorizontalDistance;
}
}

// class for binary tree build
class Binarytree {
static int idx = -1;
Node buildtree(int preorderSequence[]){
    idx++;
    if (preorderSequence[idx] == -1) {
        return null;
    }
    Node n1 = new Node(preorderSequence[idx]);
    n1.left = buildtree(preorderSequence);
    n1.right = buildtree(preorderSequence);
    return n1;
}
// preOrder traversal
void preorder(Node root){
    if (root == null) {
        return;
    }
    System.out.print(root.data+" ");
    preorder(root.left);
    preorder(root.right);
}
// inOrder traversal
void inorder(Node root){
    if (root == null) {
        return;
    }
    inorder(root.left);
    System.out.print(root.data+" ");
    inorder(root.right);
}
// postOrder traversal
void postorder(Node root){
    if (root == null) {
        return;
    }
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.data+" ");
}
// levelOrder traversal
void levelorder(Node root){
    if (root == null) {
        return;
    }
    java.util.Queue<Node> q1 = new java.util.LinkedList<>();
    q1.add(root);
    q1.add(null);
    while (!q1.isEmpty()) {
        Node n1 = q1.remove();
        if (n1 == null) { // only required for viewing purpose
            System.out.println();
            if (q1.isEmpty()) {
                break;
            }
            else{
                q1.add(null);
            }
        }
        else{
            System.out.print(n1.data);
            if (n1.left != null) {
                
                q1.add(n1.left);
            }
            if (n1.right != null) {
                
                q1.add(n1.right);
            }
        }
    }
}
// height of the tree
int height(Node root){
    if (root == null) {
        return 0;
    }
    int Lh = height(root.left);
    int Rh = height(root.right);
    return Math.max(Lh, Rh)+1;
}
// count of nodes
int count(Node root){
    if (root == null) {
        return 0;
    }
    int Lh = count(root.left);
    int Rh = count(root.right);
    return (Lh+Rh)+1;
}
// sum of all nodes
int sum(Node root){
    if (root == null) {
        return 0;
    }
    int Lh = sum(root.left);
    int Rh = sum(root.right);
    return (Lh+Rh)+root.data;
}
// diameter of the tree 1 (O(n^2))
int diameter(Node root){
    if (root == null) {
        return 0;
    }
    int Lh = height(root.left);
    int Ldia = diameter(root.left);
    int Rh = height(root.right);
    int Rdia = height(root.right);

    int self = Lh + Rh + 1;
    return Math.max(self, Math.max(Rdia, Ldia));
}
// diameter of the tree 2 (O(n))
info diameter2(Node root){
    if (root == null) {
        return new info(0,0);
    }
    
    info Linfo = diameter2(root.left);
    info Rinfo = diameter2(root.right);

    int self = Linfo.height + Rinfo.height + 1;
    int diameter =  Math.max(self, Math.max(Linfo.dia, Rinfo.dia));
    int height = Math.max(Linfo.height, Rinfo.height)+ 1;
    return new info(diameter, height);
}
}

public class Binary {

//identify if subtree of another tree
    public static boolean isidentical(Node n1 , Node subroot) {
        if (n1==null && subroot==null) {
            return true;
        }
        if ((n1==null && subroot!=null)||(n1!=null && subroot==null)) {//1st criteria
            return false;
        }
        if (n1.data!=subroot.data) {//2nd criteria
            return false;
        }
        if (!isidentical(n1.left, subroot.left)) {//3rd citeria
            return false; 
        }
        if (!isidentical(n1.right, subroot.right)) {//4th criteria
            return false;
        }
        return true;
    }
    public static boolean isSubtree(Node root , Node subroot) {
        if (root == null) {
            return false;
        }
        if (root.data == subroot.data) {
            if (isidentical(root,subroot)) {
                return true;
            }
        }
        // if subtree exists in left or right subtree
        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
    }

// top view of a tree
    public static void Topview(Node root) {
        if (root == null) {
            return;
        }
        java.util.Queue<TopviewInfo> q1 = new LinkedList<>();
        java.util.HashMap<Integer,Integer> h1 = new HashMap<>();
        q1.add(new TopviewInfo(root,0));
        int min = 0;
        int max = 0;
        while (!q1.isEmpty()) {
        TopviewInfo t1 = q1.remove();
        if (!h1.containsKey(t1.HorizontalDistance)) {
            
            h1.put(t1.HorizontalDistance, t1.n1.data);
        }
            if (t1.n1.left != null) {
                
                q1.add(new TopviewInfo(t1.n1.left,t1.HorizontalDistance-1));
                min = Math.min(min, t1.HorizontalDistance-1);// max cannot be affected by left child
            }
            if (t1.n1.right != null) {
                
                q1.add(new TopviewInfo(t1.n1.right,t1.HorizontalDistance+1));
                max = Math.max(max, t1.HorizontalDistance+1);// same for min
            }
        
        }
        for (int i = min; i <= max; i++) {
            System.out.print(h1.get(i));
        }

    }    

// kth level of a tree
    public static void Kth(Node n,int level,int k) {
        if (n==null) {
            return;
        }
        if (level == k) {
            System.out.print(n.data+" ");
            return;//dont have to go to the childs
        }
        Kth(n.left, level+1, k);
        Kth(n.right, level+1, k);
    }    

// lowest common ancestor for two nodes
    public static boolean getpath(Node root,int n,java.util.ArrayList<Integer> path) {
        if (root == null) {
            return false;
        }
        path.add(root.data);
        if (root.data == n) {
            return true;
        }
        // if found in left or right subtree
        if (getpath(root.left, n, path) || getpath(root.right, n, path)) {
            return true;
        }
        path.remove(path.size()-1);// else remove current root(backtrack)
        return false;// and return false

    }
    public static void Lca(int n1,int n2,Node root) {
        java.util.ArrayList<Integer> path1 = new ArrayList<>();
        java.util.ArrayList<Integer> path2 = new ArrayList<>();

        getpath(root,n1,path1);// getting the paths
        getpath(root,n2,path2);
        
        int i = 0; // then traversing to find the common
        while(i < path1.size()-1 && i < path2.size()-1) {
            if (path1.get(i)!=path2.get(i)) {
                break;
            }
            i++;
        }

        System.out.println(path1.get(i-1));
    }    
    //more optimized in terms of space complexity
    public static Node Lca2(int n1,int n2,Node root) {
        if (root == null || root.data == n1 || root.data == n2) {
            return root;
        }
        Node left = Lca2(n1,n2,root.left);//find leftsubtree
        Node right = Lca2(n1, n2, root.right);//find right subtree
        if (left == null) {//anyone null, means the two are on other subtree
            return right;
        }
        if(right == null){
            return left;
        }
            return root;//if left and right both are not null
    }

// minimum distance between two nodes
    public static int distH(Node root,int n1) {
        if (root == null) {
            return -1;
        }
        if (root.data == n1) {
            return 0;
        }
        int left = distH(root.left, n1);
        int right = distH(root.right, n1);
        if (left == -1 && right == -1) {
            return -1;
        }
        else if (left == -1) {// each node will +1 the result
            return right+1; // distance from current node to n1
        }
        else{
            return left+1;
        }
    }
    public static int minDis(int n1,int n2,Node root) {
        Node lca = Lca2(n1, n2, root);
        int dis1 = distH(lca,n1);//find lca to node distance
        int dis2 = distH(lca,n2);

        return dis1+dis2;
    }    

// kth ancestor of a node
    public static int kthances(Node root,int n,int k) {
        if (root == null) {
            return -1;
        }
        if (root.data == n) {//given node reached
            return 0;
        }
        int left = kthances(root.left, n, k);
        int right = kthances(root.right, n, k);
        if (left == -1 && right == -1) {
            return -1;
        }
        int max = Math.max(left, right);
        if (max+1 == k) { //target ancestor reached
            System.out.println(root.data);
        }
        return max+1;
    }    

//transform to sum tree
    public static int sumTree(Node root) {
        if (root == null) {
            return 0;
        }
        int left = sumTree(root.left);
        int right = sumTree(root.right);
        
        int data = root.data;
        int newright = root.right==null?0:root.right.data;//not to reference null val
        int newleft = root.left==null?0:root.left.data;
        root.data = left+right+newright+newleft;
        
        return data;
    }    
    public static void main(String[] args) {
        // int arr[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        // Binarytree b1 = new Binarytree();
        // Node n1 = b1.buildtree(arr);
        // System.out.println(n1.data);
        // b1.levelorder(n1);
        // System.out.println(b1.diameter2(n1).dia);

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        Node subroot = new Node(2);
        subroot.left = new Node(4);
        subroot.right = new Node(5);
       
       sumTree(root);
       Binarytree b1 = new Binarytree();
       b1.levelorder(root);
    }
}

