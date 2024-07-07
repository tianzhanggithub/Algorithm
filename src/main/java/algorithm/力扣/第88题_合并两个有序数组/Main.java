package algorithm.力扣.第88题_合并两个有序数组;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author 天章
 * @Date 2024/1/25 1:50
 * Description:
 * 合并两个有序数组
 *   给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *   请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *   注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 */
public class Main {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        solve1(nums1, nums2);
        solve2(nums1, nums2);
    }

    /**
     * 方案1，直接合并两个数组，然后用任意排序算法。这里选用最快的 快速排序，其时间复杂度为 N·logN
     * 而合并后数组长度为 m + n，所以整个方法的 时间复杂度 = (m+n)*log(m+n)
     * 空间复杂度 = log(m+n)
     */
    private static void solve1(int[] nums1, int[] nums2) {
        long time = System.nanoTime();
        int m = nums1.length - nums2.length;
        for (int i = 0; i < nums2.length; i++) {   // 合并两个数组
            nums1[m + i] = nums2[i];
        }
        System.out.println("solve1, 拼接后的数组: " + Arrays.toString(nums1));
        // 排序
        Arrays.sort(nums1);
        long interval = System.nanoTime() - time;
        System.out.println("方法1: " + interval + " ns; " + (interval/1000000L) + " ms");
    }

    /**
     * 方法2：双指针，一个数组一个指针，每一轮选择数值小的放入新数组
     * 时间复杂度 = m + n，双指针一共需要移动 m + n 次即可
     * 空间复杂度 = m + n
     *
     * 但可以，「逆向双指针」
     * 逆向双指针从尾到头，由于 nums1 的后半部分是空的，所以不需要额外数组，直接存在 nums1 就可以了，比 双指针 更优
     */
    private static void solve2(int[] nums1, int[] nums2) {
        if((nums1.length - nums2.length) == 0) {
            for (int i = 0; i < nums2.length; i++) {
                nums1[i] = nums2[i];
            }
        }
        if(nums2.length == 0)
            return;
        long time = System.nanoTime();
        int len1 = nums1.length - nums2.length;
        int len2 = nums2.length;
        int[] sorted = new int[len1 + len2];
        int pointer1 = 0, pointer2 = 0, index = 0;
        while (true) {
            if(pointer1 == len1 && pointer2 == len2)
                break;

            if(pointer1 == len1
                    || (pointer1 != len1
                        && pointer2 != len2
                        && nums1[pointer1] > nums2[pointer2]))
                sorted[index++] = nums2[pointer2++];
            else
                sorted[index++] = nums1[pointer1++];
        }
        // nums1 = Arrays.copyOf(sorted, sorted.length);   // 不能用这个，因为会赋值一个新的对象，不是原来的地址了
        for (int i = 0; i < sorted.length; i++) {
            nums1[i] = sorted[i];
        }
    }

}
