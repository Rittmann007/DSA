public class Bit {

//check even or odd
    public static void EO(int num) {
        if ((num & 1) == 1) {
            System.out.println("odd");
        } else {
            System.out.println("even");
        }
    }    
//get ith bit
    public static void Gith(int num, int i) {
        int bitmask = 1<<i;
        if ((num & bitmask)==0) {
            System.out.println(0);
        } else {
            System.out.println(1);
        }
    }  
//set ith bit
    public static void Sith(int num, int i) {
        int bitmask = 1<<i;
        System.out.println((num | bitmask));
    }   
//clear ith bit
    public static void Cith(int num, int i) {
        int bitmask = ~(1<<i);
        System.out.println((num & bitmask));
    }     
//clear last bits upto ith
    public static void CAith(int num, int i) {
        int bitmask = (~0)<<i;
        System.out.println((num & bitmask));
    }      
//clear range of bits between indices
    public static void Rb(int num , int i , int j) {
        int a = (~0)<<(j+1);  int b = (1<<i)-1;
        int bitmask = (a | b);
        System.out.println((num & bitmask));
    }      
//check if a number is power of 2 or not
    public static void P2(int num) {
        System.out.println((num & (num - 1)) == 0);
    }    
//count set bits
    public static void Cs(int num) {
        int count =0 ;
        while (num > 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num = num >> 1;
        }
        System.out.println(count);
    }    
//swap two numbers without using a third variable
    public static void swap(int x, int y) {
        System.out.println(x+","+y);
        x = x^y;
        y = x^y;
        x = x^y;
        System.out.println(x+","+y);
    }   
    public static void main(String[] args) {
      System.out.println((char)('a' | ' '));
    }
}
