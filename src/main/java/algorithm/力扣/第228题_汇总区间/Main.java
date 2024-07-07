package algorithm.力扣.第228题_汇总区间;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 天章
 * @Date 2024/2/6 23:48
 * Description:
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 *
 * 示例 1：
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 */
public class Main {

    public static void main(String[] args) {
        int[] nums = new int[] {0,1,2,4,5,7};
        List<String> res = solve1(nums);
        System.out.println(res);
    }

    /**
     * 方法 1：顺序，双指针
     * 整体还是很简单的，由于是有序数组，所以依次判断就可以了
     */
    public static List<String> solve1(int[] nums) {
        List<String> res = new ArrayList<>();
        // 先解决特殊情况，不必考虑 null
        if(nums.length == 0)
            return res;
        if(nums.length == 1) {
            res.add(String.valueOf(nums[0]));
            return res;
        }

        int pointer = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] - nums[i - 1] != 1) {  // 不连续了，需要截断，此时 [pointer, i - 1] 就是一个区间
                if(pointer == i - 1)
                    res.add(String.format("%d", nums[pointer]));
                else
                    res.add(String.format("%d->%d", nums[pointer], nums[i - 1]));
                pointer = i;
            }
        }
        // 别忘了最后一个
        if(pointer == nums.length - 1)
            res.add(String.format("%d", nums[pointer]));
        else
            res.add(String.format("%d->%d", nums[pointer], nums[nums.length - 1]));

        return res;
    }

}
