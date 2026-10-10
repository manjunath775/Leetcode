class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        j=0
        for i in range(len(nums)):
             j=j+nums[i]
        return j%k