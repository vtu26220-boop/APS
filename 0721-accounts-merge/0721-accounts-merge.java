import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();
        
        // Build graph
        for(List<String> account : accounts) {
            String name = account.get(0);
            
            for(int i = 1; i < account.size(); i++) {
                graph.putIfAbsent(account.get(i), new HashSet<>());
                nameMap.put(account.get(i), name);
                
                if(i == 1) continue;
                
                graph.get(account.get(i)).add(account.get(i - 1));
                graph.get(account.get(i - 1)).add(account.get(i));
            }
        }
        
        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        
        // DFS
        for(String email : graph.keySet()) {
            if(!visited.contains(email)) {
                List<String> temp = new ArrayList<>();
                dfs(email, graph, visited, temp);
                
                Collections.sort(temp);
                temp.add(0, nameMap.get(email));
                result.add(temp);
            }
        }
        
        return result;
    }
    
    private void dfs(String email, Map<String, Set<String>> graph, 
                     Set<String> visited, List<String> temp) {
        
        visited.add(email);
        temp.add(email);
        
        for(String next : graph.get(email)) {
            if(!visited.contains(next)) {
                dfs(next, graph, visited, temp);
            }
        }
    }
}