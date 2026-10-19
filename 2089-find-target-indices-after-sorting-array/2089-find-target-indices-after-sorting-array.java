class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        int count = 0;
        int start = 0;
        for(int num : nums) {
            if(num == target) {
                count++;
            }
            if(num < target) {
                start++;
            }
        }
        while(count-- != 0) {
            res.add(start++);
        }
        return res;
    }
}