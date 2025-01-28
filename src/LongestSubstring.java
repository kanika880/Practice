import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestSubstring {

    public static void main(String[] args) {
        System.out.println(subString("abcaddd"));
    }

    public static String subString(String input) {

        List<String> substringList = new ArrayList<>();
        Set<Character> characterSet = new HashSet<>();
        char[] inputArray = input.toCharArray();
        String output = null;

        for(char c: inputArray){
            if(!characterSet.add(c)){
                output = substringList.get(substringList.size()-1) + c ;
                substringList.add(output);
            }
            if(characterSet.add(c)){
                output = output + c;
                substringList.add(output);
            }
        }

        String maxLengthString = null;
        for(String s : substringList){
            maxLengthString = s;
            if(s.length() > maxLengthString.length()){
                maxLengthString = s;
                return maxLengthString;
            }
        }
        return maxLengthString;
    }
}
