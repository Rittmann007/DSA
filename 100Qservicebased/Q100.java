import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
            // and mid can be 1st or last element also
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

//12. rotate array
    public void rev(int[] nums,int i,int j){
        while(i <= j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;j--;
        }
    }
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k%n;// for case k > nums.length
        rev(nums,0,nums.length-1);// rotate whole
        rev(nums,0,k-1);// rotate 1st k
        rev(nums,k,nums.length-1);// rotate rest
        
    }    

//13. merge intervals
     public int[][] merge(int[][] intervals) {
        if(intervals==null || intervals.length <= 1){
            return intervals;
        }
        // sort wrt starting point
        Arrays.sort(intervals,Comparator.comparingInt(o->o[0]));

        ArrayList<int[]> result = new ArrayList<>();
        int newinterval[] = intervals[0];
        result.add(newinterval);

        for(int interval[] : intervals){
            if(interval[0] <= newinterval[1]){
                newinterval[1] = Math.max(newinterval[1],interval[1]);// update interval
            }else {
                newinterval = interval;// move to next interval
                result.add(newinterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }    

//14. majority element
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

//15. product of array except self
     public int[] productExceptSelf(int[] nums) {
        int left[] = new int[nums.length];// left elms product
        int right[] = new int[nums.length];// right elms product

        left[0] = 1;// first elm has no left
        for(int i = 1;i<nums.length;i++){
            left[i] = left[i-1]*nums[i-1];
        }
        right[nums.length-1] = 1;// last elm has no right
        for(int i = nums.length-2;i>=0;i--){
            right[i] = right[i+1]*nums[i+1];
        }
        // ith elm = product of elms in right of ith*
        //           product of elms in left of ith
        for(int i = 0;i<nums.length;i++){
            nums[i] = right[i]*left[i];
        }
        return nums;
    }
    
//16. sort colors
     public void sortColors(int[] nums) {
        int low = 0,mid = 0,high = nums.length-1;
        while(mid<=high){
            if(nums[mid] == 0){// swap low and mid
               int temp = nums[mid];
               nums[mid] = nums[low];
               nums[low] = temp;
               low++;mid++;
            }else if(nums[mid] == 1){
                mid++;
            }else{// swap high and mid
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }    

//17. minimum size subarray sum
     public int minSubArrayLen(int target, int[] nums) {
        int minLenwindow = Integer.MAX_VALUE;
        int currsum = 0;
        int low = 0,high = 0; 

        while(high < nums.length){
            currsum += nums[high];// increase the window
            high++;

            while(currsum >= target){
                int currminlen = high-low;
                minLenwindow = Math.min(minLenwindow,currminlen);//storeTheCurrentMinimumWindow
                currsum -= nums[low];// decrease the window to get minimum
                low++;
            }
        }
        return minLenwindow==Integer.MAX_VALUE? 0: minLenwindow;
    }    

//18. maximum product subarray
    public int maxProduct(int[] nums) {
        int n = nums.length-1;
        int leftProduct = 1,rightProduct = 1;
        int ans = nums[0];

        for(int i=0;i<=n;i++){
            leftProduct = leftProduct==0? 1:leftProduct;// if 0,make it 1
            rightProduct = rightProduct==0? 1:rightProduct;

            leftProduct *= nums[i];// product of elms from left
            rightProduct *= nums[n-i];// product of elms from right

            ans = Math.max(ans,Math.max(leftProduct,rightProduct));
        }

        return ans;
    }    
    
    public static void main(String[] args) {
        // Your code goes here
        System.out.println("Hello, world!");
    }
}
