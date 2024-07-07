package algorithm.力扣.第56题_合并区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author 天章
 * @Date 2024/2/7 1:14
 * Description:
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class Main {

    public static void main(String[] args) {
        int[][] ranges = new int[4][2];
        ranges[0] = new int[] {1,3};
        ranges[1] = new int[] {2,6};
        ranges[2] = new int[] {15,18};
        ranges[3] = new int[] {8,10};
        solve1(ranges);
    }

    /**
     * 方法 1：首先给区间按左边界排序，这才方便处理，不然一堆乱序的区间没法处理
     * 任意两个区间，只要有重叠（包括刚好靠拢），则满足 左区间的右边界 >= 右区间的左边界
     * 由于区间已经被排序，所以从左到右，所有的合并都是连续的，也就是不可能两个不相邻的区间合并在一起
     */
    public static int[][] solve1(int[][] intervals) {
        List<int[]> res = new ArrayList<>();   // 先用 List 存，int[][] 不方便
        // 老规矩，先处理特殊情况
        if(intervals == null || intervals.length == 0)
            return parse(res);
        if(intervals.length == 1) {   // 只有一个，不用合并
            res.add(intervals[0]);
            return parse(res);
        }

        // 先给区间排序，直接交给 java 去排
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] < b[0]) return -1;
                if (a[0] > b[0]) return 1;
                return 0;
            }
        });

        for (int i = 0; i < intervals.length;) {
            int[] head = intervals[i];
            int probe = head[1];   // 当前区间的右边界，向右探寻;   probe: 探针
            int tailPointer = i + 1;
            while (tailPointer < intervals.length) {
                if(intervals[tailPointer][0] <= probe) {   // 该区间需要合并
                    probe = Math.max(probe, intervals[tailPointer][1]);
                    tailPointer++;     // 继续向右探寻
                } else {  // 该区间不能合并了，结束
                    break;
                }
            }
            i = tailPointer;   // 下一次，从这个区间开始探查
            // 把探查结束的这个结果区间收起来
            res.add(new int[]{head[0], probe});
        }
//        for (int[] re : res) {
//            System.out.println(Arrays.toString(re));
//        }

        return parse(res);
    }

    private static int[][] parse(List<int[]> raw) {
        int[][] res = new int[raw.size()][2];
        for (int i = 0; i < raw.size(); i++) {
            int[] pair = raw.get(i);
            res[i][0] = pair[0];
            res[i][1] = pair[1];
        }
        return res;
    }

}
