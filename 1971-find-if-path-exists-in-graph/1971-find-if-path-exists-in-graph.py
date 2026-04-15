from collections import defaultdict, deque

class Solution:
    def validPath(self, n, edges, source, destination):
        
        # Create adjacency list
        graph = defaultdict(list)
        
        for u, v in edges:
            graph[u].append(v)
            graph[v].append(u)
        
        # BFS
        visited = set()
        queue = deque([source])
        
        while queue:
            node = queue.popleft()
            
            if node == destination:
                return True
            
            if node not in visited:
                visited.add(node)
                queue.extend(graph[node])
        
        return False