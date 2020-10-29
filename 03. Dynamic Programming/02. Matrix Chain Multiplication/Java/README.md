# Matrix Chain Multiplication

Given a chain `(A1 , A2 , . . . , An)` of n matrices, where for `i = 1, 2, . . . , n`,
matrix `Ai` has dimension `p[i - 1]` \* `p[i]` , fully parenthesize the product `A1 A2 An`
in a way that minimizes the number of scalar multiplications required.

Note that in the matrix-chain multiplication problem, we are not actually multiplying matrices.
Our goal is only to determine an order for multiplying matrices that has the lowest cost.

Example:

```
p = [30, 35, 15, 5, 10, 20, 25]
ans = (( A1 ( A2 A3 )) (( A4 A5) A6 ))
```
