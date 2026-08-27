import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import org.w3c.dom.Node;

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
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if(n<=2) return n;// check if 2nd idx exists
        int x = 2;
        for(int i = 2;i<n;i++){
            if(nums[x-2]!=nums[i]){
               nums[x] = nums[i];
                x++;
            }
        }
        return x;
    }    

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
// Patched Reset-to-0 Kadane
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
// Start a new subarray at the current element (i), or
// Extend the previous subarray (curr + i).
    public int maxSubArray(int[] nums) {// real kadane
        int result = Integer.MIN_VALUE;
        int curr = 0;
        for(int i:nums){
            curr = Math.max(i,curr+i);//curr is greater or curr elm
            result = Math.max(result,curr);
        }
        return result;
    }
// Maximum Sum Circular Subarray(similar but in a circular array)
// if circularly we get maxsum then it is (totalsumOfArray - minSumOfSubArray)
    public int maxSubarraySumCircular(int[] nums) {
        int maxsum = Integer.MIN_VALUE;
        int minsum = Integer.MAX_VALUE;
        int maxcurr = 0;
        int mincurr = 0;
        int total = 0;
        for (int i : nums) {//calculate min and max sum
            maxcurr = Math.max(i, maxcurr + i);
            maxsum = Math.max(maxsum, maxcurr);

            mincurr = Math.min(i, mincurr + i);
            minsum = Math.min(minsum, mincurr);

            total += i;
        }// see which is more normal maxsum or circular maxsum
        return maxsum < 0 ? maxsum /**(all -ve edge case handle)**/ : Math.max(maxsum, (total - minsum));
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
        int ans = nums[0];//for single element return that elm(given in ques)

        for(int i=0;i<=n;i++){
            leftProduct = leftProduct==0? 1:leftProduct;// if 0,make it 1
            rightProduct = rightProduct==0? 1:rightProduct;

            leftProduct *= nums[i];// product of elms from left
            rightProduct *= nums[n-i];// product of elms from right

            ans = Math.max(ans,Math.max(leftProduct,rightProduct));
        }

        return ans;
    }    

//19. trapped rainwater(given no of bars of different height and width of 1 we have to calculate how much rainwater can be trapped in those unequal set of bars)
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

//Strings
//20. reverse words in a string
    public String reverseWords(String s) {
        //trim removes leading and trailing spaces
        // \\s+ is a regex which tells split by consecutive spaces
        String arr[] = s.trim().split(" +");//store in array
        int i = 0,j = arr.length-1;
        while(i<=j){//reverse array
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;j--;
        }
        StringBuilder sc = new StringBuilder();
        for(String str:arr){// append the words in a stringbuilder
            sc.append(str);
            sc.append(" ");
        }
        sc.setLength(sc.length()-1);// remove the last space
        return sc.toString();
    }    

//21. longest common prefix
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(strs);
        char firstword[] = strs[0].toCharArray();
        char lastword[] = strs[strs.length-1].toCharArray();

        for(int i=0;i<firstword.length;i++){//compare 1st and last word
           if(firstword[i]!=lastword[i]){
            break;
           }
           sb.append(firstword[i]);
        }
        return sb.toString();
    }    

//22. valid anagram
    public static void anagram(String str1 , String str2) {
        str1.toLowerCase(); str2.toLowerCase(); // convert to lowercase to avoid overhead
        if (str1.length() == str2.length()) {  // if two are of equal length
            char[] one = str1.toCharArray();
            char[] two = str2.toCharArray();
            Arrays.sort(one);
            Arrays.sort(two);              // sort both alphabetically
            if (Arrays.equals(one, two)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        } else {
            System.out.println("false");
        }
    }    

//23. valid palindrome
    public boolean isPalindrome(String s) {
        int left = 0,right = s.length()-1;
        while(left < right){
            while(left<right && !Character.isLetterOrDigit(s.charAt(left))){
              left++;
            }
            while(left<right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if(Character.toLowerCase(s.charAt(left))!=Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;right--;
        }
        return true;
    }

//24. string compression(different that what ive done)
     public int compress(char[] chars) { 
        int write = 0, read = 0;
        while (read < chars.length) {
            char current = chars[read];
            int count = 0;

            while (read < chars.length && chars[read] == current) {//read and count untill same
                count++;
                read++;
            }
            chars[write++] = current;// write the current
            if (count > 1) {// then the number(double digit may appear)
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[write++] = c;
                }
            }
        }
        return write;// return the updated length of our array
    }

//25. First unique character in a string
     public int firstUniqChar(String s) {
        int freq[] = new int[26];
        for(int i = 0;i<s.length();i++){
           freq[s.charAt(i)-'a']++;
        }
        for(int i=0;i<s.length();i++){
            if(freq[s.charAt(i)-'a']==1){
                return i;
            }
        }
        return -1;
    }

//26. group anagrams
     public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        // sorted string is key and their anagram's list is value
        HashMap<String, ArrayList<String>> hs = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);

            if (hs.containsKey(key)) {// add the anagram
                hs.get(key).add(str);
            } else {// if not create the list and add the anagram
                ArrayList<String> ls = new ArrayList<>();
                ls.add(str);
                hs.put(key, ls);
            }
        }
        return new ArrayList<>(hs.values());
    }

