package algorithm.力扣.第70题_爬楼梯;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 天章
 * @Date 2024/3/13 21:26
 * Description:
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class Main {

    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        int f = climbStairs(3);
        System.out.println(f);
    }

    public static int climbStairs(int n) {
        if(n <= 1)   // 上 1 阶楼梯只有 1 种可能性;(上 0 也是 1 种，也就是不动，这是为了兼容 n = 2 分裂成 n = 1 和 n = 0)
            return 1;

        int var1 = n - 1;
        int down1res = 0;
        if(map.containsKey(var1))
            down1res = map.get(var1);
        else {
            down1res = climbStairs(var1);
            map.put(var1, down1res);
        }

        int var2 = n - 2;
        int down2res = 0;
        if(map.containsKey(var2))
            down2res = map.get(var2);
        else {
            down2res = climbStairs(var2);
            map.put(var2, down2res);
        }
        return down1res + down2res;
    }

}
