class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        
        int original = image[sr][sc];
        
        // If same color, no need to process
        if(original == color) return image;
        
        dfs(image, sr, sc, original, color);
        
        return image;
    }
    
    private void dfs(int[][] image, int r, int c, int original, int color) {
        
        // boundary check
        if(r < 0 || c < 0 || r >= image.length || c >= image[0].length)
            return;
        
        // if color not same
        if(image[r][c] != original)
            return;
        
        // change color
        image[r][c] = color;
        
        // explore 4 directions
        dfs(image, r+1, c, original, color);
        dfs(image, r-1, c, original, color);
        dfs(image, r, c+1, original, color);
        dfs(image, r, c-1, original, color);
    }
}