//27. remove all adjacent duplicates in string
    public String removeDuplicates(String s) {
        Stack<Character> sc = new Stack<>();
        for(char c:s.toCharArray()){// for traversing the chars without using charAt() func
            if(!sc.isEmpty() && sc.peek()==c){// if stack top is same
                sc.pop(); 
            }else{
                sc.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char ch:sc){// for traversing the stack bottom to top
            sb.append(ch);
        }
        return sb.toString();// in this way we dont have to use .reverse()
    }    

//28. find the index of the first occurence in a string
    public int strStr(String haystack, String needle) {
        // because we are finding the substring we need to consider the length of needle
        for (int i = 0; i <= (haystack.length() - needle.length()); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {// if first char of needle matched
                if (haystack.substring(i, needle.length() + i).equals(needle)) {// check the substring from there
                    return i;
                }
            }
        }
        return -1;
    }    

//29. longest substring without repeating characters
    public int lengthOfLongestSubstring(String s) {
        int left=0,right=0,len=0;
        HashSet<Character> set= new HashSet<>();// const lookup time

        while(right<s.length()){
            char ch=s.charAt(right);
            if(!set.contains(ch)){//add in set if all chars are unique
                set.add(ch);
                len=Math.max(len, right-left+1);//update with new len
                right++;
            } else {// if a duplicate appears remove and left++ untill duplicate is removed from set
                set.remove(s.charAt(left));
                left++; 
            }
        }
        return len;
    }
    
//30. Maximum avarage subarray I
    public double findMaxAverage(int[] nums, int k) {
        int n=nums.length;
        int sum=0;// temporary sum
        for(int i=0;i<k;i++){// fill up the window first
            
            sum+=nums[i];
        }
        int maxSum=sum;
        int startIndex=0;// set up our pointers
        int endIndex=k;
        while(endIndex<n){// utill entire array is traversed,move the window forward one step at a time
            sum-=nums[startIndex];//delete from start()
            startIndex++;
            sum+=nums[endIndex];//add from end
            endIndex++;
            maxSum=Math.max(maxSum,sum);
        }
        return (double) maxSum/k;
    }    

//31. find all anagrams in a string
     public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> result = new ArrayList<>();
        int sCount[] = new int[26];//freqcount of letters of current window
        int pCount[] = new int[26];//freqcount of letters
        for (char c : p.toCharArray()) {// fill up pCount
            pCount[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {// start the operation
            sCount[s.charAt(i) - 'a']++;//add in the window
            if (i >= p.length()) {
                sCount[s.charAt(i - p.length()) - 'a']--;//delete from the window
            }
            if (Arrays.equals(sCount, pCount)) {
                result.add(i - p.length()+1);
            }
        }
        return result;
    }    

//32. sliding window maximum
    public static void window(int arr[] , int k) {
        Deque<Integer> d = new LinkedList<>(); // holds indices not values
        for (int i = 0; i < arr.length; i++) {
            if (!d.isEmpty() && d.getFirst() <= i-k) { 
                d.removeFirst(); // if some idx is not part of the current window
            }
            while (!d.isEmpty() && arr[i] >= arr[d.getLast()]) {
                d.removeLast();  // if the val of curr idx is greater than that of last of deque
            }
            d.addLast(i); // also add the current idx
            if (i >= k-1) { // starting from first window 
                System.out.print(arr[d.getFirst()]+",");
            }
        }
    }

//33. minimum window substring
    public static boolean contains(int freqWinS[], int freqWinT[]) {// utility func
        for (int i = 0; i < freqWinS.length; i++) {
            if (freqWinT[i] > freqWinS[i]) {
                return false;
            }
        }
        return true;
    }

    public String minWindow(String s, String t) {
        int freqWinS[] = new int[52];//total 52 letters, lower+uppercase
        int freqWinT[] = new int[52];
        int left = 0, right = 0, minLength = Integer.MAX_VALUE, minStart = 0;

        for (char ch : t.toCharArray()) {// fill up the t freqArray
            if (Character.isLowerCase(ch)) {
                freqWinT[ch - 'a']++;
            } else {
                freqWinT[ch - 'A' + 26]++; // shift uppercase into 26–51 range
            }
        // if we want to make our sol even faster we have to trade space,
        // instead of calculating idx of every char,we can pass the char itself in the array
        // as an idx,in that case it'll use the unicode value of that char as the idx and will waste
        // huge amt of array space,because in that case we needto make the size of each array 128    
        }

        while (right < s.length()) {// twoptr approach
            char c = s.charAt(right);// increase the freq of the char in s freqarray
            if (Character.isLowerCase(c)) {
                freqWinS[c - 'a']++;
            } else {
                freqWinS[c - 'A' + 26]++;
            }

            while (contains(freqWinS, freqWinT)) {// if s freqarr contains t freqarr in it
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;// update minlength
                    minStart = left;// update min window start idx
                }
                char ch = s.charAt(left);// decrease the freq of char from start
                if (Character.isLowerCase(ch)) {
                    freqWinS[ch - 'a']--;
                } else {
                    freqWinS[ch - 'A' + 26]--;
                }
                left++;
            }

            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);//return substring
    }    

//34. fruits into baskets  or
//    find the length of the longest contiguous subarray with atmost 2 distinct ints
    public int totalFruit(int[] fruits) {
        HashMap<Integer, Integer> h1 = new HashMap<>();
        int left = 0, right = 0, maxCount = Integer.MIN_VALUE;

        while (right < fruits.length) {
            int curr = fruits[right];// start adding from right ptr
            h1.put(curr, h1.getOrDefault(curr, 0) + 1);//increase count or set initial

            while (h1.size() > 2) {// if more than 2 distinct elements
                int leftcurr = fruits[left];// start remove from left ptr
                h1.put(leftcurr, h1.get(leftcurr) - 1);//decrease count
                if (h1.get(leftcurr) == 0) {// if count 0 remove from map
                    h1.remove(leftcurr);
                }
                left++;
            }

            maxCount = Math.max(maxCount, right - left + 1);
            right++;
        }
        return maxCount == Integer.MIN_VALUE ? 0 : maxCount;
    }

//35. container with most water
    public static void CmWTP(ArrayList<Integer> list) {
        int max = 0;
        int Lp = 0;
        int Rp = list.size()-1;
        
        while (Lp < Rp) {
            int height = Math.min(list.get(Lp), list.get(Rp));//min of two boundaries
            int width = Rp-Lp; // formula for calculating width
            max = Math.max(max, (height*width));

            if (list.get(Rp) < list.get(Lp)) {//lower boundary controls area
                Rp--;
            }
            else{ // so lower height pointer will update always
                Lp++;
            }
            
        }
        
        System.out.println(max);
    }        

//36. 3 sum
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3)
            return new ArrayList<>();

        int left = 0, right = 0;
        HashSet<List<Integer>> h1 = new HashSet<>();
        Arrays.sort(nums);// sort the given array first

        for (int i = 0; i < nums.length - 2; i++) {
            left = i + 1;// fix the current elemnt and look in the array from i+1
            right = nums.length - 1;

            while (left < right) {// run the same two sum approach
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    h1.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new ArrayList<>(h1);
    }    

//Linked list
//37. Reverse linked list
    public ListNode reverseList(ListNode head) {
     ListNode prev = null;
     ListNode curr = head;
     ListNode next;
     while(curr != null){
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
     }   
     return head = prev;
    }    

//38. Linked List cycle
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
    
//39. Linked List Cycle II(detect and return the intersection point)
     public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean flag = false;
        while(fast != null && fast.next != null){
          slow = slow.next;
          fast = fast.next.next;
          if(slow == fast){
            flag = true;
            break;
          }
        }
        if(flag == false){
            return null;
        }else{
            slow = head;
            while(slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return fast;
        }
    }
    
//40. Middle of the Linked List
     public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
//41. Intersection of two linked lists
     public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while(a != b){
            if(a == null){
                a = headB;
            }
            else if(b == null){
                b = headA;
            }else{
                a = a.next;
                b = b.next;
            }
        }
        if(a == null){ // if no intersection exists
            return null;
        }else{
            return a;
        }
    }

