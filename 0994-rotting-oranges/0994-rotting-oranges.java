import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        int minutes = 0;
        
        // Count fresh oranges and add rotten oranges to queue
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 2){
                    queue.add(new int[]{i, j});
                }
                else if(grid[i][j] == 1){
                    fresh++;
                }
            }
        }
        
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        
        // BFS
        while(!queue.isEmpty() && fresh > 0){
            
            int size = queue.size();
            
            for(int i = 0; i < size; i++){
                int[] current = queue.poll();
                
                for(int[] dir : directions){
                    int r = current[0] + dir[0];
                    int c = current[1] + dir[1];
                    
                    if(r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1){
                        grid[r][c] = 2;
                        queue.add(new int[]{r, c});
                        fresh--;
                    }
                }
            }
            
            minutes++;
        }
        
        return fresh == 0 ? minutes : -1;
    }
}