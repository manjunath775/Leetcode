class Solution:
    def searchInsert(self, a: List[int], target: int) -> int:
        for i in range(len(a)):
            if(a[i]>=target):
                return i
        return len(a)