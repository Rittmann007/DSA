import java.util.*;

public class Array {

// linear search

    public static void ls(int arr[], int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                System.out.println("found at " + i);
                return;
            }
        }
        System.out.println("not found");
    }

// find largest num

    public static void ln(int arr[]) {
        int largest = Integer.MIN_VALUE;// used to represent -infinity ,included in java.util , for + replace with
                                        // MAX_VALUE
        for (int i = 0; i < arr.length; i++) {
            if (largest < arr[i]) {
                largest = arr[i];
            }
        }
        System.out.println("largest is " + largest);
    }

// binary search

    public static void bs(int arr[], int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == key) {
                System.out.println("found at " + mid);
                return;
            } else if (arr[mid] > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println("not found");
    }

// reverse array

    public static void ra(int arr[]) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) { // if stsrt==end then it will point a single element only
            int temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
            start++;
            end--;
        }
    }

// pairs of elements of an array, for n elements total pairs n*(n-1)/2

    public static void pair(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                System.out.print("(" + curr + "," + arr[j] + ") ");
            }
            System.out.println();
        }
    }

// print all subarrays of an array(a continuous part of an array)

    public static void subarray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) { // here we are starting from i for printing single element also
                System.out.print("[");
                for (int j2 = i; j2 <= j; j2++) { // for printing start to end
                    System.out.print(arr[j2] + ",");
                }
                System.out.print("] ");
            }
            System.out.println();
        }
    }

// maxsum of the subarrays(brute force)

    public static void subarraysum(int arr[]) {
        int curr = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                curr = 0;
                for (int j2 = i; j2 <= j; j2++) {
                    curr += arr[j2];
                }
                if (max < curr) {
                    max = curr;
                }
            }

        }
        System.out.println("max sum is" + max);
    }

// maxsum of the subarrays with prefix array

    public static void subarraysum2(int arr[]) {
        int curr = 0;
        int max = Integer.MIN_VALUE;
        int prefix[] = new int[arr.length];
        prefix[0] = arr[0];
        for (int i = 1; i < prefix.length; i++) { // calculating the prefix array
            prefix[i] = prefix[i - 1] + arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                curr = i == 0 ? prefix[j] : (prefix[j] - prefix[i - 1]);

                if (max < curr) {
                    max = curr;
                }
            }

        }
        System.out.println("max sum is" + max);
    }

// maxsum of the subarrays with kadane's algo(whenever in the way our final sum
// is -ve, make the sum 0)(this will take special care for all -ve numbers)

    public static void subarraysum3(int arr[]) {
        int curr = 0;
        int max = Integer.MIN_VALUE;
        int mode = 0;
        for (int i = 0; i < arr.length; i++) {  // special care
            if (arr[i] > 0) {
                mode = 1;
                break;
            }
            if (max < arr[i]) {// choose the smallest -ve element
                max = arr[i];
            }
        }
        if (mode == 1) {    // if mode 1 run the normal code
            for (int i = 0; i < arr.length; i++) {
                curr += arr[i];
                if (curr < 0) {
                    curr = 0;
                }
                if (max < curr) {
                    max = curr;
                }
            }
        }
        System.out.println("max sum is" + max);

    }

//trapped rainwater(given no of bars of different height and width of 1 we have to calculate how much rainwater can be trapped in those unequal set of bars)
//the approach is we have to calculate water for each bar and then add all
//for a single bar trapped water is min(leftmaxboundary , rightmaxboundary) - heightOfTheBaritself
    public static void TrappedR(int height[]) {
        int h = height.length;
        //first to calculate leftmaxboundary for each bar as an array
        int leftmax[] = new int[h];
        leftmax[0] = height[0];
        for (int i = 1; i <= h-1; i++) {
            leftmax[i] = Math.max(height[i], leftmax[i-1]);
        }

        // to calculate rightmaxboundary for each bar as an array
        int rightmax[] = new int[h];
        rightmax[h-1] = height[h-1];
        for (int i = h-2; i >= 0; i--) {
            rightmax[i] = Math.max(height[i], rightmax[i+1]);
        }

        //now to calculate the rainwater according to the formula
        int rainwater = 0;
        for (int i = 0; i <= h-1; i++) {
            int max = Math.min(leftmax[i], rightmax[i]);
            rainwater += max - height[i];  //for each bar
        }
        System.out.println("water is: "+ rainwater);
    }

