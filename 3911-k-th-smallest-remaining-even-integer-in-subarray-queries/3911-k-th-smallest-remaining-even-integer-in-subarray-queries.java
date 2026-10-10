import java.util.*;

class Solution {
    public int[] kthRemainingInteger(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];
        
        // Prefix sum for even numbers in the input array
        int[] evenPrefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            evenPrefix[i + 1] = evenPrefix[i] + (nums[i] % 2 == 0 ? 1 : 0);
        }

        for (int i = 0; i < m; i++) {
            int L = queries[i][0];
            int R = queries[i][1];
            long k = queries[i][2];

            // The answer could be up to 2*10^9 + 2*10^5 (approx)
            long low = 1, high = 3_000_000_000L; 
            long res = high;

            while (low <= high) {
                long mid = low + (high - low) / 2;
                
                // Count how many even numbers <= mid exist in total
                long totalEvensUpToMid = mid / 2;
                
                // Count how many even numbers <= mid exist in nums[L...R]
                int countInSubarray = countEvensInRange(nums, evenPrefix, L, R, mid);
                
                long missingCount = totalEvensUpToMid - countInSubarray;

                if (missingCount >= k) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            
            // The binary search finds the smallest mid that satisfies the count.
            // If that mid is odd, the 'k-th even' must be the even number just below it.
            ans[i] = (int) (res % 2 == 0 ? res : res - 1);
            
            // Special Case: If res was odd, we check if decreasing it changes the missingCount.
            // Actually, a simpler way: the result must be even. 
            // If res is odd, res-1 will have the same missingCount.
            if (res % 2 != 0) res--;
            ans[i] = (int) res;
        }
        return ans;
    }

    private int countEvensInRange(int[] nums, int[] evenPrefix, int L, int R, long X) {
        // Binary search to find the last index in nums[L...R] where nums[idx] <= X
        int start = L, end = R;
        int lastIdx = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= X) {
                lastIdx = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        if (lastIdx == -1) return 0;
        return evenPrefix[lastIdx + 1] - evenPrefix[L];
    }
}