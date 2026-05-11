import java.util.*;

class minheap {
private ArrayList<Integer> arr = new ArrayList<>();

void insert(int data){
    // add at last index
    arr.add(data);
    // fix heap from added element
    int x = arr.size()-1;
    int parent = (x-1)/2;
    
    // in minheap parent < child
    //for making it maxheap we just have to change the
    // comparision operator
    while (arr.get(x) < arr.get(parent)) {
        int temp = arr.get(x);
        arr.set(x,arr.get(parent));
        arr.set(parent, temp);
        
        // update x and parent for next iteration
        x = parent;
        parent = (x-1)/2;
    }
}

void peek(){
    System.out.println(arr.get(0));
}

void heapify(int rootidx){
    int leftidx = 2*rootidx+1;
    int rightidx = 2*rootidx+2;
    int minidx = rootidx;
    
    // find minimum of the three
    if (leftidx < arr.size() && arr.get(leftidx)<arr.get(minidx)) {
        minidx = leftidx;
    }
    if (rightidx < arr.size() && arr.get(rightidx)<arr.get(minidx)) {
        minidx = rightidx;
    }
    // swap with the minimum
    if (minidx != rootidx) {
        int temp = arr.get(minidx);
        arr.set(minidx, arr.get(rootidx));
        arr.set(rootidx, temp);

        // apply heapify at minidx again
        heapify(minidx);
    }
}

// delete the root
int delete(){
    // first and last node swap
    int temp = arr.get(arr.size()-1);
    arr.set(arr.size()-1, arr.get(0));
    arr.set(0, temp);

    // remove last node
    int data = arr.remove(arr.size()-1);

    // heapify
    heapify(0);
    return data;
}

boolean isEmpty(){
    return arr.size() == 0;
}}

class Car implements Comparable<Car>{
int x;int y;int distsq;int idx;
// root to point dis = sqrt(x^2+y^2)
// we are taking dis square for easy cal
Car(int x,int y,int idx){
    this.x = x;
    this.y = y;
    this.distsq = (x*x)+(y*y);
    this.idx = idx;
}

@Override
public int compareTo(Car c2){
    return this.distsq - c2.distsq;
}}

//weakest soldier
class soldier implements Comparable<soldier> {
int soldierCount;int idx;
soldier(int soldierCount,int idx){
    this.soldierCount = soldierCount;
    this.idx = idx;
}

@Override
public int compareTo(soldier s2){
    // if count same compare wrt idx
    if (this.soldierCount == s2.soldierCount) {
        return this.idx - s2.idx;
    }
    else{// else on count
        return this.soldierCount - s2.soldierCount;
    }
}}

public class Heap {

// heapsort (O(nlogn))
// maxheap -> ascending order sort, minheap -> descending
    public static void maxheapify(int arr[],int rootidx,int size) {
        int leftidx = 2*rootidx+1;
        int rightidx = 2*rootidx+2;
        int maxidx = rootidx;
        
        // find maximum of the three
        if (leftidx < size && arr[leftidx]>arr[maxidx]) {
            maxidx = leftidx;
        }
        if (rightidx < size && arr[rightidx]>arr[maxidx]) {
            maxidx = rightidx;
        }
        // swap with the maximum
        if (maxidx != rootidx) {
            int temp = arr[maxidx];
            arr[maxidx] = arr[rootidx];
            arr[rootidx] = temp;

            // apply heapify at maxidx again
            maxheapify(arr,maxidx,size);
        }
    }
    public static void heapsort(int arr[]){
        // maxheap create
        for (int i = arr.length/2; i >= 0; i--) {
            maxheapify(arr,i,arr.length);
        }
        // largest push at end loop
        for (int i = arr.length-1; i > 0; i--) {
            // swap with first
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            //heapify root for new smaller array
            maxheapify(arr,0,i);
        }
    }    

// find k nearby cars  
    public static void nearby(int pts[][],int k) {
        PriorityQueue<Car> p = new PriorityQueue<>();
        for (int i = 0; i < pts.length; i++) {
            p.add(new Car(pts[i][0], pts[i][1], i));
        }
        for (int i = 0; i < k; i++) {
            System.out.print("C"+p.remove().idx+" ");
        }
    }  

// connect N ropes with minimum cost
    public static void Nropes(int ropes[]) {
        PriorityQueue<Integer> p = new PriorityQueue<>();
        for (int i = 0; i < ropes.length; i++) {
            p.add(ropes[i]);
        }
        int cost = 0;
        while (p.size() > 1) {
            int min1 = p.remove();
            int min2 = p.remove();
            cost += min1+min2;
            p.add(min1+min2);
        }
        System.out.println("Cost is:"+cost);
    }    

// weakest soldier
    public static void Wsol(int matrix[][],int k) {
        PriorityQueue<soldier> p1 = new PriorityQueue<>();
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {// add
            count = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                count += matrix[i][j] == 1? 1 : 0;
            }
            p1.add(new soldier(count, i));
        }
        for (int i = 0; i < k; i++) {// remove
            System.out.print("R"+p1.remove().idx+" ");
        }
    }    
    public static void main(String[] args) {
        // minheap h1 = new minheap();
        // h1.insert(1);
        // h1.insert(0);
        // h1.insert(5);
        // h1.insert(3);
        // h1.insert(2);
        // while (!h1.isEmpty()) {
        //     h1.peek();
        //     h1.delete();
        // }
        // int arr[] = {8,6,5,4,3,2,1};
        // heapsort(arr);
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.print(arr[i]+" ");
        // }
        // int pts[][] = {{3,3},{5,-1},{-2,4}};
        // nearby(pts, 2);
        int soldiers[][] = {
            {1,0,0,0},
            {1,1,1,1},
            {1,0,0,0},
            {1,0,0,0}
        };
        Wsol(soldiers, 2);

    }
}