//42. merge two sorted lists
     public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode temp = new ListNode(-1);
        ListNode temp1 = temp;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp1.next = list1;
                list1 = list1.next;
                temp1 = temp1.next;
            } else {
                temp1.next = list2;
                list2 = list2.next;
                temp1 = temp1.next;
            }
        }
        while (list1 != null) {
            temp1.next = list1;
            list1 = list1.next;
            temp1 = temp1.next;
        }
        while (list2 != null) {
            temp1.next = list2;
            list2 = list2.next;
            temp1 = temp1.next;
        }
        return temp.next;
    }    

//43. remove nth node from end of list(cannot use size of linked list)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);// for handling edgecases
        dummy.next = head;

        ListNode ptr1 = dummy;// take two ptrs
        ListNode ptr2 = dummy;
        for(int i = 0 ; i<n ; i++){// move 2nd n steps ahead
            ptr2 = ptr2.next;
        }
        while(ptr2 != null && ptr2.next != null){// move both untill ptr2.next = null
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        ptr1.next = ptr1.next.next;
        return dummy.next;
    }    

//44. Palindrome linked list
    public ListNode middle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode middle = middle(head);// find middle

        ListNode prev = null;// reverse 2nd half
        ListNode curr = middle;
        ListNode next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        ListNode left = head;
        ListNode right = prev;

        while (right != null) {// then compare
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }    

//45. Sort List
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

//46. Add two numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = carry;// add the last carry

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            // extract two digits
            carry = sum / 10;// quotient or 0(if the num is<divisor)
            sum = sum % 10;// remainder or that num(if the num is<divisor)
            temp.next = new ListNode(sum);// add to result
            temp = temp.next;
        }
        if (carry == 1) {// carry can only be 0 or 1
            temp.next = new ListNode(carry);
        }
        return result.next;
    }