//You are given an array prices where prices[i] is the price of a given stock on the ith day.
// You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
// Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.    
    public static void stock(int Price[]) {
        int buyingPrice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < Price.length; i++) {
            if (buyingPrice < Price[i]) { //condition for profit
                int profit = Price[i] - buyingPrice;
                maxprofit = Math.max(maxprofit, profit); //calculating the max profit among all
            } else {
                buyingPrice = Price[i];  // if not then buy at that price
            }
        }
        System.out.println("max profit: " + maxprofit);
    }

//check for redundent elements in a array
    public static void redundent(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int el = arr[i];
            int mode = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j]==el) {
                    mode++;
                }
            }
            if (mode >= 2) {
                System.out.println("true");
                return;
            }
        }
        System.out.println("false");
    }    

// rotated array search problem in o(logn)    
    public static void RS(int arr[],int target) {
        int left = 0;
        int right = arr.length-1;
        while (left <= right) {
            int mid = (left + right)/2 ;
             
            if (arr[mid] == target) {
                System.out.println(mid);
                return;
            }  // now we have to determine which halve is sorted(main motive is to make our search space smalled)
            else if (arr[left] <= arr[mid]) {// if left halve is sorted
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else {  // else right halve is sorted
                if (target <= arr[right] && target > arr[mid]) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            }
        }
        System.out.println(-1);
    }
// given an array of size n, return the majority element
// majority element is the element that appears more than floor(n/2) times
// there is a brute force approach,(O(n^2))   
// but we will solve this using moore's voting algo(O(n))
// which says if we take a var , and increment it whenever majority elemnt comes and decrement it whenever other elemnt comes
// then the var will still be positive 
    public static void majority(int arr[]) {
        int freq = 0, ans = 0;

        for (int i = 0; i < arr.length; i++) {
            if (freq == 0) { // initilizing our ans var
                ans = arr[i];
            }
            if (ans == arr[i]) { // if same element appears
                freq++;
            } else {     // if different
                freq--;
            }
        }
        System.out.println(ans);
    }
// inversion count of an array 
// means counting inversion pairs (a[i] , a[j])
// if i < j and a[i] > a[j]
// solve by tweaking the mergesort code
    public static int merge(int a[],int low,int mid,int high) {
        int i = low,j = mid+1,k = low,inversioncount = 0;
        int b[] = new int[high+1];
        while (i<=mid && j<=high) {
            if (a[i] <= a[j]) { 
                b[k] = a[i];
                i++; k++;
            }
            else{     // in this part a[i] > a[j]
                b[k] = a[j];
                j++; k++;
                inversioncount += (mid+i-1); //<---tweaked part
            }
        }
        while (i<=mid) {
            b[k] = a[i];
            i++; k++;
        }
        while (j <= high) {
            b[k]=a[j];
            j++; k++;
        }
        for (int l = low; l <= high; l++) {
            a[l] = b[l];
        }
    return inversioncount;
    }

    public static int mergeSort(int a[],int low,int high) {
        int mid;
        if (low < high) {
            mid = (low+high)/2;
           int rightcount =  mergeSort(a, low, mid);
           int leftcount = mergeSort(a, mid+1, high);
           int mergecount = merge(a,low,mid,high);

           return (rightcount+leftcount+mergecount);
        }
      return 0;
    }    
    public static void main(String[] args) {

    // array : list of elements of the same type placed in a contiguous memory
    // location

        int arr[] = {-2,-3,-4,-5,-6,-7};
        subarraysum3(arr);
    //   System.out.println(mergeSort(arr, 0, arr.length-1)); 
        // for (int i = 0; i < arr.length; i++) {
        // System.out.print(arr[i]+" ");
        // }
    }
}
