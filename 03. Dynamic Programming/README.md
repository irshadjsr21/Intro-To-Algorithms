# Dynamic Programming

When developing a dynamic-programming algorithm, we follow a sequence of
four steps:

1. Characterize the structure of an optimal solution.
2. Recursively define the value of an optimal solution.
3. Compute the value of an optimal solution, typically in a bottom-up fashion.
4. Construct an optimal solution from computed information.

This method is applicable when:

## The problem is to find Optimal Solution

The problem should be to find an optimal solution of the problem among many
possible solutions. For example, cutting the rod in such a way to maximize the
revenue. In the rod cutting example, we can cut the rod in any way possible, but
the problem requires us to cut it in such a way that the revenue is maximized.
Hence we need to find the optimal solution.

## The problem has Optimal Sub structure

After dividing the problem into sub problems (say `n` subproblems), it should be
in such a way that if all the solutions of `n` sub problems are optimized in their
own way, then the main problem will automatically be optimized.

## All the sub problems should be independent of each other

After dividing the problem into sub problems (say `n` subproblems), it should be
in such a way that the solution of one subproblem should not affect the solution
of other subproblems. Hence, all the subproblems are independent of each other.

## Overlapping subproblems

When the same subproblems generated again and again while solving the main problem,
we call these subproblems as Overlapping subproblems.

If we implement such a problem in recursive manner, it will solve the overlapping
subproblems again and again. We can prevent these recomputations by using Dynamic
Programming.
