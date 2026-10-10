class Solution {
    public boolean validDigit(int n, int x) {
        String s = String.valueOf(Math.abs(n));
        char digit = (char)(x + '0');

        // must contain x
        if (s.indexOf(digit) == -1) {
            return false;
        }

        // should not start with x
        if (s.charAt(0) == digit) {
            return false;
        }

        return true;
    }
}