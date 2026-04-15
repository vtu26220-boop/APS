import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        
        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];
        
        for(int i = 0; i < n; i++){
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }
        
        // Build graphs
        for(int[] e : redEdges){
            redGraph[e[0]].add(e[1]);
        }
        
        for(int[] e : blueEdges){
            blueGraph[e[0]].add(e[1]);
        }
        
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        
        boolean[][] visited = new boolean[n][2];
        
        Queue<int[]> queue = new LinkedList<>();
        
        // node, color, distance
        queue.offer(new int[]{0, 0, 0}); // red
        queue.offer(new int[]{0, 1, 0}); // blue
        
        visited[0][0] = true;
        visited[0][1] = true;
        
        while(!queue.isEmpty()){
            
            int[] current = queue.poll();
            int node = current[0];
            int color = current[1];
            int dist = current[2];
            
            if(answer[node] == -1){
                answer[node] = dist;
            }
            
            if(color == 0){
                // last was red, go blue
                for(int next : blueGraph[node]){
                    if(!visited[next][1]){
                        visited[next][1] = true;
                        queue.offer(new int[]{next, 1, dist + 1});
                    }
                }
            } else {
                // last was blue, go red
                for(int next : redGraph[node]){
                    if(!visited[next][0]){
                        visited[next][0] = true;
                        queue.offer(new int[]{next, 0, dist + 1});
                    }
                }
            }
        }
        
        return answer;
    }
}