import java.util.*;
public class Oop{
    public static void main(Strings[] args) {
        Scanner sc =new Scanner(System.in);

        complex c = new complex();
        System.out.print("enter first nums real part : ");
        c.real1 = sc.nextInt();
        System.out.print("enter first nums imaginary part : ");
        c.imaginary1 = sc.nextInt();
        System.out.print("enter second nums real part : ");
        c.real2 = sc.nextInt();
        System.out.print("enter second nums imaginary part : ");
        c.imaginary2 = sc.nextInt();

        c.sum();
        c.difference();
        c.product();

        sc.close();
       
    }
}

// Q-1
class complex {

    int real1,real2;
    int imaginary1,imaginary2;


    void sum(){
       int real = real1+real2;
       int imaginary = imaginary1+imaginary2;
       System.out.println("final sum is : "+ "("+real+"+"+imaginary+"i"+")");
    }
    void difference(){
       int real = real1-real2;
       int imaginary = imaginary1-imaginary2;
       System.out.println("final difference is : "+ "("+real+"+"+imaginary+"i"+")");
    }
    void product(){
       int real = (real1*real2)-(imaginary1*imaginary2);
       int imaginary = (real1*imaginary2)+(real2*imaginary1);
       System.out.println("final product is : "+ "("+real+"+"+imaginary+"i"+")");
    }
    
}
