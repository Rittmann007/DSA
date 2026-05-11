import java.util.Arrays;

public class Strings {

// check if a string is palindrome or not    
    public static void palindrome(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(str.length()-1-i)) {
                System.out.println("false");
                return ;
            }
        }
       System.out.println("true"); ;
    }

//given a route containing 4 directions (E,W,N,S) find the shortest path to reach destination(from origin)    
//formula for calculating distance between 2 points
//√((x2 - x1)^2 + (y2 - y1)^2)
// North -  y++
// south - y--
// east - x++
// west - x--
    public static void Sp(String str) {
        int x=0 ,y = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'W') {
                x--;
            }
            else if (str.charAt(i) == 'N') {
                y++;
            }
            else if (str.charAt(i) == 'S') {
                y--;
            }
            else {
                x++;
            }

        }
        int x2 = x*x; //x1 and y1 = 0 always
        int y2 = y*y;
        float result = (float)Math.sqrt(x2 + y2);
        System.out.println(result);

    }

// for a given string convert each first letter of each word to uppercase
    public static void Upper(String str) {
        StringBuilder sb = new StringBuilder(); // avoids overhead of appending into string
        sb.append(Character.toUpperCase(str.charAt(0)));
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && i < str.length()-1) {// after space, touppercase then appen
                sb.append(str.charAt(i));
                i++;
                sb.append(Character.toUpperCase(str.charAt(i)));
            }
            else{  // else append as normal
                sb.append(str.charAt(i));
            }
        }
        System.out.println(sb);
}
// string compression (aaabbcccc -> a3b2c4)
    public static void compress(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            Integer count  = 1;
            while (i < str.length()-1 && str.charAt(i) == str.charAt(i+1)) { // if this char isequal to next char
                count++;
                i++;
            }
            sb.append(str.charAt(i)); // appending character
            if (count > 1) {
                sb.append(count);   // appending number
            }
        }
        System.out.println(sb);
    }
// see if two strings are anagrams to each other (race - care)    
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
    public static void main(String[] args) {
      anagram("race", "core");
    }
}

