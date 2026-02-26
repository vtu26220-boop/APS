class Solution {
    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        return quickSelect(nums, 0, nums.length - 1, target);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        int pivot = nums[right];
        int p = left;

        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, p);
                p++;
            }
        }

        swap(nums, p, right);

        if (p == k) return nums[p];
        else if (p < k) return quickSelect(nums, p + 1, right, k);
        else return quickSelect(nums, left, p - 1, k);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}