
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> grouped = new HashMap<>();

        for (String str : strs) {
            String sorted = sortString(str);
            grouped.putIfAbsent(sorted, new ArrayList<>());
            grouped.get(sorted).add(str);
        }

        return new ArrayList(grouped.values());
    }

    private String sortString(String str) {
        int diff = (int) 'a';

        int[] equal = new int[26];
        int[] less = new int[26];
        for (int i = 0; i < equal.length; i++) {
            equal[i] = 0;
            less[i] = 0;
        }

        for (char c : str.toCharArray()) {
            equal[c - diff]++;
        }

        for (int i = 1; i < 26; i++) {
            less[i] = equal[i - 1] + less[i - 1];
        }

        char[] result = new char[str.length()];

        for (char c : str.toCharArray()) {
            result[less[c - diff]++] = c;
        }

        return new String(result);
    }

    public static void main(String[] args) {
       
    }
}