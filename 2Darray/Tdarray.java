import java.util.*;

public class Tdarray {
// spirally print the elements of a 2D matrix
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

// sum of both diagonals(sum of each one of them too can come)
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
    // public static int[][] transpose(int M[][]) {
    //     int row  = M.length; int col = M[0].length;

    //     int result[][] = new int[col][row];

    //     for (int i = 0; i < M.length; i++) {
    //         for (int j = 0; j < M[0].length; j++) {
    //             result[j][i] = M[i][j];    // main condition
    //         }
    //     }
    //     return result;
    // }    
// inplace method
// 1  2  3        1  4  7 (as you can see left(main) diagonal remains same)
// 4  5  6   ->   2  5  8 (just elemnts in two sides of the diagonal have swapped places[2,3][3,7][6,8])
// 7  8  9        3  6  9
    public static int[][] iTranspose(int arr[][]) {
        for (int i = 0; i < arr.length-1; i++) {// traversing the right halve of the leftDiagonal
            for (int j = i+1; j < arr.length; j++) {
                int temp = arr[i][j];// to swap with right halve
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        return arr;
    }

    // Matrix addition(add two mxn matrix)
    public static int[][] addition(int arr1[][],int arr2[][]) {
        int result[][] = new int[arr1.length][arr1[0].length];
        for (int i = 0; i < arr1.length; i++) {
            for (int j=0; j < arr1[0].length; j++) {
                result[i][j] = arr1[i][j]+arr2[i][j];
            }
        }
        return result;
    }

    // Matrix multiplication (arr[m][n] x arr[n][p] = r[m][p])[n should be same in both]
    public static int[][] multiplication(int arr1[][],int arr2[][]) {
        int result[][] = new int[arr1.length][arr2[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                for (int j2 = 0; j2 < arr1[0].length; j2++) {
                    result[i][j]+=(arr1[i][j2]*arr2[j2][j]);
                }
            }
        }
        return result;
    }

    // symmetric matrix (A matrix is symmetric if it is square matrix and equal to its transpose)
    public static boolean symmetric(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j]!=arr[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // boundary traversal(print boundary elemnts in a clockwise order)
    public static void boundary(int arr[][]) {
        // upper part
        for (int i = 0; i < arr[0].length; i++) {
           System.out.print(arr[0][i]+" ");
        }

        // right (top->bottom)
        for (int i = 1; i < arr.length-1; i++) {
            System.out.print(arr[i][arr[0].length-1]+" ");
        }
        
        // lower reverse
        for (int i = arr[0].length-1; i >= 0; i--) {
            System.out.print(arr[arr.length-1][i]+" ");
        }

        // left (bottom->top)
        for (int i = arr.length-2; i >= 1; i--) {
            System.out.print(arr[i][0]+" ");
        }
    }

    // 90 degree matrix rotation(rotate the square matrix)(result[j][n-1-i] = arr[i][j])
    // apart from the extra space method, we can do it also in place
    // first, transpose the matrix -> reverse each row
    public static void Ar(int arr[]) {
        int i=0,j=arr.length-1;
        while(i<=j){
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp; 
          i++;j--;
        }
    }
    public static void rotate90(int arr[][]) {
        arr = iTranspose(arr);// transpose
        for (int i = 0; i < arr.length; i++) {
            Ar(arr[i]);// reverse row
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    // Set matrix zeros(O[1] sol required)
    // Given an mxn matrix, if any element in the matrix is 0, set its entire row and column to 0. Do this in-place
    //                [t,f,t]
    // 0  2  3        0  2  3|t| (here we can take two array to mark the row and cols where)
    // 4  5  0   ->   4  5  0|t| (zero appeared, after that we can traverse the matrix the fill the zeros)
    // 7  8  9        7  8  9|f| (according to the arrays)
    // or we can make our firstrow and firstcol to act like this arrays
    public static void setZeroes(int[][] matrix) {
        boolean firstrow = false, firstcol = false;

        // traverse the whole array and whenever zero found,
        // ,mark the corresponding firstrow and firstcol positions with zeros
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0)
                        firstrow = true;// also see if already there was zero in firstrow
                    if (j == 0)
                        firstcol = true;
                    matrix[i][0] = 0;// mark corresponding firstrow,col positions
                    matrix[0][j] = 0;
                }
            }
        }

        // fill the innerMatrix wrt the firstrow,col
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // if there was already zero in firstrow, then fill it completely by zero
        if (firstrow == true) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstcol == true) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    // check identity matrix( An identity matrix is a square matrix in which
    //  all the main(left) diagonal elements are 1 and all other (non-diagonal) elements are 0.)
    public static boolean identity(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if ((i==j && arr[i][j]!=1) || (i!=j && arr[i][j]!=0)) {// check for diagonal and other elements
                    return false
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);

    //     int M[][] = new int[2][3];
    //     for (int i = 0; i < M.length; i++) { // m.length is giving all rows
    //         for (int j = 0; j < M[0].length; j++) { // m[0].length is giving all columns
    //             M[i][j] = sc.nextInt();
    //         }
    //     }
    //  transpose(M);
    //     sc.close();
    

    int arr[][] = {{1,2,3},{4,5,6},{7,8,9}};
    rotate90(arr);
    }
}
