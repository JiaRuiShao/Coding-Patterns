/*
 * TopologicalSort of a directed graph (a graph with unidirectional edges) is a linear ordering of its vertices
 * such that for every directed edge (U, V) from vertex U to vertex V, U comes before V in the ordering.
 *
 * Given a directed graph, find the topological ordering of its vertices.
 *
 * Input: Vertices=5, Edges=[4, 2], [4, 3], [2, 0], [2, 1], [3, 1]
 * Output: Following are all valid topological sorts for the given graph:
 * 1) 4, 2, 3, 0, 1
 * 2) 4, 3, 2, 0, 1
 * 3) 4, 3, 2, 1, 0
 * 4) 4, 2, 3, 1, 0
 * 5) 4, 2, 0, 3, 1
 */

package topologicalsort;

import java.util.*;

public class TopologicalSort {

    private void findSources(List<List<Integer>> result, List<Integer> temp, Map<Integer, List<Integer>> adjacency, Map<Integer, Integer> freq, Queue<Integer> sources) {

        // base case
        if (temp.size() == freq.size()) {
            // result.add(temp); // CANNOT directly add it -> return null cuz later we will modify the elements in temp
            result.add(new ArrayList<>(temp));
            return;
        }

        if (sources == null) return;

        // recursive rules
        for (int vertex : sources) {
            Queue<Integer> next = new LinkedList<>(sources);
            next.remove(vertex);
            temp.add(vertex);

            // update the freq hashmap to find the new sources
            for (Integer v : adjacency.get(vertex)) {
                freq.put(v, freq.get(v) - 1);
                if (freq.get(v) == 0) { //  new sources
                    next.offer(v);
                }
            }

            findSources(result, temp, adjacency, freq, next);

            // backtrack -- adding back the adjacent vertex freq
            temp.remove((Integer) vertex);
            for (Integer v : adjacency.get(vertex)) {
                freq.put(v, freq.get(v) + 1);
            }
        }
    }

    /**
     * Time: O(V! * E)
     * Space: O(V!)
     *
     * @param vertices the number of vertices
     * @param edges the edges, [edge1, edge2]
     * @return all possible topological sorts
     */
    public List<List<Integer>> topologicalSort(int vertices, int[][] edges) {
        List<List<Integer>> result = new LinkedList<>();
        // corner case
        if (vertices == 0 || edges == null || edges.length == 0) {
            return result;
        }

        // 0 - define two hashmaps, one stores the vertices and its adjacent nodes;
        // the other stores the vertices and the times that vertex is an adjacent node of others
        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();

        // 1 - initialize two hashMaps
        for (int vertex = 0; vertex < vertices; vertex++) {
            adjacency.put(vertex, new LinkedList<Integer>());
            freq.put(vertex, 0);
        }

        // 2 - build two hashmaps using the given input data
        for (int[] edge : edges) {
            // edge[0] - source vertex
            // edge[1] - destination vertex
            adjacency.get(edge[0]).add(edge[1]);
            freq.put(edge[1], freq.get(edge[1]) + 1);
        }

        // 3 - find all source vertices (vertices that have no incoming edges and have only outgoing edges)
        Queue<Integer> sources = new LinkedList<>();
        for (int vertex : freq.keySet()) {
            if (freq.get(vertex) == 0) {
                sources.offer(vertex);
            }
        }

        // 4 - find the next sources recursively
        findSources(result, new LinkedList<Integer>(), adjacency, freq, sources);

        return result;
    }

    public void printLists(List<List<Integer>> results) {
        for (List<Integer> result : results) {
            System.out.println(Arrays.toString(result.toArray()));
        }
    }

    public static void main(String[] args) {
        TopologicalSort graph = new TopologicalSort();
        List<List<Integer>> result = graph.topologicalSort(4,
                new int[][]{new int[]{3, 2}, new int[]{3, 0}, new int[]{2, 0}, new int[]{2, 1}});
        graph.printLists(result);
        System.out.println();

        result = graph.topologicalSort(5, new int[][]{new int[]{4, 2}, new int[]{4, 3}, new int[]{2, 0},
                new int[]{2, 1}, new int[]{3, 1}});
        graph.printLists(result);
        System.out.println();

        /*result = graph.topologicalSort(7, new int[][]{new int[]{6, 4}, new int[]{6, 2}, new int[]{5, 3},
                new int[]{5, 4}, new int[]{3, 0}, new int[]{3, 1}, new int[]{3, 2}, new int[]{4, 1}});
        graph.printLists(result);*/
    }
}

