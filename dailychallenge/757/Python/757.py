from typing import List


class Solution:
    def intersectionSizeTwo(self, intervals: List[List[int]]) -> int:
        
        res = 0
        intervals.sort(key=lambda i: (i[1], -i[0]))
        
        p1, p2 = -1, - 1
        
        for left, right in intervals:
            if p2 < left:
                res += 2
                p1, p2 = right - 1, right

            elif p1 < left:
                res += 1
                p1, p2 = p2, right

        return res

s = Solution()
res = s.intersectionSizeTwo([[1,3],[1,7],[2,4],[3,7],[5,6],[8,9]])
print("result: ", res)