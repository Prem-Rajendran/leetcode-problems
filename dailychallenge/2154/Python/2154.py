from typing import List


class Solution:
    def findFinalValue(self, nums: List[int], original: int) -> int:
        hashset = set(nums)

        while original in hashset:
            original *= 2

        return original
    
s = Solution()
res = s.findFinalValue([5,3,6,1,12], 3)
print(res)