import java.util.*;

public class Pattern {

//hollow retangle
    //  public static void hollow(int row, int col) {
    //     for (int i = 1; i <= row; i++) {
    //         for (int j = 1; j <= col; j++) {
    //             if (i==1||i==row||j==1||j==col) { //condition to print borders
    //                 System.out.print("*");
    //             }
    //             else{
    //                 System.out.print(" "); //if not satisfied,print space to make hollow
    //             }
    //         }
    //         System.out.println();
    //     }
    //  }    

//inverted and rotated half pyramid     
       public static void irp(int line) {
        for (int i = 1; i <= line; i++) {
            for (int j = 1; j <= line-i; j++) { //first spaces == line-line no
               System.out.print(" ");
            }
            for (int k = 1; k <= i; k++) { //then star == line no
               System.out.print("*");
            }
            System.out.println();
        }
       }

//inverted half pyramid with number     
       public static void ipN(int line) {
        for (int i = 1; i <= line; i++) {
            for (int k = 1; k <= line-i+1; k++) { //numbers will be decreasing with the star
               System.out.print(k);
            }
            System.out.println();
        }
       }

//floyd's triangle  
      public static void Ft(int line) {
        int counter = 1;
        for (int i = 1; i <= line; i++) {
            for (int k = 1; k <= i; k++) { //counter is everything
               System.out.print(counter + " ");
               counter++;
            }
            System.out.println();
        }
       }

//01 triangle  
      public static void z1(int line) {
        for (int i = 1; i <= line; i++) {
            for (int k = 1; k <= i; k++) { 
               if ((i+k)%2==0) {  // print 1 when row+col is even
                System.out.print(1);
               } else {
                System.out.print(0); // otherwise 0
               }
            }
            System.out.println();
        }
       }
//solid rombus 
      public static void sr(int line) {
        for (int i = 1; i <= line; i++) {
            for (int k = 1; k <= line-i; k++) { //formula for spaces
              System.out.print(" ");
            }
            for (int j = 1; j <= line; j++) {// no of stars are const
                System.out.print("*");
            }
            System.out.println();
        }
       }
//hollow rombus 
      public static void hr(int line) {
        for (int i = 1; i <= line; i++) {
            for (int k = 1; k <= line-i; k++) { //formula for spaces
              System.out.print(" ");
            }
           for (int j = 1; j <= line; j++) { // same as hollow rectangle
            if (i==1||i==line||j==1||j==line) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
           }
            System.out.println();
        }
       }
//diamond pattern
      public static void dp(int line) {
        for (int i = 1; i <= line; i++) {
            for (int k = 1; k <= line-i; k++) { //formula for spaces
              System.out.print(" ");
            }
           for (int j = 1; j <= 2*i-1; j++) {
            System.out.print("*");
           }
            System.out.println();
        }
        for (int i = line; i >= 1; i--) {
            for (int k = 1; k <= line-i; k++) { //formula for spaces
              System.out.print(" ");
            }
           for (int j = 1; j <= 2*i-1; j++) {
            System.out.print("*");
           }
            System.out.println();
        }
       }
//butterfly pattern  
      public static void bfly(int line) {
        for (int i = 1; i <= line; i++) { //for first half
            for (int k = 1; k <= i; k++) { 
            System.out.print("*");
            }
            for (int k = 1; k <= 2*(line-i); k++) { //formula for printing spaces
            System.out.print(" ");
            }
            for (int k = 1; k <= i; k++) { 
            System.out.print("*");
            }
            System.out.println();
        }
        for (int i = line; i >= 1; i--) {// second half ,reverse the loop
            for (int k = 1; k <= i; k++) { //inner code remains the same
            System.out.print("*");
            }
            for (int k = 1; k <= 2*(line-i); k++) { 
            System.out.print(" ");
            }
            for (int k = 1; k <= i; k++) { 
            System.out.print("*");
            }
            System.out.println();
        }
       }

    public static void main(String[] args) {
    //    Scanner sc = new Scanner(System.in);

    //    System.out.print("give me the lines now : ");
    //    int lines = sc.nextInt();

//star pattern
    //    for(int i = 1; i<=lines ; i++){ //for each line
    //     for(int j = 1 ; j<=i ; j++){    // no of line == no of stars
    //         System.out.print("*");
    //     }
    //     System.out.println();
    //    }

//inverted star pattern
    //    for(int i = 1; i<=lines ; i++){ //for each line
    //     for(int j = 1 ; j<=lines-i+1 ; j++){    // no of lines increasing ,  no of stars decreasing
    //         System.out.print("*");
    //     }
    //     System.out.println();
    //    }

//number pattern
    //     for(int i = 1; i<=lines ; i++){ //for each line
    //     for(int j = 1 ; j<=i ; j++){    // no of line == no of no
    //         System.out.print(j);
    //     }
    //     System.out.println();
    //    }    

//same with abcd 
    //     char ch = 'A';
    //     for(int i = 1; i<=lines ; i++){ //for each line
    //     for(int j = 1 ; j<=i ; j++){    // no of line == no of character
    //         System.out.print(ch);
    //         ch++;
    //     }
    //     System.out.println();
    //    }    

    //    sc.close();
    dp(4);
    }
}
