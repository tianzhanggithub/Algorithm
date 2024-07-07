package algorithm.力扣.第135题_分发糖果;

import java.util.Arrays;

/**
 * @author 天章
 * @Date 2024/1/28 20:06
 * Description:
 *   分发糖果
 *   n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *   你需要按照以下要求，给这些孩子分发糖果：
 *   每个孩子至少分配到 1 个糖果。
 *   相邻两个孩子评分更高的孩子会获得更多的糖果。
 *   请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 2, 2, 1};
        solve1(arr);
    }

    /**
     * 差分
     */
    private static int solve1(int[] ratings) {
        if(ratings == null || ratings.length == 0)
            return 0;
        int[] candies = new int[ratings.length];
        // 左遍历
        int candy = 1;
        candies[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            int diff = ratings[i] - ratings[i - 1];
            if(diff <= 0)
                candy = 1;
            else
                candy++;
            candies[i] = candy;
        }
        System.out.println("左处理: " + Arrays.toString(candies));

        // 右处理修正
        candy = 1;
        int total = 0;
        candies[ratings.length - 1] = Math.max(candies[ratings.length - 1], candy);
        total += candies[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            int diff = ratings[i] - ratings[i + 1];
            if(diff <= 0)
                candy = 1;
            else
                candy++;
            candies[i] = Math.max(candies[i], candy);
            total += candies[i];
        }
        System.out.println("右处理修正: " + Arrays.toString(candies));
        System.out.println("count: " + total);
        return total;
    }

}
