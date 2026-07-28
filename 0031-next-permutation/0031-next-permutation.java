class Solution {
    public void nextPermutation(int[] nums) {

        int pivot = -1;
        int len = nums.length;

        // Step 1: Find pivot 🔍
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }

        // If no pivot exists, reverse the entire array 🔄
        if (pivot == -1) {
            reverse(nums, 0, len - 1);
            return;
        }

        // Step 2: Find the rightmost element greater than pivot ➕
        for (int i = len - 1; i > pivot; i--) {
            if (nums[pivot] < nums[i]) {
                swap(nums, pivot, i);
                break;
            }
        }

        // Step 3: Reverse the suffix to get the smallest arrangement ✨
        reverse(nums, pivot + 1, len - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}