//47. flatten a multilevel doubly linked list
    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }
        Node curr = head;
        while (curr != null) {
            if (curr.child != null) {// if a node has a child
            //flatten the child node by attaching them linearly to curr node
                Node next = curr.next;// store curr.next for future
                curr.next = flatten(curr.child);// recursion
                curr.next.prev = curr;
                curr.child = null;// now make its child null after flattening

                while (curr.next != null) {// find the tail
                    curr = curr.next;
                }
                if (next != null) {// then attach the tail to next
                    curr.next = next;
                    next.prev = curr;
                }
            }

            curr = curr.next;
        }
        return head;
    }    

//48. copy list with random pointer
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node curr = head;
        while (curr != null) {// add duplicates in between the originals
            Node New = new Node(curr.val);
            New.next = curr.next;
            curr.next = New;
            curr = New.next;
        }
        curr = head;
        while (curr != null) {// copy the random ptr seq between duplicates
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        curr = head;
        Node newhead = head.next;
        Node newcurr = newhead;
        while (curr != null) {// break the duplicate from original
            curr.next = newcurr.next;
            curr = curr.next;
            if (curr != null) {
                newcurr.next = curr.next;
                newcurr = newcurr.next;
            }
        }
        return newhead;// return the duplicate head
    }  

//49. reverse nodes in k group
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        for (int i = 0; i < k; i++) {// check if k nodes exists
            if (temp == null) {
                return head;
            }
            temp = temp.next;
        }
        // recursively reverse the rest of the LL, and return the new head
        ListNode remainingHead = reverseKGroup(temp, k);

        temp = head;// now attach the the curr k group in reverse to the new head
        for (int i = 0; i < k; i++) {
            ListNode next = temp.next;
            temp.next = remainingHead;
            remainingHead = temp;
            temp = next;
        }
        return remainingHead;// this becomes the new head
    }    

//50. delete node in a linked list
    public void deleteNode(ListNode node) {
        ListNode prev = null;
        while (node != null && node.next != null) {
            node.val = node.next.val;
            prev = node;
            node = node.next;
        }
        prev.next = node.next;// delete the last duplicate node
    }    

//51. odd even linked list
    public ListNode oddEvenList(ListNode head) {
        ListNode oddHead = new ListNode(-1);
        ListNode evenHead = new ListNode(-1);
        ListNode odd = oddHead;
        ListNode even = evenHead;
        ListNode temp = head;
        int i = 1;
        while(temp != null){
            if(i%2 == 0){// for even
                even.next = temp;
                even = temp;
            }else{// for odd
                odd.next = temp;
                odd = temp;
            }
            temp = temp.next;
            i++;
        }
        even.next = null;
        odd.next = evenHead.next;// attach even next to odd
        return oddHead.next;
    }    

//52. merge k sorted lists
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode tempNode = new ListNode(-1);
        ListNode temp = tempNode;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next;
            }
        }
        while (head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }
        while (head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        return tempNode.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int last = lists.length - 1;
        while (last != 0) {
            int i = 0, j = last;// divide & conqure merge with 2 pointers
            while (i < j) {
                lists[i] = merge(lists[i], lists[j]);
                i++;
                j--;
                if (i >= j)
                    last = j;
            }

        }
        return lists[0];

    }

//stack & queue
//53. valid parenthesis
    public boolean isPair(char a , char b){
        if(a== '(' && b== ')'){
            return true;
        }else if(a=='{' && b== '}'){
            return true;
        }else if(a== '[' && b== ']'){
            return true;
        }else{
            return false;
        }
    }
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0 ; i<s.length() ; i++){
            char ch = s.charAt(i);
            if(ch=='(' || ch=='{' || ch=='['){// if opening push
                st.push(ch);
            }else{
            // if closing appears first or its not making pair    
                if(st.isEmpty() || !isPair(st.peek(),ch)){
                    return false;
                }else{
                    st.pop();
                }
            }
        }
        if(st.isEmpty()){
            return true;
        }else{
            return false;
        }
    }    

//54. Implement queue using stacks
    class StackQueue{
    java.util.Stack<Integer> s1 = new java.util.Stack<>();
    java.util.Stack<Integer> s2 = new java.util.Stack<>();

    boolean isEmpty(){
        return s1.isEmpty();
    }

    void enqueue(int data){ // just enqueue takes O(n)
        if (isEmpty()) {
            s1.push(data);
        }
        else{
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(data);

            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
    }

    int dequeue(){
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }
        return s1.pop();
    }

    int peek(){
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }
        return s1.peek();
    }
}

