class Solution {
    public boolean rotateString(String s, String goal) {
        String copy = s;
        
        do { 
            s = s.substring(1) + s.charAt(0);
            if (s.equals(goal)) return true;
        } while (!copy.equals(s));

        return false;
    }
}