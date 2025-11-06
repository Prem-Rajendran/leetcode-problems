package com.dailychallenge.problem3607;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    private static void testCase1() {
        int[][] connections = {
                {1,2},
                {2,3},
                {3,4},
                {4,5},
        };

        int[][] queries = {
                {1,3},
                {2,1},
                {1,1},
                {2,2},
                {1,2},
        };

        int[] result = processQueries(5, connections, queries);

        System.out.println("Test Case 1");
        for (int val : result){
            System.out.print(val + ", ");
        }
        System.out.println();
    }

    private static void testCase2() {
        int[][] connections = {};

        int[][] queries = {
                {1,1},{2,1},{1,1}
        };

        int[] result = processQueries(5, connections, queries);

        System.out.println("Test Case 2");
        for (int val : result){
            System.out.print(val + ", ");
        }
        System.out.println();
    }

    private static void testCase3() {
        int[][] connections = {
                {2,1}
        };

        int[][] queries = {
                {2,1},{1,2},{2,1},{1,1},{1,2},{1,1},{1,1},{2,1},{2,2}
        };

        int[] result = processQueries(2, connections, queries);

        System.out.println("Test Case 3");
        for (int val : result){
            System.out.print(val + ", ");
        }
        System.out.println();
    }

    public static int[] processQueries(int c, int[][] connections, int[][] queries) {
        Map<Integer, TreeSet<Integer>> groupedComponents = new HashMap<>();
        Map<Integer, Integer> groupIdDict = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        var adjList = createAdjacencyMap(connections);

        for (int i = 1; i < c + 1; i++) {
            groupConnectedComponents(adjList, groupedComponents, groupIdDict, visited, i, i);
        }

        List<Integer> result = new ArrayList<>();
        for (int[] query : queries) {
            // Resolve
            if (query[0] == 1){
                if (visited.contains(query[1]))
                    result.add(query[1]);
                else {
                    var id = groupIdDict.get(query[1]);
                    var neighbours = groupedComponents.get(id);

                    boolean resolved = false;

                    while (!neighbours.isEmpty()) {
                        var node = neighbours.pollFirst();
                        if (visited.contains(node)){
                            resolved = true;
                            result.add(node);
                            neighbours.add(node);
                            break;
                        }
                    }

                    if (!resolved) result.add(-1);
                }
            }
            // Take it offline
            else {
                visited.remove(query[1]);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void groupConnectedComponents(Map<Integer, List<Integer>> adjList, Map<Integer, TreeSet<Integer>> groupedComponents, Map<Integer, Integer> groupIdDict, Set<Integer> visited, int node, int groupId){
        if (visited.contains(node))
            return;

        visited.add(node);
        var list = groupedComponents.getOrDefault(groupId, new TreeSet<>());
        list.add(node);
        groupedComponents.put(groupId, list);
        groupIdDict.put(node, groupId);

        for (int neighbours : adjList.getOrDefault(node, new ArrayList<>())){
            groupConnectedComponents(adjList, groupedComponents, groupIdDict, visited, neighbours, groupId);
        }
    }

    private static Map<Integer, List<Integer>> createAdjacencyMap(int[][] connections) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int[] adj : connections) {

            if (adjList.containsKey(adj[0])) {
                adjList.get(adj[0]).add(adj[1]);
            }
            else {
                List<Integer> list = new ArrayList<>();
                list.add(adj[1]);
                adjList.put(adj[0], list);
            }

            if (adjList.containsKey(adj[1])){
                adjList.get(adj[1]).add(adj[0]);
            }
            else {
                List<Integer> list = new ArrayList<>();
                list.add(adj[0]);
                adjList.put(adj[1], list);
            }
        }

        return adjList;
    }
}