//55. next greater element I
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int result[] = new int[nums1.length];
        HashMap<Integer,Integer> h1 = new HashMap<>();// to store num->next greater mapping
        Stack<Integer> s1 = new Stack<>();

        for(int i=nums2.length-1 ; i>=0 ; i--){
            int num = nums2[i];
            while(!s1.isEmpty() && num>=s1.peek()){
                s1.pop();
            }
            if(s1.isEmpty()){
                h1.put(num,-1);
            }else{
                h1.put(num,s1.peek());
            }
            s1.push(num);
        }
        for(int i = 0 ; i<nums1.length ; i++){// just fill the array according to mapping
            result[i] = h1.get(nums1[i]);
        }
        return result;
    }

//56. Next greater element II
    public int[] nextGreaterElements(int[] nums) {
        int result[] = new int[nums.length];
        int N = nums.length; 
        Stack<Integer> s1 = new Stack<>();
        for(int i=(2*N)-1 ; i>=0 ; i--){
            while(!s1.isEmpty() && nums[i%N]>=s1.peek()){
                s1.pop();
            }
            if(i<N){// calculate result for original elements
                if(s1.isEmpty()){
                    result[i] = -1;
                }else{
                    result[i] = s1.peek();
                }
            }
            s1.push(nums[i%N]);
        }
        return result;
    }    

//57. Largest rectangle in histogram
    public int[] NextSmallerRight(int[] heights) {
        int result[] = new int[heights.length];
        Stack<Integer> s1 = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!s1.isEmpty() && heights[i] <= heights[s1.peek()]) {
                s1.pop();
            }
            if (s1.isEmpty()) {
                result[i] = heights.length;
            } else {
                result[i] = s1.peek();
            }
            s1.push(i);
        }
        return result;
    }

    public int[] NextSmallerLeft(int[] heights) {
        int result[] = new int[heights.length];
        Stack<Integer> s1 = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!s1.isEmpty() && heights[i] <= heights[s1.peek()]) {
                s1.pop();
            }
            if (s1.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = s1.peek();
            }
            s1.push(i);
        }
        return result;
    }

    public int largestRectangleArea(int[] heights) {
        int right[] = NextSmallerRight(heights);// smallest in right
        int left[] = NextSmallerLeft(heights);// smallest in left
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            int width = right[i] - left[i] - 1;
            result = Math.max(result, (height * width));
        }
        return result;
    }    

//58. design circular queue
    class ArrayCircularQueue {
    int size;
    int front = -1;
    int rear = -1;
    int arr[];

ArrayCircularQueue(int n){
    size = n;
    arr = new int[size];
}    

boolean isEmpty(){
    return (front == -1 && rear ==-1);
}

boolean isFull(){
    return (rear+1)%size == front;
}

void enqueue(int val){
    if (isFull()) {
        System.out.println("queue is full");
    }
    if (front == -1) { // for 1st element push
        front = 0;
    }
    rear = (rear+1)%size; // circular increment
    arr[rear] = val;
}

int dequeue(){
    if (isEmpty()) {
        System.out.println("queue is empty");
    }
    int val = arr[front];
    if (front == rear) {// for last element remove
        front = rear = -1;
    } else {
        front = (front+1)%size;
    }
    return val;

}

int peek(){
    if (isEmpty()) {
        System.out.println("queue is empty");
    }
    int val = arr[front];
    return val;
}
}

//59. Simplify Path
    public String simplifyPath(String path) {
        String arr[] = path.split("/");
        Stack<String> s1 = new Stack<>();
        for (String str : arr) {
            if (!str.equals(".") && !str.equals("..") && !str.equals("")) {
                s1.push(str);
            } else if (!s1.isEmpty() && str.equals("..")) {

                s1.pop();
            }
        }
        if (s1.isEmpty()) {
            return "/";
        } else {
            StringBuilder sp = new StringBuilder();
            for (String str : s1) {
                sp.append("/");
                sp.append(str);
            }
            return sp.toString();
        }
    }

//60. Implement stack using queues
    class MyStack {
    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        if (!q1.isEmpty()) {
            q1.add(x);
        } else {
            q2.add(x);
        }
    }

    public int pop() {
        if (empty()) {
            return -1;
        } else if (!q1.isEmpty()) {
            int x = 0;
            while (!q1.isEmpty()) {
                int top = q1.remove();
                if (q1.isEmpty()) {
                    x = top;
                    break;
                }
                q2.add(top);
            }
            return x;
        } else {
            int x = 0;
            while (!q2.isEmpty()) {
                int top = q2.remove();
                if (q2.isEmpty()) {
                    x = top;
                    break;
                }
                q1.add(top);
            }
            return x;
        }
    }

    public int top() {
        if (empty()) {
            return -1;
        } else if (!q1.isEmpty()) {
            int top = 0;
            while (!q1.isEmpty()) {
                top = q1.remove();
                q2.add(top);
            }
            return top;
        } else {
            int top = 0;
            while (!q2.isEmpty()) {
                top = q2.remove();
                q1.add(top);
            }
            return top;
        }
    }

    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}    

