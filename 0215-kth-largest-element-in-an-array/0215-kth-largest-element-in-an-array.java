import java.util.PriorityQueue;

class Solution {

    public int findKthLargest(int[] nums, int k) {

        // Min Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int num : nums) {
            pq.add(num);

            // keep only k largest elements
            if (pq.size() > k) {
                pq.poll();   // remove smallest
            }
        }

        // kth largest element
        return pq.peek();
    }
} 