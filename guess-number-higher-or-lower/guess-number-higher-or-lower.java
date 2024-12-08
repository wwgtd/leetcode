/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution {
    public int guessNumber(int n) {
        int p1 = 1, p2 = n;
        
        while (p1 <= p2) {
            int pivot = p1 + (p2 - p1) / 2;
            int guessResult = guess(pivot);

            if (guessResult == 0) {
                return pivot;
            }

            if (guessResult == 1) {
                p1 = pivot + 1;
            } else {
                p2 = pivot;
            }
        }

        return -1;
    }

    private int search(int p1, int p2) {
        if (p1 >= p2) {
            return -1;
        }

        int pivot = (p1 + p2) / 2;

        int guessResult = guess(pivot);

        if (guessResult == 0) {
            return pivot;
        }

        if (guessResult == 1) {
            return search(pivot + 1, p2);
        }

        return search(p1, pivot);
    }

    public int guess(int num) {
        return 0;
    }
}