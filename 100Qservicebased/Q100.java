import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
    
    public static void main(String[] args) {
        // Your code goes here
        System.out.println("Hello, world!");
        String arr[] = {"rito","m","roro"};
        System.out.println(Arrays.toString(arr));
    }
}
