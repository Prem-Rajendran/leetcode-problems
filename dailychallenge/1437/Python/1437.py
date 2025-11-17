from typing import List


class Solution:
    def kLengthApart(self, nums: List[int], k: int) -> bool:
        left = -1

        for right in range(len(nums)):
            if nums[right] == 1:
                if left != -1 and right - left - 1 < k:
                    return False
                left = right

        return True

s = Solution()

res = s.kLengthApart([1,0,0,0,1,0,0,1], 2)
print(res)