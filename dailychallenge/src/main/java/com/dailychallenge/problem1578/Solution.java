package com.dailychallenge.problem1578;

public class Solution {
    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    private static void testCase1(){
        String colors = "abaac";
        int[] neededTime = {1,2,3,4,5};

        int result = minCost(colors, neededTime);
        System.out.println("Testcase 1 - Result: " + result);
    }

    private static void testCase2(){
        String colors = "bbbaaa";
        int[] neededTime = {4,9,3,8,8,9};

        int result = minCost(colors, neededTime);
        System.out.println("Testcase 2 - Result: " + result);
    }

    private static void testCase3(){
        String colors = "aabaa";
        int[] neededTime = {1,2,3,4,1};

        int result = minCost(colors, neededTime);
        System.out.println("Testcase 3 - Result: " + result);
    }


    public static int minCost(String colors, int[] neededTime) {
        var arr = colors.toCharArray();

        int removedTime = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int index = i + 1;

            if (arr[i] != arr[index])
                continue;

            removedTime += Math.min(neededTime[i], neededTime[index]);

            if (neededTime[i] > neededTime[index]){
                int temp = neededTime[i];
                neededTime[i] = neededTime[index];
                neededTime[index] = temp;
            }
        }

        return removedTime;
    }
}
