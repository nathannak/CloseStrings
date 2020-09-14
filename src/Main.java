import java.util.*;

public class Main {

    /*

    We will consider two strings close if one can be obtained from the other, using the following operations:

    swap any two symbols in one of the strings,
    swap occurrences of any two existing symbols in one of the strings (for example, if your string contains both as and bs, you can change all as to bs and all the bs to as).
    Now you want to write a method that finds out whether the given strings are considered close, by the definition above.

    example:

    For A = "abbzccc" and B = "babzzcz", the output should be
    closeNames(A, B) = true.

    One possible way to transform "abbzccc" to "babzzcz" is this:

    "abbzccc" (this string is className)
    "babzccc" (swap positions of the first two characters)
    "babczzz" (switch all c and z characters)
    "babzzcz" (swap positions of the characters at indices 3 and 5; this string is now methodName)

    For A = "abcbdb" and B = "bbbcca", the output should be closeNames(A, B) = false.

     */

    //A = "zzzaakk", B = "akzzzzz", false
    //A = "abbzccc", B = "babzzcz", true
    //A = "abbbzcc", B = "babzzcz", true
    //A = "abbbzcf", B = "babzzcz", false
    //A = "abbzzca", B = "babzzcz", false

    public static void main(String[] args) {

        System.out.println( isCloseString("zzzaakk","akzzzzz") ); //false -> max z is 5 in second one, 3 in first one
        System.out.println( isCloseString("abbzccc","babzzcz") ); //true -> z and c can be swapped
        System.out.println( isCloseString("abbbzcc","babzzcz") ); //true -> z and b / a and c can be swapped
        System.out.println( isCloseString("abbzzca","babzzcz") ); //true -> max z is 3 in second one, 2 in first one

    }

    public static boolean isCloseString(String a , String b) {

        //if anagram return true
        if(isAnagram(a,b)) return true;

        //not anagram, max occurrence of any char in a and b should be equal [case: "zzzaakk", "akzzzzz"]
        Map<Character,Integer> map1 = new HashMap<>();

        for(char c :  a.toCharArray())
            map1.put(c, map1.getOrDefault(c,0)+1);

        Map<Character,Integer> map2 = new HashMap<>();
        for(char c :  b.toCharArray())
            map2.put(c, map2.getOrDefault(c,0)+1);

        List<Integer> list1 = new ArrayList<>(map1.values());
        List<Integer> list2 = new ArrayList<>(map2.values());

        if(Collections.max(list1).intValue() != Collections.max(list2).intValue() )
            return false;

        //now make sure differences in chars cancel out each other
        int sum1 = 0;
        for(int n : list1) sum1 += n;

        int sum2 = 0;
        for(int n : list2) sum2 += n;

        return sum1-sum2 ==0;

    }

    public static boolean isAnagram(String a, String b) {

        int[] arr1 = new int[26];
        for(char c : a.toCharArray()) {
            arr1[c-'a']++;
        }

        for(char c : b.toCharArray()){
            arr1[c-'a']--;
        }


        for(int n : arr1) {
            if(n!=0) return false;
        }

        return true;

    }

}

