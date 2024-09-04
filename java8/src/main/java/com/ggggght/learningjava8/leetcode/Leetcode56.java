package com.ggggght.learningjava8.leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * <pre>
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * </pre>
 *
 */
public class Leetcode56 {
    /**
     * 使用优先队列进行排序对所有的区间进行排序  排序完成之后, 依次遍历第一个元素的第二项, 如果和上一项有重叠, 则更新, 否则就添加当前项
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> q = new PriorityQueue<>(intervals.length, Comparator.comparingInt(o -> o[0]));
        Collections.addAll(q, intervals);
        LinkedList<int[]> res = new LinkedList<>();
        res.add(q.poll());
        while (!q.isEmpty()) {
            var poll = q.poll();
            var last = res.getLast();
            if (poll[0] <= last[1]) {
                if (poll[1] > last[1]) {
                    last[1] = poll[1];
                    res.set(res.size() - 1, last);
                }
            } else {
                res.add(poll);
            }
        }

        return res.toArray(new int[0][0]);
    }
}
