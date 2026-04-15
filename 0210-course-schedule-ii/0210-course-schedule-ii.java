import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        
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
        
        // Add 0 in-degree courses
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        
        int[] order = new int[numCourses];
        int index = 0;
        
        while(!queue.isEmpty()){
            int course = queue.poll();
            order[index++] = course;
            
            for(int next : graph.get(course)){
                inDegree[next]--;
                if(inDegree[next] == 0){
                    queue.add(next);
                }
            }
        }
        
        if(index == numCourses){
            return order;
        }
        
        return new int[0];
    }
}