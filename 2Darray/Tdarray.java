import java.util.*;

public class Tdarray {

    public static void spiralMatrix(int M[][]) {
        int Sr = 0; // starting row
        int Sc = 0; // starting coloumn
        int Er = M.length - 1; // end row
        int Ec = M[0].length - 1;// end colomn

        while (Sr <= Er && Sc <= Ec) {

            // top
            for (int i = Sc; i <= Ec; i++) {
                System.out.print(M[Sr][i] + ",");
            }
            // right
            for (int i = Sr + 1; i <= Er; i++) {
                System.out.print(M[i][Ec] + ",");
            }
            // down
            for (int i = Ec - 1; i >= Sc; i--) {
                if (Sr == Er) {
                    break;
                }
                System.out.print(M[Er][i] + ",");
            }
            // left
            for (int i = Er - 1; i > Sr; i--) {
                if (Sc == Ec) {
                    break;
                }
                System.out.print(M[i][Sc] + ",");
            }

            Sc++; // for inner spirals
            Sr++;
            Er--;
            Ec--;
        }
    }

// sum of both diagonals(sum of each one of them too)

    public static void DiagonalSum(int M[][]) {
        int sum = 0;

        //O(n^2) method

        // for (int i = 0; i < M.length; i++) {
        //     for (int j = 0; j < M[0].length; j++) {
        //         if (i == j) { // forleft diagonal[primary diagonal] i==j (for this else if condition the
        //                       // common element in even matrix is calculated once)
        //             sum += M[i][j];
        //         } else if (i + j == M.length - 1) { // for right i+j == n-1
        //             sum += M[i][j];
        //         }
        //     }
        // }

        //O(n) method

        for (int i = 0; i < M.length; i++) {
            sum += M[i][i];   // primary diagonal
            
            if (i != M.length-1-i) { // to avoid recalculating common element in even matrix
                
                sum += M[i][M.length-1-i];  // secondary diagonal
            }
        }

        System.out.println(sum);
    }

// search element in a (row wise and column wise)sorted matrix
// we will use staircase search (for (n x m) matrix time complexity will be O(n+m))
//   10,20,30,40 <-
//   15,25,35,45       // these are the two positions in matrix from where if we start to search the element then
//   27,29,37,48       // we will exactly know which direction to take
//-> 32,33,39,50       // if (key < M[i][j] => leftmove) and if (key > M[i][j] => bottomMove)

public static void SearchM(int M[][] , int key) {
    int i = 0  ; int j = M[0].length-1;

    while (i <= M.length-1 && j >= 0) {  //untill we reach the other corner of the matrix
        if (M[i][j] == key) {
            System.out.println("found at (" + i +","+j +")");
            return;
        }
        else if(M[i][j] > key){   // left move
            j--;
        }
        else{        // bottom move
            i++;
        }
    }

    System.out.println("not found");
}

// sum of numbers in the second row of the array
    public static void practice2(int M[][]) {
       int sum = 0;
        for (int i = 0; i < M[0].length; i++) {
            sum += M[1][i];
        }
        System.out.println(sum);
    }
// transpose of a matrix
    public static void transpose(int M[][]) {
        int row  = M.length; int col = M[0].length;

        int result[][] = new int[col][row];

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                result[j][i] = M[i][j];    // main condition
            }
        }
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }    
    public static void main(Strings[] args) {
        Scanner sc = new Scanner(System.in);

        int M[][] = new int[2][3];
        for (int i = 0; i < M.length; i++) { // m.length is giving all rows
            for (int j = 0; j < M[0].length; j++) { // m[0].length is giving all columns
                M[i][j] = sc.nextInt();
            }
        }
     transpose(M);
        sc.close();
    }
}
