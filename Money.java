/**
 * Creates a Money class to manipulate money.
 * @author Parth Thakur
 * @version 1
 */
public class Money {
    /**
     * Creates a method that recursively counts inputted cents and equates to dollars.
     * @param cents Integer array representing indvidiual cent value.
     * @return Double representing dollar amount total.
     */
    public static double countTotal(int[] cents) {
        if (cents.length == 0) {
            return 0;
        }
        if (cents.length == 1) {
            return cents[0] / 100.0;
        } else {
            return cents[0] / 100.0 + countTotal(arrayShorten(cents));
        }
    }
    private static int[] arrayShorten(int[] x) {
        int[] temp = new int[x.length - 1];
        for (int i = 1; i < x.length; i++) {
            temp[i - 1] = x[i];
        }
        return temp;
    }
    /**
     * Creates a method that calculates the difference between a dollar amount and an array of cents.
     * @param dollars Represents dollar value required for purchase.
     * @param cents An array of individual cent value.
     * @return Double that represents the difference between the two quantities.
     */
    public static double findDifference(double dollars, int[] cents) {
        return dollars - countTotal(cents);
    }
    /**
     * Creates a method that makes change when a value is given.
     * @param num The value given to make change for
     * @param coins Represents the integer array consisting of coins for change making
     * @return Returns an integer that represents the least amount of change needed using greedy method.
     */
    public static int makeChange(int num, int[] coins) {
        if (num == 0) {
            return 0;
        } else {
            return num / coins[0] + makeChange(num % coins[0], arrayShorten(coins));
        }
    }
    /**
     * Creates a method that returns change for a value with standard amount of coins.
     * @param num Represents the integer value that we need to make change for.
     * @return Returns the amount of coins needed to make change.
     */
    public static int makeChange(int num) {
        int[] coins = {25, 10, 5, 1};
        return makeChange(num, coins);
    }
}