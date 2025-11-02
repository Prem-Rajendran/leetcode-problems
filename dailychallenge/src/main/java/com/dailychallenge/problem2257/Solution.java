package com.dailychallenge.problem2257;

public class Solution {

    private static final int GUARD_H = 1;
    private static final int GUARD_V = 2;
    private static final int WALL = 3;
    private static final int EMPTY = 0;

    private static int count = 0;

    private static void markWalls(int[][] grid, int[][] walls) {
        for (int[] w : walls) {
            int wX = w[0], wY = w[1];
            grid[wX][wY] = WALL;
        }
    }

    private  static  void markGuardHorizontalPath(int[][] grid, int[][] guards) {
        for (int[] g : guards) {
            int gX = g[0];
            int gY = g[1];

            for (int i = gY; i < grid[0].length; i++) {
                if (grid[gX][i] == WALL || grid[gX][i] == GUARD_H)
                    break;
                count --;
                grid[gX][i] = GUARD_H;
            }

            for (int i = gY - 1; i >= 0; i--) {
                if (grid[gX][i] == WALL || grid[gX][i] == GUARD_H)
                    break;
                count --;
                grid[gX][i] = GUARD_H;
            }

        }
    }

    private  static  void markGuardVerticalPath(int[][] grid, int[][] guards) {
        for (int[] g : guards) {
            int gX = g[0];
            int gY = g[1];

            for (int i = gX; i < grid.length; i++) {
                if (grid[i][gY] == WALL || grid[i][gY] == GUARD_V)
                    break;

                if (grid[i][gY] == EMPTY)
                    count --;
                grid[i][gY] = GUARD_V;
            }

            for (int i = gX - 1; i >= 0; i--) {
                if (grid[i][gY] == WALL || grid[i][gY] == GUARD_V)
                    break;

                if (grid[i][gY] == EMPTY)
                    count --;
                grid[i][gY] = GUARD_V;
            }

        }
    }

    private static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        count = m * n - walls.length;
        markWalls(grid, walls);
        markGuardHorizontalPath(grid, guards);
        markGuardVerticalPath(grid, guards);
        return count;
    }

    public static void main(String[] args) {
        System.out.println("2257. Count Unguarded Cells in the Grid");
        int[][] guards = {{0,0},{1,1},{2,3}};
        int[][] walls = {{0,1},{2,2},{1,4}};
        int val = countUnguarded(4, 6, guards, walls);
        System.out.println("Result: " + val);
    }
}