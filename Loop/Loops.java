import java.util.*;

public class Loops {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

// reverse of a number.

//     System.out.print("give me the number now : ");
//     int num = sc.nextInt();
//     int rev = 0;

//     while (num > 0) {
//         int ld = num%10;
//         rev = (rev*10)+ld;
//         num /= 10;
//     }
// System.out.println(rev);

//prime or not

// System.out.print("enter the num : ");
// int num = sc.nextInt();
// if (num == 2) {
//     System.out.println("n is prime");
// } else {
//     Boolean isprime = true;
//     for (int i = 2; i <= Math.sqrt(num); i++) {
//         if (num % i == 0) {
//             isprime = false;
//         }
//     }

//     if (isprime) {
//         System.out.println("n is prime"); 
//     } else {
//          System.out.println("n is not prime");
//     }
// }

// Write a program that reads a set of integers,and then prints the sum of the even and odd integers.
// int esum = 0;
// int osum = 0;
// while (true) {
//     System.out.print("enter the num or press 0 to break : ");
//     int n = sc.nextInt();

//     if (n == 0) {
//         break;
//     } else {
//         if (n%2 == 0) {
//             esum+=n;
//         } else {
//             osum+=n;      }
//     }
    
// }
// System.out.println("sum of even num" + esum);
// System.out.println("sum of odd num" + osum);

//factorial calculator
// System.out.print("enter the num : ");
// int n = sc.nextInt();
// int fact = 1;
// for (int i = n; i >= 1; i--) {
//     fact *= i;
// }
// System.out.println("factorial is : " + fact);



        sc.close();
    }
}
