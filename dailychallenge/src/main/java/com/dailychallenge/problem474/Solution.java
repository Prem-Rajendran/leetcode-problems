package com.dailychallenge.problem474;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
    }

    public static void testCase1() {
        String[] strs = {"10","0001","111001","1","0"};
        int result = findMaxForm(strs, 5, 3);
        System.out.println("Result: " + result);
    }

    public static void testCase2() {
        String[] strs = {"10","0","1"};
        int result = findMaxForm(strs, 1, 1);
        System.out.println("Result: " + result);
    }

    public static void testCase3() {
        String[] strs = {"10","0","1","100100101","0","0","0","000000","11111","1"};
        int result = findMaxForm(strs, 6, 1);
        System.out.println("Result: " + result);
    }

    public static void testCase4() {
        String[] strs = {"101000000","1100001010","11101000","011010110","0010001","0011","0111101111"};
        int result = findMaxForm(strs, 10, 11);
        System.out.println("Result: " + result);
    }

    public static int findMaxForm(String[] S, int M, int N) {
        int[][] dp = new int[M+1][N+1];
        for (String str : S) {
            int zeros = 0, ones = 0;
            for (char c : str.toCharArray())
                if (c == '0') zeros++;
                else ones++;
            for (int i = M; i >= zeros; i--)
                for (int j = N; j >= ones; j--)
                    dp[i][j] = Math.max(dp[i][j], dp[i-zeros][j-ones] + 1);
        }
        return dp[M][N];
    }
}
