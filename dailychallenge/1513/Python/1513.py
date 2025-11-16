class Solution:
    def numSub(self, s: str) -> int:
        res = 0
        substrings = s.split("0")

        for substring in substrings:
            length = len(substring)
            if length == 0:
                continue
            
            res += (length * 0.5 * (length + 1))
        return int(res % (10**9 + 7))

s = Solution()
res = s.numSub("0110111")
print("test1", res)

res = s.numSub("101")
print("test2", res)

res = s.numSub("111111")
print("test3", res)