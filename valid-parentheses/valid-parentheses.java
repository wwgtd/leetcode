
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

class Solution {
    private final Set<Character> openings = Set.of('(', '[', '{');
    private final Set<Character> closings = Set.of(')', ']', '}');

    public boolean isValid(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (Set.of('(', '[', '{').contains(s.charAt(i))) {
                deque.addFirst(s.charAt(i));
                continue;
            }

            if (Set.of(')', ']', '}').contains(s.charAt(i))) {
                Character open = deque.pollFirst();
                if (open == null) return false;
                switch(s.charAt(i)) {
                    case ')' -> {
                        if (open != '(') return false;
                    }
                    case '}' -> {
                        if (open != '{') return false;
                    }
                    case ']' -> {
                        if (open != '[') return false;
                    }
                }
            }
        }

        return deque.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("([])"));
    }
    
}