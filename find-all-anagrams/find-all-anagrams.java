
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int pLength = p.length();

        if (s.length() < pLength) {
            return Collections.emptyList();
        }

        int endPos = pLength;
        List<Integer> result = new LinkedList<>();
        
        HashMap<Character, Integer> pCounter = new HashMap<>();
        for (char c : p.toCharArray()) {
            pCounter.putIfAbsent(c, 0);
            pCounter.put(c, pCounter.get(c) + 1);
        }

        HashMap<Character, Integer> sCounter = new HashMap<>();
        for (int i = 0; i < endPos; i++) {
            Character character = s.charAt(i);
            sCounter.putIfAbsent(character, 0);
            sCounter.put(character, sCounter.get(character) + 1);
        }

        if (areEqual(pCounter, sCounter)) {
            result.add(0);
        }

        endPos++;

        while (endPos <= s.length()) {
            int startPos = endPos - pLength;
            Character removedChar = s.charAt(startPos - 1);
            sCounter.put(removedChar, sCounter.get(removedChar) - 1);

            Character newChar = s.charAt(endPos - 1);
            sCounter.putIfAbsent(newChar, 0);
            sCounter.put(newChar, sCounter.get(newChar) + 1);

            if (areEqual(sCounter, pCounter)) result.add(startPos);
            endPos++;            
        }

        return result;
    }

    private boolean areEqual(Map<Character, Integer> first, Map<Character, Integer> second) {
        return first.entrySet().stream()
            .allMatch(e -> e.getValue().equals(second.getOrDefault(e.getKey(), 0)));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> positions = solution.findAnagrams("cbaebabacd", "abc");
        System.out.println(positions);
    }
}