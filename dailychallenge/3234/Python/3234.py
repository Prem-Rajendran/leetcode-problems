# 3234. Count the Number of Substrings With Dominant Ones

class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        length = len(s)

        # Next Zeros Array
        next_zeros = [length] * (length)
        for i in range(length - 2, -1, -1):
            if s[i + 1] == "0":
                next_zeros[i] = i + 1
            else:
                next_zeros[i] = next_zeros[i + 1]
                
        res = 0
        for left in range(length):
            zeros = 1 if s[left] == "0" else 0
            right = left
            
            while zeros**2 <= length:
                next_z = next_zeros[right]
                ones = next_z - left - zeros

                if ones >= zeros**2:
                    res += min(
                        next_z - right,
                        ones - zeros**2 + 1
                    )
                
                right = next_z
                zeros += 1

                if right == length:
                    break

        return res


s = Solution()

res = s.numberOfSubstrings("101101")
print(res)
