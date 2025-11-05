package com.dailychallenge.problem3321;

import java.util.*;

public class Solution {

    private static Comparator<Integer[]> highestFrequency = (Integer[] a, Integer[] b) -> {
      if (Objects.equals(a[0], b[0]))
          return Integer.compare(b[1], a[1]);
      return Integer.compare(b[0], a[0]);
    };

    private static Comparator<Integer[]> lowestFrequency = (Integer[] a, Integer[] b) -> {
        if (Objects.equals(a[0], b[0]))
            return Integer.compare(a[1], b[1]);
        return Integer.compare(a[0], b[0]);
    };

    public static void main(String[] args) {
        int[] nums = {10, 7, 10, 6, 9, 8, 7, 9};
        long[] res = findXSum(nums,4, 3);

        for (var val : res) {
            System.out.println(val);
        }
    }

    public static long[] findXSum(int[] nums, int k, int x) {
        long[] result = new long[nums.length - k + 1];
        int start = 0;
        var map = new HashMap<Integer, Integer>();
        TreeSet<Integer[]> remainder = new TreeSet<>(highestFrequency);
        TreeSet<Integer[]> topElements = new TreeSet<>(lowestFrequency);

        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum = updateMap(nums[i], 1, map, remainder, topElements, x, sum);
        }

        result[start] = sum;

        int end = k;
        while (end < nums.length) {
            sum = updateMap(nums[start], -1, map, remainder, topElements, x, sum);
            sum = updateMap(nums[end], 1, map, remainder, topElements, x, sum);
            start ++;
            end ++;
            result[start] = sum;
        }

        return result;
    }

    private static long updateMap(int val, int freq, Map<Integer, Integer> map, TreeSet<Integer[]> remainder, TreeSet<Integer[]> topElements, int limit, long currentSum){
        if (map.containsKey(val)){
            int oldFreq = map.get(val);
            var oldItem = new Integer[]{oldFreq, val};

            if (topElements.remove(oldItem)){
                currentSum -= ((long) val * oldFreq);
            }

            remainder.remove(oldItem);
            map.remove(val);
            freq += oldFreq;
        }

        map.put(val, freq);
        var newItem = new Integer[]{freq, val};

        if (topElements.size() == limit) {
            topElements.add(newItem);
            currentSum += ((long) newItem[0] * newItem[1]);
            var top = topElements.pollFirst();
            if (top != null){
                currentSum -= ((long) top[0] * top[1]);
                remainder.add(top);
            }
        }
        else {
            remainder.add(newItem);
            var top = remainder.pollFirst();
            if (top != null) {
                currentSum += ((long) top[0] * top[1]);
                topElements.add(top);
            }
        }

        return currentSum;
    }
}
