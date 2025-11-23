from typing import List
from collections import defaultdict


class Solution:
    def maxBalancedSubarray(self, nums: List[int]) -> int:
        xor = 0
        diff = 0
        result = 0

        hashmap = defaultdict(int)
        hashmap[(xor, diff)] = 0

        for index, num in enumerate(nums):
            if num % 2 == 0:
                diff -= 1
            else:
                diff += 1

            xor = num ^ xor

            if (xor, diff) in hashmap:
                result = max(result, index - hashmap[(xor, diff)])

            hashmap[(xor, diff)] = index

        return result
    
s = Solution()

res = s.maxBalancedSubarray([3,1,3,2,0])
print(res)
        