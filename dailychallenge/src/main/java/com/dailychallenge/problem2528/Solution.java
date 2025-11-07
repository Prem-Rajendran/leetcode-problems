package com.dailychallenge.problem2528;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        //testCast1();
        testCast2();
    }

    private static void testCast1() {
        var stations = new int[] {1,2,4,5,0};
        var result = maxPower(stations, 1, 2);
        System.out.println("TestCast 1 - Result: " + result);
    }

    private static void testCast2() {
        var stations = new int[] {2,10,12,3};
        var result = maxPower(stations, 0, 14);
        System.out.println("TestCast 1 - Result: " + result);
    }

    public static long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] diff = new long[n  + 1];

        for (int i = 0; i < n; i++) {
            int left = Math.max(0, i - r);
            int right = Math.min(n, i + r + 1);

            diff[left] += stations[i];
            diff[right] -= stations[i];
        }

        long low = Arrays.stream(stations).min().orElse(0);
        long high = Arrays.stream(stations).sum() + (long) k;

        long result = low;
        long[] diffCopy = diff.clone();
        while (low <= high) {
            long target = (low + high)/2;

            if (canAchieve(target, diffCopy, k, r)){
                result = target;
                low = target + 1;
            }
            else {
                high = target - 1;
            }
        }

        return result;
    }

    private static boolean canAchieve(long target, long[] diff, long k, int r) {
        long currentPower = 0;
        int n = diff.length - 1;
        for (int i = 0; i < n; i++) {
            currentPower += diff[i];
            if (currentPower < target) {
                long additional = target - currentPower;
                if (additional > k)
                    return false;

                k -= additional;
                currentPower += additional;
                int right = Math.min(n, i + 2 * r + 1);
                diff[right] -= additional;
            }
        }
        return true;
    }
}
