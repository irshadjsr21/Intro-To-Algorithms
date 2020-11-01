# Optimal Binary Search Tree

We are given a sequence `K = (k1, k2 . . . . , kn)` of `n` distinct keys in sorted order (so that
`k1 < k2 < . . . . < kn`), and we wish to build a binary search tree from these keys.
For each key `ki` , we have a probability `pi` that a search will be for `ki`. Some
searches may be for values not in K, and so we also have `n + 1` “dummy keys”.
`d0, d1, d2 . . . . , dn` representing values not in K. In particular, `d0` represents all values
less than `k1` , `dn` represents all values greater than `kn` , and for `i = 1, 2, . . . . n-1`,
the dummy key `di` represents all values between `ki` and `k i+1` . For each dummy
key `di` , we have a probability `qi` that a search will correspond to `di`.

For a given set of probabilities, we wish to construct a binary search tree whose
expected search cost is smallest. We call such a tree an optimal binary search tree.

Example:

```
p = [ 0.15, 0.10, 0.05, 0.10, 0.20 ]
q = [ 0.05, 0.10, 0.05, 0.05, 0.05, 0.10 ]

Ans =
      k2
    /   \
   k1   k5
       /
      k4
      /
     k3
```