//61. min stack
    class MinStack {
    Stack<Integer> s1;
    Stack<Integer> min;

    public MinStack() {
        s1 = new Stack<>();//keeps track of all
        min = new Stack<>();//keeps track of min vals
    }

    public void push(int value) {
        s1.push(value);
        if (min.isEmpty() || value <= min.peek()) {
            min.push(value);
        }
    }

    public void pop() {
        if (!s1.isEmpty()) {
            int value = s1.pop();
            if (!min.isEmpty() && value == min.peek()) {
                min.pop();
            }
        }

    }

    public int top() {
        return s1.isEmpty() ? -1 : s1.peek();
    }

    public int getMin() {
        return min.isEmpty() ? -1 : min.peek();
    }
}

//62. daily temperatures
    public int[] dailyTemperatures(int[] temperatures) {
        int result[] = new int[temperatures.length];
        Stack<Integer> s = new Stack<>();// stores indices
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && temperatures[i] >= temperatures[s.peek()]) {
                s.pop();
            }
            if (s.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = s.peek() - i;
            }
            s.push(i);
        }
        return result;
    }

//63. Evaluate reverse polish notation(postfix notation)
    public int evalRPN(String[] tokens) {
        Stack<Integer> sk = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                int a = sk.pop();
                int b = sk.pop();
                sk.push(b + a);
            } else if (tokens[i].equals("-")) {
                int a = sk.pop();
                int b = sk.pop();
                sk.push(b - a);
            } else if (tokens[i].equals("*")) {
                int a = sk.pop();
                int b = sk.pop();
                sk.push(b * a);
            } else if (tokens[i].equals("/")) {
                int a = sk.pop();
                int b = sk.pop();
                sk.push(b / a);
            } else {// if number, parse and push
                sk.push(Integer.parseInt(tokens[i]));
            }
        }
        return sk.peek();
    }    

//64. LRU cache
    class LRUCache {
    public class Node {
        int key, val;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    Node head, tail;// using doubly Linked List and HashMap
    int capacity;
    HashMap<Integer, Node> h1 = new HashMap<>();

    public LRUCache(int capacity) {
        this.head = new Node(-1, -1);// most recent elements from head
        this.tail = new Node(-1, -1);// least recent elements from tail
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!h1.containsKey(key)) {
            return -1;
        } else {// get the value and remove and add to first
            Node n1 = h1.get(key);
            remove(n1);
            addFirst(n1);// as this is now most recent
            return n1.val;
        }
    }

    public void put(int key, int value) {
        if (h1.containsKey(key)) {// just update value wrt key
            Node n1 = h1.get(key);
            n1.val = value;
            remove(n1);
            addFirst(n1);

        } else {
            if (h1.size() == capacity) {// if full
                Node n = tail.prev;
                remove(n);// remove least recent from tail
                h1.remove(n.key);
            }
            Node n1 = new Node(key, value);
            h1.put(key, n1);// add new node after head
            addFirst(n1);
        }
    }

    private void remove(Node n1) {// utility funcs
        n1.prev.next = n1.next;
        n1.next.prev = n1.prev;
    }

    private void addFirst(Node n1) {
        Node next = head.next;
        head.next = n1;
        n1.prev = head;
        n1.next = next;
        next.prev = n1;
    }
}    

//65. Minimum Add to Make Parentheses Valid
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                st.push(ch);
            } else {
                if (!st.isEmpty() && st.peek() == '(') {// is opening,closing matches
                    st.pop();
                } else {// else push
                    st.push(ch);
                }

            }
        }
        return st.size();
    }

// Recursion & Backtracking
//66. Subsets
    public void subset(int nums[], List<List<Integer>> arr, List<Integer> al, int i) {
        if (i == nums.length) {
            arr.add(new ArrayList<>(al));
            return;
        }
        al.add(nums[i]);//returns true if success,so not passed directly
        subset(nums, arr, al, i + 1);// add
        al.remove(al.size() - 1);// backtrack because arraylist uses one copy for every recursive call ,not like strings
        subset(nums, arr, al, i + 1);// not add
    }
    //  public void subset(int nums[], List<List<Integer>> arr, List<Integer> al, int start) {
    //     arr.add(new ArrayList<>(al));
    //     for (int i = start; i < nums.length; i++) {
    //         al.add(nums[i]);// for this problem above is enough,but this is more efficient
    //         subset(nums, arr, al, i + 1);
    //         al.remove(al.size() - 1);
    //     }
    // }
    public List<List<Integer>> subsets(int[] nums) {// done all these because of ans in list
        List<List<Integer>> arr = new ArrayList<>();// contains our final ans
        List<Integer> al = new ArrayList<>();// stores intermediate subsets
        subset(nums, arr, al, 0);// auxiliary func
        return arr;
    }    

//67. Subsets II(includes duplicate elements in array)
// can filter redundant elements after sorting with hashsets,but this is more efficient
    public void subset(int nums[], List<List<Integer>> arr, List<Integer> al, int start) {
        arr.add(new ArrayList<>(al));
        for (int i = start; i < nums.length; i++) {// i==nums.length loop wouldn't run and return(implicit base case)
            if (i > start && nums[i] == nums[i - 1])// filtering duplicate elements,loop helps in it
                continue;
            al.add(nums[i]);
            subset(nums, arr, al, i + 1);// add
            al.remove(al.size() - 1);// backtrack
            // explicit dont add branch is not given loop handles that
        }

    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> arr = new ArrayList<>();
        List<Integer> al = new ArrayList<>();
        Arrays.sort(nums);// sorting is required to find duplicates
        subset(nums, arr, al, 0);
        return arr;
    }    

