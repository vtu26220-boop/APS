import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        
        // Assign unique group for -1
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }
        
        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();
        
        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];
        
        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            groupGraph.add(new ArrayList<>());
        }
        
        // Build graphs
        for (int i = 0; i < n; i++) {
            for (int before : beforeItems.get(i)) {
                
                itemGraph.get(before).add(i);
                itemIndegree[i]++;
                
                if (group[i] != group[before]) {
                    groupGraph.get(group[before]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }
        
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);
        if (groupOrder.size() == 0) return new int[0];
        
        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        if (itemOrder.size() == 0) return new int[0];
        
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        
        for (int item : itemOrder) {
            groupToItems.putIfAbsent(group[item], new ArrayList<>());
            groupToItems.get(group[item]).add(item);
        }
        
        List<Integer> result = new ArrayList<>();
        
        for (int g : groupOrder) {
            if (groupToItems.containsKey(g)) {
                result.addAll(groupToItems.get(g));
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int n) {
        
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            
            for (int next : graph.get(node)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        
        return result.size() == n ? result : new ArrayList<>();
    }
}