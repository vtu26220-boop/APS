import java.util.*;

class Solution {
    
    private int[] count;
    private int[] indexes;
    
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        
        count = new int[n];
        indexes = new int[n];
        
        for(int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        
        mergeSort(nums, 0, n - 1);
        
        List<Integer> result = new ArrayList<>();
        for(int c : count) {
            result.add(c);
        }
        
        return result;
    }
    
    private void mergeSort(int[] nums, int left, int right) {
        if(left >= right) return;
        
        int mid = left + (right - left) / 2;
        
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        
        merge(nums, left, mid, right);
    }
    
    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        
        int i = left, j = mid + 1, k = 0;
        
        while(i <= mid && j <= right) {
            if(nums[indexes[i]] <= nums[indexes[j]]) {
                temp[k] = indexes[j];
                j++;
            } else {
                count[indexes[i]] += right - j + 1;
                temp[k] = indexes[i];
                i++;
            }
            k++;
        }
        
        while(i <= mid) {
            temp[k++] = indexes[i++];
        }
        
        while(j <= right) {
            temp[k++] = indexes[j++];
        }
        
        for(int p = 0; p < temp.length; p++) {
            indexes[left + p] = temp[p];
        }
    }
}