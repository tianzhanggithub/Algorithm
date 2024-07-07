package algorithm.面试题.阿里.平均数大于K的最长子序列的长度;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 天章
 * @Date 2024/3/27 1:02
 * Description:
 * 给定 n 个正整数组成的数组，求平均数大于 k 的最长子序列的长度
 *
 * 输入描述
 * 第一行输入两个正整数 n 和 k，用空格隔开
 * 第二行输入 n 个正整数用来表示数组
 * 输出描述
 * 如果不存在子序列的平均数大于，则输出-1。 否则输出最长子序列的长度。
 *
 * 样例
 * 输入
 * 5 2
 * 3 1 1 2 3
 * 输出
 * 2
 */
public class Main {

    private static Map<Long, Long> cache = new HashMap<>();
    private static int k;
    private static int maxLen = -1;

    public static void main(String[] args) {
        int[] arr = new int[] {3,1,1,2,3};
        k = 2;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j <= i + 1 ; j++) {
                solve(arr, i, j);
            }
        }
        System.out.println(maxLen);
    }

    /**
     * 又是子序列，一看到子序列就准备用动态规划
     * 对于 index 位置上的元素 K，考虑 index + 1 种情况
     * 例如：对于 index = 3，则考虑子数组：[3], [2,3], [1,2,3] 三种情况
     * 根据动态规划的思想，不考虑自身，就是需要 [2], [1,2] 这两个子序列的信息
     * 需要什么信息？ 1. 数据总量 count， 2. 总和 sum
     * 这就发现了动态规划子问题了！
     *   获取以 pointer 为结束索引，长度为 len 的数组的信息
     * 汇总？
     *   每组计算之后，看是否符合要求，符合的话，就看要不要更新 maxLen
     *
     * @return 数据总和 sum
     * 不需要返回 len，len 不是在参数里了么？
     */
    public static long solve(int[] arr, int pointer, int len) {
        // 先看缓存
        long key = (((long)pointer) << 32) + (long)len;    // 唯一 key
        Long record = cache.getOrDefault(key, null);
        if(record != null)
            return record;
        // 没缓存，计算
        long sum = arr[pointer];
        if(len != 1)
            sum += solve(arr, pointer - 1, len - 1);
        cache.put(key, sum);
        if(len > maxLen && ((double)sum / len) > k)
            maxLen = len;

        return sum;
    }



}
