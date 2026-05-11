public class Rec {
// factorial of n
    public static int factorial(int n) {
        if (n == 0) {  // base case
            return 1;
        }
        int fact = n * factorial(n-1);
        return fact;
    }
// sum of n natural nums
    public static int Nsum(int n) {
        if (n == 1) {  // base case
            return 1;
        }
        int sum = n + Nsum(n-1);
        return sum;
    }
// print nth fibonacci num (fibonacci seq - each num is sum of previous two)[O(2^n)]
    public static int fib(int n) {
        if (n == 1 | n == 0) {  // base case
            return n;
        }
        int fibo = fib(n-1) + fib(n-2);
        return fibo;
    }    
// check if array is sorted or not
    public static boolean isSorted(int arr[] , int i) {
        if (i == (arr.length - 1)) {  // base case
            return true;
        }
        if (arr[i] > arr[i+1]) {
            return false;
        }
        else{
            return isSorted(arr, i+1);
        }
    }    
// check the first occurence of a number in an array
    public static int Foccurance(int arr[] , int key , int i) {
        if (i == (arr.length - 1)) {  // base case
            return -1;
        }
        if (arr[i] == key) {
            return i;
        }
        else{
            return Foccurance(arr, key, i+1);
        }
    }    
// check the last occurence of a number in an array (this time, first we look forward and then compare with self)
    public static int Loccurance(int arr[] , int key , int i) {
        if (i == (arr.length)) {  // base case
            return -1;
        }
       int isfound = Loccurance(arr, key, i+1);  // first we go to the last
       if (isfound == -1 && arr[i]==key) {  // then we compare
        return i;
       }
       return isfound;
    }    
// calculate x^n (O(n))
    public static int Xn(int x, int n) {
      if (n == 0) {  // base case
        return 1;
      }
      return x * Xn(x, n-1);
    }    
// calculate x^n (optimized) (O(logn))
    public static int XnO(int x, int n) {
      if (n == 0) {  // base case
        return 1;
      }
     int hlf = XnO(x, n/2);
     int hlfSQ = hlf*hlf;
      if (n%2 != 0) {  // for odd n(multiply extra x)
        hlfSQ *= x;
      }

      return hlfSQ;
    }   
// tiling problem
    public static int tiling(int n) {
        if (n==1 || n==0) { // base case
            return 1;
        }
        //vertical
       int f1 = tiling(n-1);
        //horizontal
       int f2 = tiling(n-2);

        // total ways
        return (f1+f2);
    }   
// remove duplicates in a string
    public static void duplicate(String org,StringBuilder removed,int idx,boolean map[]) {
        if (idx == org.length()) {  // base case
           System.out.println(removed);
           return;
        }
        if (map[org.charAt(idx) - 'a'] == false ) {// if char has appeared first time then append
            map[org.charAt(idx) - 'a'] = true;
            duplicate(org,removed.append(org.charAt(idx)),idx+1,map);
        }
        else{
            duplicate(org,removed,idx+1,map);
        }
    }    
// friends pairing problem
    public static int pairing(int n) {
        if (n==1 || n==2) { // base cases
            return n;
        }
        //single
        int f1 = pairing(n-1);
        //pair
        int f2 = ((n-1)*pairing(n-2));

        int total = (f1+f2);
        return total;
    }    
// print all binary strings of size n, without consecutive ones
    public static void Bs(int n,int lastplace,String str) {
        if (n == 0) {
            System.out.println(str);  // base case
            return;
        }
        // if (lastplace == 0) {  // if lastplace is 0 then 1 or 0 can be put after that
        //     Bs(n-1, 0, str+'0');
        //     Bs(n-1, 1, str+'1');
        // }
        // else{
        //     Bs(n-1, 0, str+'0'); // only 0 can be put to avoid consecutive ones
        // }
        Bs(n-1, 0, str+'0');
        if (lastplace == 0) {
            Bs(n-1, 1, str+'1');
        }
    }
// find all occurances(indices) of a given key in an array    
    public static void alloccurance(int arr[] , int key , int i) {
        if (i == (arr.length)) {  // base case
            return ;
        }
        if (arr[i] == key) {
           System.out.println(i);
        }
        
            alloccurance(arr, key, i+1);
        
    }    
// provided a number , convert it into a string of english like (2019 -> two zero one nine)
// digits are in a range of 0-9 and last digit can't be zero
    public static void Es(int num , String str[]) {
        if (num == 0) { // base case
            return;
        }
      int lastdigit = num%10;
        Es(num/10, str);
        System.out.print(str[lastdigit]+" ");
    }
// tower of hanoi problem
    public static void ToH(int n,String source,String helper,String destination) {
        if (n == 1) {
            System.out.println("transfer "+n+" from "+source+" to "+destination);
            return;
        }
        ToH(n-1, source, destination, helper);
        System.out.println("transfer "+n+" from "+source+" to "+destination);
        ToH(n-1, helper, source, destination);
    }
    public static void main(String[] args) {
        // Your code goes here
        int arr[] = {1,2,3,2,8,4,2,2,5};
        String str[] = {"zero","one","two","three","four","five","six","seven","eight","nine","ten"};
   ToH(3, "S", "H", "D");
       
    }
}
