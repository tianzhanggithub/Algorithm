package algorithm.力扣.第27题_移除元素;

import java.util.Arrays;

/**
 * @author 天章
 * @Date 2024/1/26 23:06
 * Description:
 *   移除元素
 *   给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *  不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *  元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class Main {

    public static void main(String[] args) {
        int[] nums = new int[] {4, 5};
        //solve1(nums, 3);
        solve2(nums, 5);
    }

    /**
     * 方法 1：双指针，第一个指针存储最新一个“新元素”索引；第二个指针指向当前处理元素
     * 原地处理，元素顺序不会改变
     * 此时不是最优解，因为最坏情况下：只有第一个元素需要被删除，那么整个数组就需要移动 len - 1 次，不是很划算
     */
    private static int solve1(int[] nums, int val) {
        long time = System.nanoTime();
        if(nums.length == 0)
            return 0;

        int pointer = 0;   // 这是第一个指针
        for (int i = 0; i < nums.length; i++) {   // i 充当第二个指针
            if(nums[i] != val)
                nums[pointer++] = nums[i];
        }
        long interval = System.nanoTime() - time;
        System.out.println("结果: " + Arrays.toString(nums) + "; count = " + pointer);
        System.out.println("方法1: " + interval + " ns; " + (interval/1000000L) + " ms");
        return pointer;
    }

    /**
     * 方法 2：优化双指针，第一个指针遍历所有元素；第二个指针从最后一个数开始，一旦指针1发现一个val，指针2扔一个数字过去补位
     * 但注意，指针2不要把val扔过去了
     * 同时，指针1也不需要遍历所有的，它大于指针2时，就结束了
     * 原地处理，性能更好，但会打乱元素顺序
     */
    private static int solve2(int[] nums, int val) {
        if(nums.length == 0)
            return 0;

        int pointer = nums.length - 1;   // 这是第一个指针
        int count = 0;
        for (int i = 0; i < nums.length; i++) {   // i 充当第二个指针
            if(i > pointer)
                break;
            if(nums[i] == val) {
                while (pointer >= i && nums[pointer] == val) {
                    pointer--;
                }
                if(pointer >= i) {
                    nums[i] = nums[pointer--];
                    count++;
                }
            } else
                count++;
        }
        System.out.println("count: " + count);
        return count;
    }

}
