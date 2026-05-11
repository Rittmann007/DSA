import java.util.*;
public class Arraylist {
//swap two indices    
    public static void swap(ArrayList<Integer> list,int idx1,int idx2) {
        int temp = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, temp);
    }
//container with most water(Brute force)(O(n^2))
    public static void CmWB(ArrayList<Integer> list) {
        int max = 0;

        for (int i = 0; i < list.size(); i++) {// checking every pair of boundaries
            for (int j = i+1; j < list.size(); j++) {

                int height = Math.min(list.get(i), list.get(j));//min of two boundaries
                int width = j-i; // formula for calculating width
                max = Math.max(max, (height*width));

            }
        }
        System.out.println(max);
    }    
//container with most water(two pointer approach)(O(n))
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
//two sum or pair sum(brute force)
    public static void PairSm(ArrayList<Integer> list,int target) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                if ((list.get(i)+list.get(j))==target) {
                    System.out.println("exists");
                    return;
                }
            }
        }
        System.out.println("not exists");
    }    
//two sum or pair sum(Two pointer approach)(works only for sorted arraylists)
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
//two sum or pair sum - 2(Two pointer approach)(in a rotated and sorted arraylist)
    public static void PairSmTpTWO(ArrayList<Integer> list,int target) {
        int pivot = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > list.get(i+1)) {// getting the pivot point
                pivot = i;
                break;
            }
        }
        int Lp = pivot+1;
        int Rp = pivot;


        while (Lp != Rp) {
            //case 1
            if (list.get(Rp)+list.get(Lp) == target) {
                System.out.println("exists");
                return;
            }
            //case 2
            if (list.get(Rp)+list.get(Lp) < target) { //move to bigger values
                Lp = (Lp+1) % list.size();
            }
            //case 3
            else{    //move to smaller values
                Rp = (list.size()+Rp-1) % list.size();
            }
        }

        System.out.println("not exists");
    }    
// Monotonic ArrayList(EASY)    
// An Arraylist is monotonic if it is either monotone increasing or monotone decreasing.
// AnArraylist nums ismonotone increasing if for all i <= j, nums.get(i) <=nums.get(j). An
// Arraylist nums is monotone decreasing if for all i <= j, nums.get(i) >= nums.get(j).
// Given an integer Arraylist nums, return true if the given list is monotonic, or false otherwise.  
    public static void monotone(ArrayList<Integer> list) {
        int k = 0;int n = 0;
        for (int i = 0; i < list.size()-1; i++) {
            if (!(list.get(i)<=list.get(i+1))) {
               break;
            }
            else{
                k++;
            }
        }
        for (int i = 0; i < list.size()-1; i++) {
            if (!(list.get(i)<=list.get(i+1))) {
                break;
            }
            else{
                n++;
            }
        }
        if (k == list.size()-1 || n == list.size()-1) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
        // shorter code
        // boolean inc = true;
        // boolean dec = true;
        // for (int i=0; i<A.size()-1; i++) {
        // if (A.get(i) > A.get(i+1))
        // inc = false;
        // if (A.get(i) < A.get(i+1))
        // dec = false;
        // }
        // return inc || dec;
    }  

// Lonely Numbers in ArrayList(MEDIUM)
// Youaregivenanintegerarraylistnums.Anumberxislonelywhenitappearsonlyonce,and
// no adjacent numbers (i.e. x + 1 and x - 1) appear in the arraylist.
// Return all lonely numbers in nums. You may return the answer in any order.
    public static void Lonely(ArrayList<Integer> list) {
        // boolean x1 = false;
        // boolean x2 = false;
        // boolean repeat = false;

        // for (int i = 0; i < list.size(); i++) {
        //     for (int j = 0; j < list.size(); j++) {
        //         if (list.get(j) == list.get(i)+1) {
        //             x1 = true;
        //         }
        //        else if (list.get(j) == list.get(i)-1) {
        //             x2 = true;
        //         }
        //         else if (i!=j && list.get(j) == list.get(i)) {
        //             repeat = true;
        //         }

        //     }
        //     if (!(x1 || x2 || repeat)) {
        //         System.out.print(list.get(i)+" ");
        //     }
        //     x1 = false;x2 = false;repeat = false;
        // }

        //optimized sol O(nlogn)
        Collections.sort(list);
        if (list.get(1) - list.get(0) > 1) {  //first element
            System.out.print(list.get(0)+ " ");
        }
        for (int i = 1; i < list.size()-1; i++) { // rest
            if ((list.get(i) - list.get(i-1)) > 1 && (list.get(i+1) - list.get(i)) > 1) {
                System.out.print(list.get(i)+ " ");
            }
        }
        if ((list.get(list.size()-1) - list.get(list.size()-2)) > 1) { // last element
            System.out.print(list.get(list.size()-1)+ " ");
        }
    }
// Most Frequent Number following Key (EASY)
// You are given an integer Arraylist nums. You are also given an integer key, which is present in
// nums.
// For every unique integer target in nums, count the number of times target immediately follows
// an occurrence of key in nums. In other words, count the number of indices i such that:
// 0 <= i <= nums.size() - 2,
// nums.get(i) == key and,
// nums.get(i+1) == target.
// Return the target with the maximum count.    
    public static void followingkey(ArrayList<Integer> list,int key) {
        int countarray[] = new int[1000];

        for (int i = 0; i < list.size()-1; i++) { // counting for each num its count of follow
            if (list.get(i) == key) {
                countarray[list.get(i+1)-1]++;
            }
        }

        int max = Integer.MIN_VALUE;
        int num = 0;

        for (int i = 0; i < countarray.length; i++) { // finding which num has highest count
            if (max < countarray[i]) {
                max = countarray[i];
                num = i+1;
            }
        }
        System.out.println(num + " appeared "+max + " times ");
    }
    public static void main(String[] args) {
       ArrayList<Integer> list = new ArrayList<>();
       list.add(1);
       list.add(100);
       list.add(200);
        list.add(1);
        list.add(100);
    //    list.add(10);
       
       
    //    System.out.println(list);
    //    swap(list, 0, 3);
    //    System.out.println(list);
    // CmWTP(list);
    followingkey(list, 1);
    }
}
