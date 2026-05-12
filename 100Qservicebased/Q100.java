import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Q100 {

// Arrays    
//1. kth largest element in an array    
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            if (pq.size() < k) {
                pq.add(nums[i]);
            } else {
                if (pq.peek() < nums[i]) {
                    pq.remove();
                    pq.add(nums[i]);
                }
            }
        }
        return pq.peek();

    }

//2. remove duplicates from sorted array
    public int removeDuplicates(int[] nums) {
        int x = 0;
        for(int i = 1;i<nums.length;i++){
            if(nums[x]!=nums[i]){
                x++;
                nums[x] = nums[i];
            }
        }
        return x+1;
    }
    
//3. remove duplicates from sorted array II
    public int removeDuplicates(int[] nums) {
3        int n = nums.length;
4        if(n<=2) return n;// check if 2nd idx exists
5        int x = 2;
6        for(int i = 2;i<n;i++){
7            if(nums[x-2]!=nums[i]){
8               nums[x] = nums[i];
10                x++;
11            }
12        }
13        return x;
14    }    

//4. Move zeros to end
    public void moveZeroes(int[] nums) {
        int x = 0;
        for(int i = 0;i<nums.length;i++){
           if(nums[i]!=0){
            int temp = nums[i];//swap i and x
            nums[i] = nums[x];
            nums[x] = temp;
            x++;
           }
        }
    }

//5. missing number
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int total = 0;
        for(int i:nums){
          total += i;
        }
        return ((n*(n+1))/2)-total;
    }
    
//6. single number
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i:nums){
            result = result ^ i;
        }
        return result;
    }    

//7. maxsum of the subarrays with kadane's algo(whenever in the way our final sum
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

//8. two sum
// leetcode version(return indices)
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            int a = nums[i];
            int moreNeeded = target - a;
            if(map.containsKey(moreNeeded)){
                return new int[]{map.get(moreNeeded),i};
            }
            map.put(a,i);
        }
        return new int[]{-1,-1};
    }
// normal version(exists or not)
    public static void PairSmTP(ArrayList<Integer> list,int target) {
        int Lp = 0;
        int Rp = list.size()-1;

        while (Lp < Rp) {
            //case 1
            if (list.get(Rp)+list.get(Lp) == target) {
                System.out.println("exists");
                return;
            }
            //case 2
            if (list.get(Rp)+list.get(Lp) < target) { //move to bigger values
                Lp++;
            }
            //case 3
            else{    //move to smaller values
                Rp--;
            }
        }

        System.out.println("not exists");
    }    
    
//9. Find first and last position of element in sorted array
    public int[] searchRange(int[] nums, int target) {// implemented in func for better view
        if (nums == null || nums.length == 0) {
            return new int[] { -1, -1 };
        }
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int low = 0, high = nums.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                ans = mid;
                if (isFirst) {// first occurance
                    high = mid - 1; // keep searching left
                } else {// last occurance
                    low = mid + 1;  // keep searching right
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
//10. search in rotated sorted array
    public static void RS(int arr[],int target) {
        int left = 0;
        int right = arr.length-1;
        while (left <= right) {
            int mid = left + ((right-left)/2) ;//optimized
             
            if (arr[mid] == target) {
                System.out.println(mid);
                return;
            }  // now we have to determine which halve is sorted(main motive is to make our search space smalled)
            else if (arr[left] <= arr[mid]) {// if left halve is sorted
                if (target >= arr[left] && target < arr[mid]) {//target is in left halve
                    right = mid-1;
                } else {// target is in right halve
                    left = mid+1;
                }
            } else {  // else right halve is sorted
                if (target <= arr[right] && target > arr[mid]) {// target is in right halve
                    left = mid+1;
                } else {// target is in left halve
                    right = mid-1;
                }
            }
        }
        System.out.println(-1);
    }    

//11. find peak element
    public int findPeakElement(int[] nums) {
        int low = 0,high = nums.length-1,mid = 0;
        while(low<=high){
            mid = low + (high-low)/2;
            // we have to check if mid-1 or mid+1 is going out of bounds
            if((mid==0 || nums[mid-1]<nums[mid]) && (mid==nums.length-1 || nums[mid+1]<nums[mid])){
                return mid;
            }
            else if(mid > 0 && nums[mid-1]>nums[mid]){// if previous elm is greater
                high = mid-1; //search in left
            }else {
                low = mid+1;// search in right
            }
        }
        return -1;
    }    
    
    public static void main(String[] args) {
        // Your code goes here
        System.out.println("Hello, world!");
    }
}
