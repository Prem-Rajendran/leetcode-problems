package com.dailychallenge.problem3318;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        testCase1();
    }

    private static void printResult(int[] nums){
        System.out.println("Result:\n");
        for (int n : nums){
            System.out.print(n + ", ");
        }
        System.out.println();
    }

    private static void testCase1() {
        var result = findXSum(new int[] { 1,1,2,2,3,4,2,3 }, 6, 2);
        printResult(result);
    }

    public static int[] findXSum(int[] nums, int k, int x) {
        Map<Integer, Integer> map = new HashMap<>();

        int n = nums.length;
        int start = 0;
        int end = k;

        int[] result = new int[n - k + 1];

        for (int i = 0; i < end; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        result[start] = calculate(map, x);

        while (end < nums.length) {
            map.put(nums[start], map.getOrDefault(nums[start], 0) - 1);
            map.put(nums[end], map.getOrDefault(nums[end], 0) + 1);
            start++;
            end++;
            result[start] = calculate(map, x);
        }
        return result;
    }

    private static int calculate(Map<Integer, Integer> map, int x) {
        Comparator<Integer[]> comparator = (a, b) -> {
            if (a[1].equals(b[1])){
                return b[0] - a[0];
            }
            else{
                return b[1] - a[1];
            }
        };

        List<Integer[]> queue = new ArrayList<>();

        for (var keyValue : map.entrySet()){
            queue.add(new Integer[] {keyValue.getKey(), keyValue.getValue()});
        }

        queue.sort(comparator);

        int result = 0;
        for (int i = 0; i < x && !queue.isEmpty() && i < queue.size(); i++) {
            var val = queue.get(i);
            result += val[0] * val[1];
        }

        return result;
    }
}
