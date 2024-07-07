package algorithm.力扣.第137题_只出现一次的数字II;

/**
 * @author 天章
 * @Date 2024/3/27 17:44
 * Description:
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
 * 请你找出并返回那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[] {5,2,5,5,6,6,6,5,5,5};
        System.out.println(solve(arr));
    }

    public static int solve(int[] nums) {
        int ones = 0, twos = 0;

        for (int num : nums) {
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }

        return ones;
    }

}
