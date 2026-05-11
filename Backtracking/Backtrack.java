public class Backtrack {

// backtracking with arrays(1st exp)
    public static void Bt(int arr[],int i,int val) {
        if (i==arr.length) {  // base case
            for (int j = 0; j < arr.length; j++) { // print the array
        System.out.print(arr[j]+" ");
        }
        return;  // and return
        }
        arr[i]=val;
        Bt(arr, i+1, val+1); 
        arr[i] = arr[i]-2;  // backtracking part
    }    
// find string subsets
    public static void subset(String str,int i,String ans) {
        if (i==str.length()) { // base case
            System.out.println(ans);
            return;
        }
        // for each character in the string
        subset(str, i+1, ans+str.charAt(i));// yes (add in the ans)
        subset(str, i+1, ans);// no
    }    
// find all permutations of a string
    public static void permutation(String given , String ans) {
        if (given.length()==0) {  // base case
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < given.length(); i++) {// looping through every character choice of "given"
            // "abcde" = "ab" + "cd" = "abcd"
            String newgiven = given.substring(0, i) + given.substring(i+1);
            permutation(newgiven, ans+given.charAt(i));// pushing current char in "ans" and out of "given"
        }
    }    

// n-queens all ways(O(n!)=> first Q- n choice, 2nd Q- n-1 choice, 3rd Q- n-2
// choice etc)
    public static boolean isSafe(char board[][], int row, int col) {
        // vertical up
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board[0].length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void nQueenAllways(char board[][], int row) {
        if (row == board.length) {
            System.out.println("---------------");
            for (int i = 0; i < board.length; i++) { // m.length is giving all rows
                for (int j = 0; j < board[0].length; j++) { // m[0].length is giving all columns
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            return;
        }
        for (int i = 0; i < board[0].length; i++) { // try to put the queens in different rows
            if (isSafe(board, row, i)) { // check if safe to put
                board[row][i] = 'Q';
                nQueenAllways(board, row + 1); // go for next row
                board[row][i] = '.'; // backtracking step
            }
        }

    }

// grid ways
    public static int Grid(int i,int j,int n,int m) {
        if (i==(n-1) && j==(m-1)) {  // final cell
            return 1;
        }
        else if (i==n || j==m) { // avoid boundary cross
            return 0;
        }
        int w1 = Grid(i+1, j, n, m);
        int w2 = Grid(i, j+1, n, m);
        int total  = w1 + w2;
        return total;
    }   

// sudoku solver
// in sudoku we can place same digit in same row,coloumn,subgrid (here we try to find one valid sol thats why returning boolean)
    public static boolean isSafeSudoku(int arr[][],int row,int col,int digit) {
        for (int i = 0; i < 9; i++) { // horizontal
           if (arr[row][i] == digit) {
            return false;
           }
        } 
        for (int i = 0; i < 9; i++) { // vertical
           if (arr[i][col] == digit) {
            return false;
           }
        } 

        //same grid(3x3)
        int startingrow = (row/3)*3; //formula
        int startingcol = (col/3)*3;

        for (int i = startingrow; i < startingrow+3; i++) {
            for (int j = startingcol; j < startingcol+3; j++) {
                if (arr[i][j]==digit) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean sudoku(int arr[][],int row,int col) {
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
        if (arr[row][col]!=0) { // if a digit already placed
            return sudoku(arr, nextrow, nextcol); // move next
        }
        for (int i = 1; i <=9 ; i++) { // try all digits in a particular position
            if (isSafeSudoku(arr,row,col,i)) {
                arr[row][col]=i;
                if (sudoku(arr, nextrow, nextcol)) { // returning success if valid digit found
                    return true;
                }
                arr[row][col]=0; //backtracking step
            } 
        }

        return false; // for any iteration if no valid digit found for position of this iteration
    }

//     Question 1 :
// Rat in a Maze
// You are given a starting position for a rat which is stuck in a maze at an initial point (0, 0) (the
// maze can be thought of as a 2-dimensional plane). The maze would be given in the form of a
// square matrix of order N * N where the cells with value 0 represent the maze’s blocked
// locations while value 1 is the open/available path that the rat can take to reach its destination.
// The rat's destination is at (N - 1, N - 1).
// Your task is to find all the possible paths that the rat can take to reach from source to
// destination in the maze.
// The possible directions that it can take to move in the maze are 'U'(up) i.e. (x, y- 1) , 'D'(down)
// i.e. (x, y + 1) , 'L' (left) i.e. (x - 1, y), 'R' (right) i.e. (x + 1, y).
// (This problem is similar to Grid ways.

// here we want to find all values thats why just, return

    public static void rat(int arr[][],int row,int col,String path) {
        //base case
        if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length || arr[row][col] == 0 || arr[row][col] == -1) {
            return;
        }
        if (row == arr.length-1 && col == arr[0].length-1) { //destination reached
            System.out.println(path);
            return;
        }
        
        //work
        arr[row][col] = -1; //visited

        rat(arr, row-1, col, path+"U");//up
        rat(arr, row+1, col, path+"D");//down
        rat(arr, row, col-1, path+"L");//left
        rat(arr, row, col+1, path+"R");//right

        arr[row][col] = 1; //unvisited
    }
  
// Question 2 :
// Keypad Combinations
// Given a string containing digits from 2-9 inclusive, print all possible letter combinations that
// the number could represent. You can print the answer in any order.
// A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1
// does not map to any letters    
// similar to string subsets problem.
     public static void keypad(String str,int i,String ans,String map[]) {
        if (i==str.length()) { // base case
            System.out.println(ans);
            return;
        }
       //primitive way(not for all no's)
        // keypad(str, i+1, ans+(map[(str.charAt(i)-'0')].charAt(0)),map);
        // keypad(str, i+1, ans+(map[(str.charAt(i)-'0')].charAt(1)),map);
        // keypad(str, i+1, ans+(map[(str.charAt(i)-'0')].charAt(2)),map);

        // modern way
       String MappedString = map[(str.charAt(i)-'0')];//this is giving our mapped String
       for (int j = 0; j < MappedString.length(); j++) { // looping through every charcter choice
        keypad(str, i+1, ans+(MappedString.charAt(j)),map);
       }
    // if "ans" variable is build using Stringbuilder instead of string
    // then we have to subtract the pushed character in backtracing step
    // for string, Each recursive call gets its own copy of ans
    // although Stringbuilder is more efficient
    }    

// Knight’s Tour
//     Given a N*N board with the Knight placed on the first block of an empty board. Moving
// according to the rules of chess, knights must visit each square exactly once. Print the order of
// each cell in which they are visited   
    public static boolean Ktour(int mat[][],int row,int col,int count,int n) {
        //base case
        if (row<0 || col<0 || row>=n || col>=n) { //boundary cases
            return false;
        }
        if (mat[row][col] != -1) { // already visited
            return false;
        }

        mat[row][col] = count; // fill the cell with count,to fill the last cell also

        if (count == (n*n)-1) { // end of sol
            return true;
        }
        //work
        // taking 8 possible moves for a knight
        if (Ktour(mat, row-2, col+1, count+1,n)) {
            return true;
        };
        if (Ktour(mat, row-1, col+2, count+1,n)) {
            return true;
        };
        if ( Ktour(mat, row+1, col+2, count+1,n)) {
           return true;
        };
        if (Ktour(mat, row+2, col+1, count+1,n)) {
            return true;
        };
        if (Ktour(mat, row+2, col-1, count+1,n)) {
            return true;
        };
        if (Ktour(mat, row+1, col-2, count+1,n)) {
            return true;
        };
        if (Ktour(mat, row-1, col-2, count+1,n)) {
            return true;
        };
        if (Ktour(mat, row-2, col-1, count+1,n)) {
            return true;
        };

        mat[row][col] = -1; // backtrack/unmark if wrong move
        return false;
    }
    public static void main(String[] args) {
    //     int arr[] = new int[5];
    //    Bt(arr, 0, 1);
    //    for (int i = 0; i < arr.length; i++) {
    //     System.out.print(arr[i]+" ");
    //     }
    //     char board[][] = new char[4][4];
    //     for (int i = 0; i < board.length; i++) { // m.length is giving all rows
    //         for (int j = 0; j < board[0].length; j++) { // m[0].length is giving all columns
    //            board[i][j] = '.';
    //         }
    //     }
    //    System.out.println(Grid(0, 0, 3, 3)); 
    // }

//     int arr[][] = {
//     {5, 3, 0, 0, 7, 0, 0, 0, 0},
//     {6, 0, 0, 1, 9, 5, 0, 0, 0},
//     {0, 9, 8, 0, 0, 0, 0, 6, 0},
//     {8, 0, 0, 0, 6, 0, 0, 0, 3},
//     {4, 0, 0, 8, 0, 3, 0, 0, 1},
//     {7, 0, 0, 0, 2, 0, 0, 0, 6},
//     {0, 6, 0, 0, 0, 0, 2, 8, 0},
//     {0, 0, 0, 4, 1, 9, 0, 0, 5},
//     {0, 0, 0, 0, 8, 0, 0, 7, 9}
// };

// if (sudoku(arr, 0, 0)) {
//      for (int i = 0; i < arr.length; i++) { // m.length is giving all rows
//             for (int j = 0; j < arr[0].length; j++) { // m[0].length is giving all columns
//                System.out.print(arr[i][j]);
//             }
//             System.out.println();
//         }
// }
// else{
//     System.out.println("no");
// }

//  int maze[][] = { { 1, 0, 0, 0 },
//                   { 1, 1, 0, 1 },
//                   { 0, 1, 0, 0 },
//                   { 1, 1, 1, 1 } };

// rat(maze, 0, 0, "");

// String mapS[] = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
// keypad("79", 0, "", mapS);

int mat[][] = new int[8][8];
for (int i = 0; i < 8; i++) { for (int j = 0; j < 8; j++) { mat[i][j] = -1; } };
if (Ktour(mat, 0, 0, 0, 8)) {
     for (int i = 0; i < mat.length; i++) { // m.length is giving all rows
            for (int j = 0; j < mat[0].length; j++) { // m[0].length is giving all columns
               System.out.print(mat[i][j]+ " ");
            }
            System.out.println();
        }
}
else{
    System.out.println("no");
}
}
}