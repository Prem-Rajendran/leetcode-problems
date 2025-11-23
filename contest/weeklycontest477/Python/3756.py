from typing import List


class Solution:
    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        MOD = 10**9 + 7
        N = len(s)

        index = [0] * (N+1)
        value = [0] * (N+1)
        total = [0] * (N+1)
        pow10 = [1] * (N+1)

        result = []

        for i in range(1, N+1):
            pow10[i] = (pow10[i-1] * 10) % MOD
        
        c = 0
        for i in range(N):
            digit = ord(s[i]) - 48

            if digit != 0:
                c+=1
                value[c] = (value[c-1] * 10 + digit) % MOD
                total[c] = total[c-1] + digit

            index[i+1] = c

        for left, right in queries:
            l = index[left]
            r = index[right + 1]

            if l == r:
                result.append(0)
                continue

            length = r - l
            num = (value[r] - (value[l] * pow10[length])) % MOD
            sum_of_digits = total[r] - total[l]

            result.append((num * sum_of_digits) * MOD)
        
        return result
    
s = Solution()
res = s.sumAndMultiply("9876543210", [[0,9]])
print(res)