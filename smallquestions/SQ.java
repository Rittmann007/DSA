import java.util.*;

public class SQ {
    // reverse string
    public static void sr(String str) {
        int i=0,j=str.length()-1;
        char arr[] = str.toCharArray();
        while(i<=j){
          char c = arr[i];
          arr[i] = arr[j];
          arr[j] = c; 
          i++;j--;
        }
        System.out.println(new String(arr));
    }

    // check if a string is palindrome
    public static boolean palindrome(String str) {
        for (int i = 0,j = str.length()-1; i<j; i++,j--) {
            if(str.charAt(i)!=str.charAt(j)){
                return false;
            }
        }
        return true;
    }

    // check if a number is palindrome
    public static boolean numP(int num) {
        int temp = num;
        int num2 = 0;
        while (temp>0) {// extract the last digit and store it in another var
            num2= num2*10 + (temp%10);
            temp/=10;
        }
        return num==num2? true:false;
    }

    // reverse an integer
    public static void numR(int num) {
        int num2 = 0;
        while (num>0) {
            num2= num2*10 + (num%10);
            num/=10;
        }
        System.out.println(num2);
    }

    // check if a number is prime
    public static boolean prime(int num) {
        if(num<=1)return false; //0 and 1 are not prime
        if(num==2 || num==3)return true; //2 and 3 are smallest prime

        if(num%2==0 || num%3==0)return false;// check their multiples also

        for (int i = 5; i <= Math.sqrt(num); i+=6) {// check 5 to √n skipping 2 and 3 multiples
            if(num%i==0 || num%(i+2)==0)
                return false;
        }
        return true;
    }

    // print prime nums in a range
    public static void PinR(int low,int high) {
        for (int i = low; i <= high; i++) {
            if (prime(i)) {
                System.out.print(i+" ");
            }
        }
    }

    // find factorial of a number
    public static int factorial(int num) {
        if(num==0){
            return 1;
        }
        return num*factorial(num-1);
    }

    // generate fibonacci series
    public static void fibs(int n) {
        int first=0,second=1;
        for (int i = 0; i < n; i++) {
            System.out.print(first+" ");
            int next = first+second;
            first = second;
            second = next;
        }
    }

    // find nth fibonacci number
    public static int fibo(int n) {
        if(n<=1)return n;// it will give fib0 as 0(0 based idx)
        // if(n==1)return 0; it will give fib1 as 0(1 based idx)
        // if(n==2)return 1;

        return fibo(n-1)+fibo(n-2);
    }

    // check if a number is armstrong(153 = 1^3+5^3+3^3)
    public static void Arm(int num) {// can be easily done by converting the num in string
        int temp = num;
        int i=0;
        while (temp>0) {// count digit
            temp/=10;
            i++;
        }
        temp = num;
        int result = 0;
        while(temp>0){// make number
            result += (int)Math.pow(temp%10, i);
            temp/=10;
        }
        //num==result?System.out.println("true"):System.out.println("false"); wrong!!,ternary expects vals not statements
        System.out.println(num == result ? "true" : "false");
    }

    // check if a number is perfect
    // A perfect number is a positive integer that is equal to the sum of its proper divisors (excluding itself)
    public static void perfect(int num) {
        int result = 1;// 1 is always a divisor
        for (int i = 2; i <= num/2; i++) {// any divisor other than the num itself is <=num/2
            if (num%i == 0) {
                result+=i;
            }
        }
        System.out.println(num==result?"true":"false");
    }

    // find GCD of two numbers
    // we can loop from 1 to smallest to find the greatest divisor
    // or Euclidian algo - gcd(a,b) = gcd(a-b,b) [where, a>b] (do untill any one becomes 0)
    // we can improve the formula further - gcd(a,b) = gcd(a%b,b) [where, a>b]
    public static int gcd(int num1,int num2) {
        while (num1>0 && num2>0) {
            if(num1>num2){
                num1 = num1%num2;
            }else{
                num2 = num2%num1;
            }
        }
        return num1==0?num2:num1;
    }

    // find LCM of two numbers
    // lcm(a,b) = (a*b)/gcd(a,b)
    public static void lcm(int num1,int num2) {
        System.out.println((num1*num2)/gcd(num1,num2));
        // extend by pairwise for more than two num lcm(a,b,c) = (lcm(a,b),c) 
    }

    // count digits in a number
    public static void Cdigit(int num) {
        int i=0;
        while (num>0) {
            num/=10;
            i++;
        }
        System.out.println(i);
    }

    // sum of digits of a number
    public static void Sdigit(int num) {
        int result = 0;
        while (num>0) {
            result+=(num%10);
            num/=10;
        }
        System.out.println(result);
    }

    // reverse words in a string
    public static void Rword(String str) {
        String arr[] = str.trim().split(" +");
        for (int i = 0,j = arr.length-1; i<j; i++,j--) {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append(" ");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb.toString());
    }

