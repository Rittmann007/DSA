class Node {
Node children[] = new Node[26];// 26 small letters in eng
boolean EndOfWord = false;
Node(){
    for (int i = 0; i < children.length; i++) {
        children[i] = null;
    }
}}

class trieClass { 
Node root = new Node();
// insert in trie
void insert(String word){
    Node curr = root;
    for (int i = 0; i < word.length(); i++) {
        int idx = word.charAt(i)-'a';
        if (curr.children[idx] == null) {
            curr.children[idx] = new Node();
        }
        curr = curr.children[idx];
    }
    curr.EndOfWord = true;
}    
// search in trie
boolean search(String key){
    Node curr = root;
    for (int i = 0; i < key.length(); i++) {
        int idx = key.charAt(i)-'a';
        if (curr.children[idx] == null) {
            return false;
        }
        curr = curr.children[idx];
    }
    return curr.EndOfWord;
}}

// special trie with frequency for prefix problem
class prefixNode {
prefixNode children[] = new prefixNode[26];// 26 small letters in eng
boolean EndOfWord = false;
int frequency;
prefixNode(){
    for (int i = 0; i < children.length; i++) {
        children[i] = null;
    }
    frequency = 1;
}}

class prefixtrieClass { 
prefixNode root = new prefixNode();
// insert in trie
void insert(String word){
    prefixNode curr = root;
    for (int i = 0; i < word.length(); i++) {
        int idx = word.charAt(i)-'a';
        if (curr.children[idx] == null) {
            curr.children[idx] = new prefixNode();
        }
        else{
            curr.children[idx].frequency++;
        }
        curr = curr.children[idx];
    }
    curr.EndOfWord = true;
}}

public class Trie {

// word break problem
    public static boolean wordbreak(trieClass t1,String key) {
        if (key.length() == 0) {// 2nd part being length 0,no need to check
            return true;
        }
        for (int i = 1; i <= key.length(); i++) {// in substring func i is exclusive
            // if 1st part is valid then check 2nd part recursively
            if (t1.search(key.substring(0, i)) &&  wordbreak(t1, key.substring(i))) {
                return true;// if both true
            }
        }
        return false;
    }    

// Prefix problem
    public static String prefix(prefixtrieClass p1,String key) {
        prefixNode curr = p1.root;
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i)-'a';
            if (curr.children[idx] == null) {
                return "";
            }else {
                s1.append(key.charAt(i));
                if (curr.children[idx].frequency == 1) {
                    break;
                }
            }
            curr = curr.children[idx];
        }
        return s1.toString();
    }    

// starts with problem
    public static boolean startsWith(trieClass t1,String key) {
        Node curr = t1.root;
        for (int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i)-'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }    

// unique substrings
    public static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
           if (root.children != null) {
            // for each not null children recursively
             count += countNodes(root.children[i]);
            }
        }
        return count+1;
    }
    public static void substrings(String key) {
        trieClass t1 = new trieClass();
        for (int i = 0; i < key.length(); i++) {
            // find suffix
            String suffix = key.substring(i);
            // insert them
            t1.insert(suffix);
        }
        System.out.println(countNodes(t1.root));//count the nodes
    }    

// longest word with all prefixes
    static String ans = "";
    public static void longrec(Node root,StringBuilder temp) {
        if (root == null) {
            return;
        }
        for (int i = 0; i < 26; i++) {// for each letter
            if (root.children[i]!=null && root.children[i].EndOfWord == true) {
                char ch = (char)(i+'a');
                temp.append(ch);
                if (ans.length() < temp.length()) {
                    ans = temp.toString();// update ans
                }
                longrec(root.children[i], temp);// recursive step
                temp.deleteCharAt(temp.length()-1);//backtracking step
            }

        }
    }    
    public static void main(String[] args) {
        // String words[] = {"the","a","there","their","any","thee"};
        // trieClass t1 = new trieClass();
        // for (int i = 0; i < words.length; i++) {
        //     t1.insert(words[i]);
        // }
        // System.out.println(t1.search("a"));
        // trieClass t1 = new trieClass();
        // String words[] = {"i","like","sam","samsung","mobile","ice"};
        // for (int i = 0; i < words.length; i++) {
        //     t1.insert(words[i]);
        // }
        // System.out.println(wordbreak(t1,"ilikesamsung"));
        // prefixtrieClass p1 = new prefixtrieClass();
        // String words[] = {"zebra","dog","duck","dove"};
        // for (int i = 0; i < words.length; i++) {
        //     p1.insert(words[i]);
        // }
        // for (int i = 0; i < words.length; i++) {
        //     System.out.print(prefix(p1, words[i])+" ");
        // }
        String words[] = {"a","banana","app","appl","ap","apply","apple"};
        trieClass t1 = new trieClass();
        for (int i = 0; i < words.length; i++) {
            t1.insert(words[i]);
        }
       longrec(t1.root, new StringBuilder(""));
        System.out.println(ans);
    }
}
