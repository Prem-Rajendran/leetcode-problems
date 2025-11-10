package com.dailychallenge.problem3542;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        testCase1();
        testCase2();
    }

    private static void testCase2() {
        int[] nums = {1,2,1,2,1,2};
        var result = minOperations(nums);
        System.out.println("Result: " + result);
    }

    private static void testCase1() {
        int[] nums = {1, 0, 3, 1, 2, 0, 1, 1};
        var result = minOperations(nums);
        System.out.println("Result: " + result);
    }

    public static int minOperations(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>((Integer a, Integer b) -> b - a);
        int operations = 0;

        for (int i = 0; i < nums.length; i++) {

            if (set.isEmpty() && nums[i] != 0){
                set.add(nums[i]);
                continue;
            }

            while (!set.isEmpty() && set.first() > nums[i]){
                set.pollFirst();
                operations++;
            }

            if (nums[i] != 0){
                set.add(nums[i]);
            }
        }

        operations += set.size();

        return operations;
    }
}
