import java.util.ArrayList;
import java.util.List;

class Solution {
    
    private static final String[] KEYPAD = {
        "",     
        "",     
        "abc",  
        "def",  
        "ghi",  
        "jkl",  
        "mno",  
        "pqrs", 
        "tuv",  
        "wxyz"  
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
      
        if (digits == null || digits.length() == 0) {
            return result;
        }

        backtrack(result, digits, 0, new StringBuilder());
        return result;
    }

    private void backtrack(List<String> result, String digits, int index, StringBuilder current) {
   
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

    
        int digit = digits.charAt(index) - '0';
        String letters = KEYPAD[digit];

        for (char c : letters.toCharArray()) {
            current.append(c);                          
            backtrack(result, digits, index + 1, current); 
            current.deleteCharAt(current.length() - 1); 
        }
    }
}