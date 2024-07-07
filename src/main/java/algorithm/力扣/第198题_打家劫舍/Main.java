package algorithm.力扣.第198题_打家劫舍;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 天章
 * @Date 2024/3/13 21:37
 * Description:
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋
 * 装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 */
public class Main {

    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        int res = rob(new int[]{2, 7, 9, 3, 1});
        System.out.println(res);
    }

    /**
     * 对于 n 个连续的房子，计算其偷窃总数最大值，有两种情况:
     *  1. 偷第 n 个房子， + 前 n - 2 个房子的最大值 （n - 1不可偷）
     *  2. 不偷第 n 个房子， 仅 前 n - 1 个房子的最大值
     */
    public static int rob(int[] nums) {
        map.clear();
        return rob(nums, nums.length);
    }

    // int n 是个数，而作为索引时需要 -1
    public static int rob(int[] nums, int n) {
        if(n <= 0)
            return 0;
        if(n == 1)
            return nums[0];

        if (map.containsKey(n))
            return map.get(n);

        int pro1 = nums[n - 1] + rob(nums, n - 2);
        int pro2 = rob(nums, n - 1);
        int res = Math.max(pro1, pro2);
        map.put(n, res);
        return res;
    }
}
