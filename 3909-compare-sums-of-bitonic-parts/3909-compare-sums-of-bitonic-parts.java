class Solution {
    public int compareBitonicSums(int[] nums) {
        int n = nums.length;
        int peak = 0;

        // find actual peak (where increasing stops)
        while (peak + 1 < n && nums[peak] < nums[peak + 1]) {
            peak++;
        }

        long leftSum = 0;
        long rightSum = 0;

        // ascending part: 0 to peak
        for (int i = 0; i <= peak; i++) {
            leftSum += nums[i];
        }

        // descending part: peak to n-1
        for (int i = peak; i < n; i++) {
            rightSum += nums[i];
        }

        if (leftSum > rightSum) {
            return 0;
        } else if (rightSum > leftSum) {
            return 1;
        } else {
            return -1;
        }
    }
}