//68. permutations/ also permutationsII which follows same modificaton from subsetsII
    public void recursion(List<Integer> listnums, List<List<Integer>> arr, List<Integer> al) {
        if (listnums.size() == 0) {
            arr.add(new ArrayList<>(al));
            return;
        }
        for (int i = 0; i < listnums.size(); i++) {
            List<Integer> newList = new ArrayList<>(listnums);//use newlist for removing element
            int val = newList.remove(i);
            al.add(val);
            recursion(newList, arr, al);// recurse
            al.remove(al.size() - 1);// backtrack
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> arr = new ArrayList<>();
        List<Integer> listnums = new ArrayList<>();
        for (int i : nums) {
            listnums.add(i);
        }
        recursion(listnums, arr, new ArrayList<>());
        return arr;
    }    

//69. N-Queens
    public boolean isSafe(char arr[][], int row, int col) {
        //vertically up
        for (int i = row - 1; i >= 0; i--) {
            if (arr[i][col] == 'Q')
                return false;
        }
        // left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 'Q')
                return false;
        }
        // right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < arr[0].length; i--, j++) {
            if (arr[i][j] == 'Q')
                return false;
        }

        return true;
    }
    public void backtrack(List<List<String>> Listarr, char arr[][], int row, int n) {
        if (row == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < arr[0].length; j++) {
                    sb.append(arr[i][j]);
                }
                temp.add(sb.toString());
            }
            Listarr.add(temp);// copy the array chessboard to list chessboard
            return;
        }
        for (int i = 0; i < n; i++) {// traversing each col
            if (isSafe(arr, row, i)) {
                arr[row][i] = 'Q';// add
                backtrack(Listarr, arr, row + 1, n);//recurse to next row
                arr[row][i] = '.';// backtrack
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> Listarr = new ArrayList<>();
        char arr[][] = new char[n][n];// make char array chessboard to easily work on
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = '.';
            }
        }
        backtrack(Listarr, arr, 0, n);
        return Listarr;
    }    

//70. unique paths (just like grid ways)
    public int backtrack(int i, int j, int m, int n, int grid[][]) {
        if (i == (m - 1) && j == (n - 1)) {
            return 1;
        }
        if ((i >= m || j >= n)) {
            return 0;
        }
        if (grid[i][j] != -1) {//so we wont recompute
            return grid[i][j];
        }
        grid[i][j] = backtrack(i + 1, j, m, n, grid) + backtrack(i, j + 1, m, n, grid);
        return grid[i][j];
    }
    public int uniquePaths(int m, int n) {
        int grid[][] = new int[m][n];// normally exponential time complexity
        for (int i = 0; i < m; i++) {// but here we make grid to store intermediate results
            for (int j = 0; j < n; j++) {
                grid[i][j] = -1;
            }
        }
        return backtrack(0, 0, m, n, grid);
    }    

//71. unique paths II( like rat in a maze )
    public int backtrack(int i, int j, int m, int n, int obstacleGrid[][], int grid[][]) {
        if ((i >= m || j >= n) || (obstacleGrid[i][j] == 1)) {
            return 0;
        }
        if (i == (m - 1) && j == (n - 1)) {
            return 1;
        }
        if (grid[i][j] != -1) {// if we use the same given array, in obstacle 1, cant tell if its a stored val or not
            return grid[i][j];
        }
        grid[i][j] = backtrack(i + 1, j, m, n, obstacleGrid, grid) + backtrack(i, j + 1, m, n, obstacleGrid, grid);
        return grid[i][j];
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int grid[][] = new int[m][n];// normally exponential time complexity
        for (int i = 0; i < m; i++) {// but here we make grid to store intermediate results
            for (int j = 0; j < n; j++) {
                grid[i][j] = -1;
            }
        }
        return backtrack(0, 0, m, n, obstacleGrid, grid);
    }

//72. word search(like rat in a maze)
    public boolean backtrack(char board[][], String word, int i, int j, int index) {
        if (index == word.length())
            return true;
        if ((i < 0 || j < 0) || (i >= board.length || j >= board[0].length)) {
            return false;
        }
        if (board[i][j] != word.charAt(index)) {// this also returns false for visited cells
            return false;
        }
        char temp = board[i][j];
        board[i][j] = '-';
        boolean val = backtrack(board, word, i + 1, j, index + 1) ||
                backtrack(board, word, i, j + 1, index + 1) ||
                backtrack(board, word, i - 1, j, index + 1) ||
                backtrack(board, word, i, j - 1, index + 1);
        board[i][j] = temp;
        return val;

    }
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (backtrack(board, word, i, j, 0)) {// starting from any cell, ans can be found
                    return true;
                }
            }
        }
        return false;
    }    