    // count vowels and consonants in a string
    public static void VandC(String str) {
        str = str.toLowerCase();
        int v=0,c=0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u') {
                v++;
            }else{
                c++;
            }
        }
        System.out.println("vowels:"+v+" consonants:"+c);
    }

    // count frequency of characters
    public static void freq(String str) {
        str = str.toLowerCase();
        int arr[] = new int[26];
        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i)-'a']++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>0) {
                System.out.println((char)(i+'a')+":"+arr[i]);
            }
        }
    }

    // find duplicate characters in a string
    public static void dupC(String str) {
        str = str.toLowerCase();
        int arr[] = new int[26];
        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i)-'a']++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>1) {
                System.out.print((char)(i+'a')+" ");
            }
        }
    }

    // check if array is sorted
    public static boolean Sarray(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]<arr[i-1]) {
                return false;
            }
        }
        return true;
    }

    // find largest element in an array
    public static void largest(int arr[]) {
        int max = Integer.MIN_VALUE;
        for(int i:arr){
            if (i>max) {
                max = i;
            }
        }
        System.out.println(max);
    }

    // find 2nd largest element in an array
    public static void Slargest(int arr[]) {
        int max = Integer.MIN_VALUE;
        int Smax = Integer.MIN_VALUE;// java vars must begin with a letter/_ /$
        for(int i:arr){  
            if (i>max) {
                Smax = max;
                max = i;
            }else if (i>Smax && i!=max) {// prevent storing duplicateof max in Smax
                Smax = i;
            }
        }
        System.out.println(Smax);
    }

    // check leap year
    // A year is a leap year if:
    // It is divisible by 400(century year), or It is divisible by 4 but not divisible by 100(non century year).
    public static void leap(int year) {
        if (year%400==0) {
            System.out.println("true");
        }else if (year%100!=0 && year%4==0) {
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }

    // find common elements between arrays
    public static void common(int arr1[],int arr2[]) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> common = new HashSet<>();

        for (int i : arr1) {
            set.add(i);
        }
        for (int i : arr2) {
            if (set.contains(i)) {
                common.add(i);
            }
        }

        System.out.println(common.isEmpty()?"false":common);
    }

    // check anagram

    // binary search
    public static void binary(int arr[],int target) {
        int low = 0 ,high = arr.length-1;
        while (low<high) {
            int mid = low+(high-low)/2;
            if (arr[mid] == target) {
                System.out.println("found at:"+mid+"index");
                return ;
            }else if (target > arr[mid]) {
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        System.out.println("not found");
    }

    // merge,quick ,2d array

    // merge sort
    public static void merge(int arr[],int low,int mid,int high) {
        int i=low,j=mid+1,k=low;
        int arr1[] = new int[arr.length];
        while (i<=mid && j<=high) {
            if (arr[i] > arr[j]) {
                arr1[k] = arr[j];
                j++;k++;
            }else{
                arr1[k] = arr[i];
                i++;k++;
            }
        }
        while (i<=mid) {
            arr1[k] = arr[i];
            i++;k++;
        }
        while (j<=high) {
            arr1[k] = arr[j];
            j++;k++;
        }
        for (int l = low; l <= high; l++) { //loop is from low to high(not traversing entire arr)
            arr[l] = arr1[l];
        }
    }
    public static void mergeSort(int arr[],int low,int high) {
        if (low<high) {
            int mid = (low+high)/2;
            mergeSort(arr,low,mid);
            mergeSort(arr,mid+1,high);
            merge(arr,low,mid,high);// merge happens in same arr(not in two separate)
        }
    }

    // quick sort
    // 1. pivot=low
    // 2. i = low+1
    // 3. j = high
    // 4. i++ untill elemnt > pivot is found
    // 5. j-- umtill elemnt <= pivot is found
    // 6. swap a[i] & a[j] and repeat 4 and 5 untill (j<=i)
    // 7. swap pivot and a[j]
    public static int pivot(int arr[],int low,int high) {
        int pivot = low;
        int i=low+1,j=high;
        
        while (i<j) {
            while (i<=high && arr[i]<=arr[pivot]) {// add safeguard for idxOutOfBound error
                i++;
            }
            while (j>=low && arr[j]>arr[pivot]) {
                j--;
            }
            if (i<j) {
                int temp = arr[j];// swap i,j
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }

        int temp = arr[j];// swap pivot,j
        arr[j] = arr[pivot];
        arr[pivot] = temp;

        return j;
        
    }
    public static void quickSort(int arr[],int low,int high) {
        if (low<high) {
            int pivotposition = pivot(arr,low,high);
            quickSort(arr, low, pivotposition-1);// sort the left side of pivot
            quickSort(arr, pivotposition+1, high);// sort right side of pivot
        }
    }

    // see other sortings also

    // also see patterns
    
    public static void main(String[] args) {
        // Your code goes here
        System.out.println("Hello, world!");
        int arr[]= {1,2,3,7,4,5,6};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
