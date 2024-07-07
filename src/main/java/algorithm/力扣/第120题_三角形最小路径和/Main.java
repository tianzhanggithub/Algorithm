package algorithm.力扣.第120题_三角形最小路径和;

import java.util.*;

/**
 * @author 天章
 * @Date 2024/3/13 22:49
 * Description:
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的
 * 是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 */
public class Main {

    private static Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(-1));
        list.add(Arrays.asList(2,3));
        list.add(Arrays.asList(1,-1,-3));
        System.out.println(minimumTotal(list));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        map.clear();
        int size = triangle.size();
        int x = size - 1;   // x 是行索引，从 0 开始
        List<Integer> integers = triangle.get(x);
        int minSub = Integer.MAX_VALUE;
        for (int y = 0; y <= x; y++) {
            int temp = subCalc(triangle, x, y);
            if(temp < minSub)
                minSub = temp;
        }
        return minSub;
    }

    // 计算到第 n 层的最小路径和，按照三角形，则第 n 层有 n 个节点，也就是从 n - 1 层到 n 层有 n 个子问题结果
    // x 是 行索引，从 0 开始；  y 是列索引，从 0 开始
    public static int subCalc(List<List<Integer>> triangle, int x, int y) {
        // 对于第 x 行 第 y 列的点来说，其上层子结果只有 1 个或 2 个 或 3 个；
        // 正常来讲是 3 个，但是要考虑数组越界问题
        Integer val = triangle.get(x).get(y);
        if(x == 0)  // 第 0 行，也就是只有 1 个数字在这一行，那没办法了，不能往上了
            return val;

        // 先查缓存
        if(!map.containsKey(x))
            map.put(x, new HashMap<>());
        Map<Integer, Integer> subMap = Main.map.get(x);
        if(subMap.containsKey(y))
            return subMap.get(y);
        // 往上层求子解
        // 第 1 个子解，由左上方下来的
        int minSum = Integer.MAX_VALUE;
        if(y >= 1) {  // 原式: if(x - 1 >= 0 && y - 1 >= 0)
            int temp = subCalc(triangle, x - 1, y - 1);
            if(temp < minSum)
                minSum = temp;
        }
        // 第 2 个子解，来自正上方
        if(y <= x - 1) {   // 原式: if(x - 1 >= 0 || y <= x - 1)
            int temp = subCalc(triangle, x - 1, y);
            if(temp < minSum)
                minSum = temp;
        }
        // 第 3 个子解，来自右上方
        if(y + 1 <= x - 1) {
            int temp = subCalc(triangle, x - 1, y);
            if(temp < minSum)
                minSum = temp;
        }

        int res = val + minSum;
        map.get(x).put(y, res);
        return res;
    }

}