//73. sudoku solver
    public boolean isSafe(char board[][], int row, int col, int num) {
        //same row
        for (int i = 0; i < board[0].length; i++) {
            if (board[row][i] == (char) (num + '0')) {
                return false;
            }
        }
        //same col
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char) (num + '0')) {
                return false;
            }
        }
        //same subgrid
        int srow = (row / 3) * 3;// formula
        int scol = (col / 3) * 3;

        for (int i = srow; i < srow + 3; i++) {
            for (int j = scol; j < scol + 3; j++) {
                if (board[i][j] == (char) (num + '0')) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean sudoku(char arr[][],int row,int col) {
        //base case
        if (row == 9) {
            return true;
        }

        //work
        int nextrow = row; int nextcol = col+1;// calculating next row & col
        if (nextcol==9) {
           nextrow += 1;
           nextcol = 0; 
        }
        if (arr[row][col]!='.') { // if a digit already placed
            return sudoku(arr, nextrow, nextcol); // move next
        }
        for (int i = 1; i <=9 ; i++) { // try all digits in a particular position
            if (isSafeSudoku(arr,row,col,i)) {
                arr[row][col] = (char) (j + '0');// number to corresponding char
                if (sudoku(arr, nextrow, nextcol)) { // returning success if valid digit found
                    return true;
                }
                arr[row][col]='.'; //backtracking step
            } 
        }

        return false; // for any iteration if no valid digit found for position of this iteration
    }
    public void solveSudoku(char[][] board) {
        sudoku(board, 0, 0);
    }    

//74. letter combinations of a phone number
    public void combination(List<String> l1, String digits, StringBuilder ans, String arr[], int i) {
        if (i == digits.length()) {
            l1.add(ans.toString());
            return;
        }
        String mappedString = arr[((digits.charAt(i) - '0') - 1)];
        for (int j = 0; j < mappedString.length(); j++) {// traverse through each choice
            ans.append(mappedString.charAt(j));
            combination(l1, digits, ans, arr, i + 1);
            ans.deleteCharAt(ans.length() - 1);// taken stringBuilder, so backtracking
        }
    }
    public List<String> letterCombinations(String digits) {
        List<String> l1 = new ArrayList<>();
        String arr[] = { "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        combination(l1, digits, new StringBuilder(""), arr, 0);
        return l1;
    }    

//75. tower of hanoi problem
    public static void ToH(int n,String source,String helper,String destination) {
        if (n == 1) {
            System.out.println("transfer "+n+" from "+source+" to "+destination);
            return;
        }
        ToH(n-1, source, destination, helper);
        System.out.println("transfer "+n+" from "+source+" to "+destination);
        ToH(n-1, helper, source, destination);
    }    

//76. combination sum
    public void backtrack(int candidates[], int target, int i, List<Integer> ans, HashSet<List<Integer>> s1) {
        if (target == 0) {// this sol, is not that efficient, just for core understanding
            s1.add(new ArrayList<>(ans));
            return;
        }
        if (i == candidates.length || target < 0)// target is always > 0
            return;

        ans.add(candidates[i]);// 3 choice for each number
        backtrack(candidates, target - candidates[i], i + 1, ans, s1);//add it in our ans and move to next
        backtrack(candidates, target - candidates[i], i, ans, s1);//add it ,but again do the same
        ans.remove(ans.size() - 1);
        backtrack(candidates, target, i + 1, ans, s1);//ignore it, move next
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> arr = new ArrayList<>();
        HashSet<List<Integer>> s1 = new HashSet<>();// in this,multiple sets of same ans can appear
        backtrack(candidates, target, 0, new ArrayList<>(), s1);// so using hashset
        for(List<Integer> val : s1){
            arr.add(val);
        }
        return arr;
    }
    public void backtrack(int candidates[], int target, int start, List<Integer> ans, List<List<Integer>> arr) {
        if (target == 0) {// this one is a efficient sol
            arr.add(new ArrayList<>(ans));
            return;
        }
        if (target < 0)
            return;

        for (int i = start; i < candidates.length; i++) {// this loop reflects our 3 choices implicitly
            ans.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, ans, arr);
            ans.remove(ans.size() - 1);

        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> arr = new ArrayList<>();// this one doesn't need a hashset at all
        backtrack(candidates, target, 0, new ArrayList<>(), arr);
        return arr;
    }

//77. combination sum II( no reuse allowed of same numbers )
    public void backtrack(int candidates[], int target, int start, List<Integer> ans, List<List<Integer>> arr) {
        if (target == 0) {
            arr.add(new ArrayList<>(ans));
            return;
        }
        if (target < 0)
            return;

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1])// skip duplicate inputs
                continue;
            ans.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, ans, arr);// only i+1, no reuse allowed
            ans.remove(ans.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);// duplicate inputs appear, sort the array
        List<List<Integer>> arr = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), arr);
        return arr;
    }    
    
    public static void main(String[] args) {
        // Your code goes here
        System.out.println("Hello, world!");
        String arr[] = {"rito","m","roro"};
        System.out.println(Arrays.toString(arr));
    }
}
