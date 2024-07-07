package algorithm.面试题.阿里.不同元素个数为N的子数组数量;

import java.util.*;

/**
 * @author 天章
 * @Date 2024/3/26 23:25
 * Description:
 * 小红拿到了一个长度为 n 的数组。她定义一个连续子数组的权值为:子数组内不同元素的个数。小红想知道，权值
 * 分别为 1, 2, 3, ..., n 的子数组数量有多少个。
 *
 * 输入描述
 * 第一行输入 1 个正整数 n，代表数组的元素数量
 * 第二行输入 n 个正整数，代表小红拿到的数组
 * 输出描述
 * n 个整数，分别代表权值为 1, 2, ..., n 的子数组数量
 *
 * 样例
 * 输入
 * 4
 * 1 2 2 3
 * 输出
 * 5 4 1 0
 */
public class Main {

    private static Map<Long, Set<Integer>> cache = new HashMap<>();
    private static int[] weights;

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,2,3};
        weights = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {   // i 就是 index
            for (int j = 1; j <= i + 1; j++) {   // j 就是 len
                resolve(arr, i, j);
            }
        }
        System.out.println(Arrays.toString(weights));
    }

    /**
     * 思路：动态规划
     * 对于 index = n 的位置，考虑 index 及其之前的所有元素，在该位置可以得到 n + 1 个结果
     * 如：对于数组 [1 2 2 3]
     * index = 2，则考虑 前置数组: [1 2 2]
     *                              --     // 权值为 1 的结果，可以直接得出
     *                            ----     // 权值为 2 的结果，由 index = 1 && len = 1 的已知结果 + 当前位置 得出
     *                          ------     // 权值为 3 的结果，由 index = 1 && len = 2 的已知结果 + 当前位置 得出
     * 上面提到的 "index = x && len = y 的已知结果"，就是需要复用的动态规划小任务
     * @param arr: 原始数组，只读不写
     * @param index: 指定一个元素的位置（索引）
     * @param len: 指定长度
     * @return 指定范围内的所有元素（不重复）
     */
    public static Set<Integer> resolve(int[] arr, int index, int len) {
        long key = (((long)index) << 32) + (long)len;    // 唯一 key
        Set<Integer> record = cache.getOrDefault(key, null);
        if(record != null)
            return record;

        // 没有缓存，开始计算
        // index = x && len = y 的结果，需要先得到 index = x - 1 && len = len - 1 的结果，然后再和本元素进行结合
        // 先处理特殊情况
        Set<Integer> res = null;
        if(len == 1) {   // 长度为 1，返回自身
            res = new HashSet<>();
            res.add(arr[index]);
        } else {
            Set<Integer> preRes = resolve(arr, index - 1, len - 1);
            res = new HashSet<>(preRes);   // 复制一个，不然所有的 Set 都是同一个对象了
            res.add(arr[index]);
        }
        // 既然是第一次计算，那就要计数了
        weights[res.size() - 1]++;
        cache.put(key, res);
        return res;
    }



}
