import java.util.*;

class Solution {
    
    class NodeInfo {
        int row, col, val;
        
        NodeInfo(int r, int c, int v) {
            row = r;
            col = c;
            val = v;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<NodeInfo> list = new ArrayList<>();
        
        dfs(root, 0, 0, list);
        
        // Sort by column, then row, then value
        Collections.sort(list, (a, b) -> {
            if (a.col != b.col)
                return a.col - b.col;
            if (a.row != b.row)
                return a.row - b.row;
            return a.val - b.val;
        });
        
        List<List<Integer>> result = new ArrayList<>();
        
        int prevCol = Integer.MIN_VALUE;
        for (NodeInfo node : list) {
            if (node.col != prevCol) {
                result.add(new ArrayList<>());
                prevCol = node.col;
            }
            result.get(result.size() - 1).add(node.val);
        }
        
        return result;
    }
    
    private void dfs(TreeNode node, int row, int col, List<NodeInfo> list) {
        if (node == null) return;
        
        list.add(new NodeInfo(row, col, node.val));
        
        dfs(node.left, row + 1, col - 1, list);
        dfs(node.right, row + 1, col + 1, list);
    }
}