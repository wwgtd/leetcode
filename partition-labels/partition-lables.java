
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            lastMap.putIfAbsent(c, s.lastIndexOf(c));
        }

        int p1 = 0, p2 = 0;

        Character c = s.charAt(0);
        List<Integer> listResult = new ArrayList();
        p1 = 0;
        p2 = lastMap.get(c);
        if (p2 == p1) {
            listResult.add(1);
            p1 = 1;
            p2 = lastMap.get(s.charAt(1));
        }

        for (int i = 0; i < s.length(); i++) {
            if (lastMap.get(s.charAt(i)) > p2) {
                p2 = lastMap.get(s.charAt(i));
            } else if (i == p2) {
                listResult.add(p2 - p1 + 1);
                p1 = i + 1;
                if (p1 < s.length()) {
                    p2 = lastMap.get(s.charAt(p1));
                }
            }
        }

        
        return listResult;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(solution.partitionLabels("eccbbbbdec"));
    }
}