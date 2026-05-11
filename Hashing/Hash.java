import java.util.*;

import org.w3c.dom.Node;

// hashmap implementation
class hashmap<K,V> {// generics
class Node {
K key;V value;
    Node(K key,V value){
        this.key = key;
        this.value = value;
    }
}
private int n;//current nodecount
private int N;// size
private LinkedList<Node> bucket[];// our hashmap
@SuppressWarnings("unchecked")
hashmap(){
    this.n = 0;
    this.N = 4;
    this.bucket = new LinkedList[4];
    for (int i = 0; i < bucket.length; i++) {
        bucket[i] = new LinkedList<>();
    }
}

private int getBucketindex(K key){
    int hash = key.hashCode();// hashed value
    int di = Math.abs(hash)%N;// get pos val from 0 to N-1
    return di;
}

private int getDataindex(int bucketIndex,K key){
    LinkedList<Node> l1 = bucket[bucketIndex];
    for (int i = 0; i < l1.size(); i++) {
        if (key == l1.get(i).key) {
            return i;
        }
    }
    return -1;
}

@SuppressWarnings("unchecked")
private void rehash(){
    LinkedList<Node> old[] = bucket;
    bucket = new LinkedList[2*N];
    N = 2*N;
    for (int i = 0; i < bucket.length; i++) {
        bucket[i] = new LinkedList<>();
    }
    // copy nodes from old to new
    for (int i = 0; i < old.length; i++) {
        LinkedList<Node> l1 = old[i];
        for (int j = 0; j < l1.size(); j++) {
            Node n1 = l1.remove();
            put(n1.key, n1.value);
        }
    }
}

void put(K key,V value){
    int bucketIndex = getBucketindex(key);
    int dataIndex = getDataindex(bucketIndex,key);
    if (dataIndex != -1) {// then update
        Node n1 = bucket[bucketIndex].get(dataIndex);
        n1.value = value;
    }else {// then add new node
        LinkedList<Node> l1 = bucket[bucketIndex];
        l1.add(new Node(key, value));
        n++;
    }
    // if lambda reaches above predetermined threshold
    // that is linked lists are overflown with nodes
    double lambda = (double)n/N;
    if (lambda > 2.0) {
        rehash();
    }
}

boolean containsKey(K key){
    int bucketIndex = getBucketindex(key);
    int dataIndex = getDataindex(bucketIndex,key);
    if (dataIndex != -1) {
        return true;
    }else {
        return false;
    }
}

V remove(K key){
    int bucketIndex = getBucketindex(key);
    int dataIndex = getDataindex(bucketIndex,key);
    if (dataIndex != -1) {
        Node n1 = bucket[bucketIndex].remove(dataIndex);
        return n1.value;
    }else {
        return null;
    }
}

V get(K key){
    int bucketIndex = getBucketindex(key);
    int dataIndex = getDataindex(bucketIndex,key);
    if (dataIndex != -1) {
        Node n1 = bucket[bucketIndex].get(dataIndex);
        return n1.value;
    }else {
       return null;
    }
}

ArrayList<K> keyset(){
    ArrayList<K> arr = new ArrayList<>();
    for (int i = 0; i < bucket.length; i++) {
        LinkedList<Node> l1 = bucket[i];
        for (int j = 0; j < l1.size(); j++) {
            Node n1 = l1.get(j);
            arr.add(n1.key);
        }
    }
    return arr;
}}

public class Hash {

// majority element
    public static void Majority(int arr[]) {
        HashMap<Integer,Integer> h1 = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (h1.containsKey(arr[i])) {// increase frequency
                h1.put(arr[i], h1.get(arr[i])+1);
            }
            else{
                h1.put(arr[i], 1);
            }
        }
        for (int i : h1.keySet()) {// iterate over keys
            if (h1.get(i) > arr.length/3) {
                System.out.print(i+" ");
            }
        }
    }    

// valid anagram
    public static boolean anagram(String s,String t) {
        if (s.length()!=t.length()) {
            return false;
        }
        HashMap<Character,Integer> h1 = new HashMap<>();
        // fill the map
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // if key exits, will return val or default val
            h1.put(ch, h1.getOrDefault(ch, 0)+1);
        }
        // verify with the 2nd string and unfill
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (h1.containsKey(ch) == true) {
                if (h1.get(ch) == 1) {
                    h1.remove(ch);
                }
                else{
                    h1.put(ch, h1.get(ch)-1);
                }
            }else{
                return false;
            }
        }
        return h1.isEmpty();
    }    

// union and intersection    
    public static void union(int arr1[],int arr2[]) {
        HashSet<Integer> h1 = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            h1.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            h1.add(arr2[i]);
        }
        System.out.println(h1);
    }
    public static void intersection(int arr1[],int arr2[]) {
        HashSet<Integer> h2 = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            h2.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
        // find common elements
            if (h2.contains(arr2[i])) {
                h2.remove(arr2[i]);
                result.add(arr2[i]);
            }
        }
        System.out.println(result);
    }

// find itinerary for tickets
    public static void itinerary(HashMap<String,String> tickets) {
        HashMap<String,String> reverse = new HashMap<>();
        // to , from
        for (String str : tickets.keySet()) {
            reverse.put(tickets.get(str), str);
        }
        String start = "";
        // place which isn't in "to"
        for (String str : tickets.keySet()) {
            if (!reverse.containsKey(str)) {
                start = str;
            }
        }
        // print the route
        System.out.print(start);
        for (String str : tickets.keySet()) {
            System.out.print("->"+tickets.get(start));
            start = tickets.get(start);
        }
    }    

// largest subarray with sum 0
    public static void sum0(int arr[]) {
        HashMap<Integer,Integer> h1 = new HashMap<>();
        int sum = 0;
        int length = 0;
        for (int j = 0; j < arr.length; j++) {
            sum += arr[j];
            // if sum itself becomes 0
            // means 0-j, sum = 0
            if (sum == 0) {
                length = j+1;
            }
            // otherwise if sum exists
            if (h1.containsKey(sum)) {
                length =Math.max(length,j - h1.get(sum));// j-i
            }else {
                h1.put(sum, j);
            }
        }
        System.out.println("max subarray length "+length);
    }    

// subarray sum equal to k (return number of such subarrays)
    public static void sumk(int arr[],int k) {
        HashMap<Integer,Integer> h1 = new HashMap<>();
        h1.put(0, 1);// default(for single val edge cases)
        int sum = 0;
        int count = 0;
        for (int j = 0; j < arr.length; j++) {
            sum += arr[j];
            if (h1.containsKey(sum-k)) {
                count += h1.get(sum-k);
            }
            // if sum exists-increase freq , if not-put with 1 freq
            h1.put(sum, h1.getOrDefault(sum, 0)+1);
        }
        System.out.println("count of subarrays are "+count);
    }    
    public static void main(String[] args) {
    //    hashmap<String,Integer> h1 = new hashmap<>();
    //    h1.put("ritom", 21);
    //    h1.put("lola", 23);
    //    ArrayList<String> arr = h1.keyset();
    //    for (String str : arr) {
    //     System.out.println(str+","+h1.get(str));
    //    }
    //    System.out.println(h1.containsKey("ritom"));
    // HashMap<String,String> tickets = new HashMap<>();
    // tickets.put("chennai", "bengaluru");
    // tickets.put("mumbai", "delhi");
    // tickets.put("goa", "chennai");
    // tickets.put("delhi", "goa");
    // itinerary(tickets);
    int arr[] = {1,2,3};
    sumk(arr, 3);
    }
}
