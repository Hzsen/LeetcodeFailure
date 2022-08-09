# Dynamic Programming

Dynamic Programming (DP) is a programming paradigm that can systematically and efficiently explore all possible solutions to a problem. As such, it is capable of solving a wide variety of problems that often have the following characteristics:

1. The problem can be broken down into "overlapping subproblems" - smaller versions of the original problem that are re-used multiple times.
2. The problem has an "optimal substructure" - an optimal solution can be formed from optimal solutions to the overlapping subproblems of the original problem.

If you wanted to find the $n^{th}$ Fibonacci number $F(n)$, you can break it down into smaller **subproblems** - find $F(n - 1)$ and $F(n - 2)$ instead. 

Then, adding the solutions to these subproblems together gives the answer to the original question, $F(n-1) + F(n-2) = F(n)F(n−1)+F(n−2)=F(n)$, which means the problem has **optimal substructure**, since a solution F(n)*F*(*n*) to the original problem can be formed from the solutions to the subproblems. These subproblems are also **overlapping** - for example, we would need $F(4)$ to calculate both $F(5)$ and $F(6)$.



### Bottom-up (Tabulation)

Bottom-up is implemented with iteration and starts at the base cases. Let's use the Fibonacci sequence as an example again. The base cases for the Fibonacci sequence are $F(0) = 0$ and $F(1) = 1$. With bottom-up, we would use these base cases to calculate $F(2)$, and then use that result to calculate $F(3)$, and so on all the way up to $F(n)$.

### Top-down (Memoization)

Top-down is implemented with recursion and made efficient with memoization. If we wanted to find the $n^{th}$ Fibonacci number F(n)F(n), we try to compute this by finding $F(n - 1)$ and $F(n - 2)$. This defines a recursive pattern that will continue on until we reach the base cases $F(0) = F(1) = 1$. The problem with just implementing it recursively is that there is a ton of unnecessary repeated computation. Take a look at the recursion tree if we were to find $F(5)$:

![img](https://leetcode.com/explore/learn/card/Figures/DP1/C1A2_1.png)

Notice that we need to calculate $F(2)$ three times. This might not seem like a big deal, but if we were to calculate $F(6)$, this **entire image** would be only one child of the root. Imagine if we wanted to find $F(100)$- the amount of computation is exponential and will quickly explode. The solution to this is to **memoize** results.

***<u><u>memoizing a result means to store the result of a function call, usually in a hashmap or an array, so that when the same function call is made again, we can simply return the memoized result instead of recalculating the result.***</u>

After we calculate $F(2)$, let's store it somewhere, so in the future, whenever we need to find $F(2)$, we can just refer to the value we already calculated instead of having to go through the entire tree again. 