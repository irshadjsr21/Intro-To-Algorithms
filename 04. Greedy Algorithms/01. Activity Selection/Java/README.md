# Activity Selection

We have a set `S = {a1, a2, . . . , an}` of `n` proposed activities that wish to
use a resource, such as a lecture hall, which can serve only one activity at a time.

Each activity `ai` has a start time `si` and a finish time `fi` , where `0 <= si < fi < INFINITY`.
If selected, activity `ai` takes place during the half-open time interval `[si, fi)`.
Activities `ai` and `aj` are compatible if the intervals `[si, fi)` and `[sj, fj)` do not overlap.
That is, `ai` and `aj` are compatible if `si >= fj` or `sj >= fi`.

In the activity-selection problem, we wish to select a maximum-size subset of mutually compatible activities.
We assume that the activities are sorted in monotonically increasing order of finish time.

Example:

```
s = [ 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 1, 2 ]
f = [ 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16 ]

Ans = [1 , 4, 8, 11]
```
