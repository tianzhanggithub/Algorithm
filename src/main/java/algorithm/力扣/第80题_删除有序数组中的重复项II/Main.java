package algorithm.力扣.第80题_删除有序数组中的重复项II;

import java.util.Arrays;

/**
 * @author 天章
 * @Date 2024/1/27 23:45
 * Description:
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，
 * 使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,2,2,2,3,4,5,5,6,7,8,8};
        solve1(arr);
    }

    /**
     * 方法 1：双指针
     * 原理：指针 A，B 开始都在索引 0，每一回合，A 先走一步
     * 如果 A 发现现在这个和 B 那个不一样，就让 B 也走一步
     * 如果 A 发现数值不一样了，别急，先给重复项计数 +1,
     * 如果重复项计数 count < 2，说明刚遇见相同的，B还是可以走
     * 如果 count >= 2，B 就不能走了，A 说 "你先别动，我看看前面还有多少个"，直到 A 发现不一样了，就告诉 B "你走一步吧，记得把 count 清零"
     */
    private static int solve1(int[] nums) {
        int pointer = 0;
        int duplicate = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[pointer] == nums[i])
                duplicate++;
            else
                duplicate = 0;
            if(duplicate < 2) {
                nums[++pointer] = nums[i];
            }
            System.out.println("步骤: " + Arrays.toString(nums));
        }
        System.out.println("count: " + (pointer + 1));
        return ++pointer;
    }

}
