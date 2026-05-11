import java.util.*;

public class Greedy {

// activity selection problem
    public static void Activity(int start[] , int end[]) {
        int activities[][] = new int[start.length][3];
    // in this 2d array each row is representing an activity
    // within that 1st col = idx , 2nd col = start time , 3rd col = end time
        for (int i = 0; i < activities.length; i++) {
            activities[i][0] = i;
            activities[i][1] = start[i];
            activities[i][2] = end[i];
        }

    // sorting with ending time
        Arrays.sort(activities,Comparator.comparingDouble(o -> o[2]));

        int count = 0;
        ArrayList<Integer> arr = new ArrayList<>();

    // adding first activity
        arr.add(activities[0][0]);
        count++;
        int lastend = activities[0][2];

        for (int i = 1; i < activities.length; i++) {
            if (lastend <= activities[i][1]) {
                arr.add(activities[i][0]);
                lastend = activities[i][2];//updating lastend
                count++;
            }
        }
        System.out.println("count : "+count);
        for (int i = 0; i < arr.size(); i++) {
            System.out.print("A"+arr.get(i)+" ");
        }

    }    

//fractional knapsack
    public static void knapsack(int value[],int weight[],int knapsack) {
        // 1st col-idx , 2nd col-ratio
        double ratio[][] = new double[value.length][2];
        for (int i = 0; i < ratio.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = value[i]/(double)weight[i];
        }

        // sorting in ascending order
        Arrays.sort(ratio,Comparator.comparingDouble(o -> o[1]));

        int remainingCapacity = knapsack;
        int RecievedVal = 0;
    // reversing the loop to get the large ratios first
        for (int i = ratio.length-1; i >= 0; i--) {
            int idx = (int)ratio[i][0];
            if (remainingCapacity >= weight[idx]) {
                remainingCapacity -= weight[idx];
                RecievedVal += value[idx];
            }
            else{
                RecievedVal += (int)remainingCapacity*ratio[i][1];
                remainingCapacity = 0;
            }
        }
        System.out.println("total val: "+RecievedVal);
    }    

// minimum absolute difference pairs
    public static void Absolute(int arr1[], int arr2[]) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
       int mindiff = 0;
        for (int i = 0; i < arr2.length; i++) {
            mindiff += Math.abs(arr1[i]-arr2[i]);
        }
        System.out.println("min abs diff is: "+mindiff);
    }    

// maximum length chain of pairs (same as activity selection)
    public static void maxLenChain(int chains[][]) {

    // sorting with ending link el
        Arrays.sort(chains,Comparator.comparingDouble(o -> o[1]));

        int count = 0;

    // adding first link
        count++;
        int lastChainend = chains[0][1];

        for (int i = 1; i < chains.length; i++) {
            if (lastChainend <= chains[i][0]) {
                lastChainend = chains[i][1];//updating lastend
                count++;
            }
        }
        System.out.println("count : "+count);

    }        

// Indian coins
    public static void Indian(int amount) {
        Integer coins[] = {1,2,5,10,20,50,100,500,2000};
    // to use reverseorder() we must use type 'Integer'
        Arrays.sort(coins,Comparator.reverseOrder());//descending order

        int coinCount = 0;
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                while (coins[i] <= amount) {
                    coinCount++;
                    arr.add(coins[i]);
                    amount -= coins[i];
                }
            }
        }
        System.out.println("count is : "+coinCount);
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i)+" ");
        }
    }    

//chocola problem
    public static void chocola(Integer verticalCosts[], Integer horizontalCosts[]) {
    // verticalCosts.length = col-1  ,  horizontalCosts.length = row-1;   
    // descending order sorting 
        Arrays.sort(verticalCosts,Comparator.reverseOrder());
        Arrays.sort(horizontalCosts,Comparator.reverseOrder());

        int v = 0 ; int h = 0;//pointers
        int verticalLines = 1; int horizontalLines = 1;
        int cost = 0;

        while (h < horizontalCosts.length && v < verticalCosts.length) {
            if (horizontalCosts[h] <= verticalCosts[v]) {
                cost += (horizontalLines*verticalCosts[v]) ;//through all horizontal lines
                v++;
                verticalLines++;//one vertical cut
            }
            else{
                cost+= (verticalLines*horizontalCosts[h]);//through all vertical lines
                h++;
                horizontalLines++;//one horizontal cut
            }
        }
        while (h<horizontalCosts.length) {//if any left
             cost+= (verticalLines*horizontalCosts[h]);
                h++;
                horizontalLines++;
        }

        while (v<verticalCosts.length) {//if any left
            cost += (horizontalLines*verticalCosts[v]) ;
                v++;
                verticalLines++;
        }

        System.out.println("cost is : "+ cost);
    }    
    public static void main(String[] args) {
       Integer v[] = {2,1,3,1,4};
       Integer h[] = {4,1,2};
       chocola(v, h);
    }
}
