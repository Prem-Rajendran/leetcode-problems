class Solution:
    def maxOperations(self, s: str) -> int:
        index = 0
        operations = 0
        ones_count = 0

        while index < len(s):

            if s[index] == "0":
                while s[index] != "1":
                    index += 1
                operations += ones_count

            if s[index] == "1":
                ones_count += 1

            index += 1
            
        return operations

            

s = Solution()

print("Result 1", s.maxOperations("1001101"))
print("Result 2", s.maxOperations("00111"))
print("Result 3", s.maxOperations("100110001"))