import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        int[] inDegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();
        
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }
        
        // Build graph
        for(int[] pre : prerequisites){
            int a = pre[0];
            int b = pre[1];
            
            graph.get(b).add(a);
            inDegree[a]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        // Add courses with 0 in-degree
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        
        int count = 0;
        
        while(!queue.isEmpty()){
            int course = queue.poll();
            count++;
            
            for(int next : graph.get(course)){
                inDegree[next]--;
                if(inDegree[next] == 0){
                    queue.add(next);
                }
            }
        }
        
        return count == numCourses;
    }
}