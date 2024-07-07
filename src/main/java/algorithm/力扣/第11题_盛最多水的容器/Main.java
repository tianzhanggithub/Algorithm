package algorithm.力扣.第11题_盛最多水的容器;

/**
 * @author 天章
 * @Date 2024/1/28 20:59
 * Description:
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[] {9,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,7,10};
        solve1(arr);
    }

    /**
     * 方法 1：双指针
     *   任意两个垂线之间构成的面积都有可能是最大的，哪怕 width = 1，也有可能左右垂线的 height = 1000000 而成为最大的，因此遍历所有情况不可避免
     *   双指针从头尾开始计算，每轮向中间移动一个指针，移动哪个？当然是较小的那个，为什么？
     *   设，当前数组为  a,b, ... ,x,y，此时双指针分别在 a,y处，width = w，那么此时 S = min(a, y)*w
     *   现在，要移动左右指针其中一个，共可能得到两种情况，S1 = min(a,x)*(w-1), S2 = min(b,y)*(w-1)
     *   同时，不要忘了移动之前的 S0 = min(a, y)*w
     *   此时，也就是从这三个面积中选出最大的一个
     *   由于数轴对称不会改变最终结果，所以可以假设 a < y
     *   由于面积总是由两条直线中较短的那个决定，因此，
     *     移动较长的那个指针，结果可能不变，可能变小
     *     移动较短的那个指针，结果可能不变，可能变小，可能变大
     *   所以，我们发现，移动 较短的指针，面积不可能变大，也就是说 移动 a 处的指针，得到 S2 = min(b,y)*(w-1) <= S0，所以这个情况可以丢弃
     *   而移动 y 处的指针，得到 S1 = min(a,x)*(w-1)，才有可能比 S0 大，S2直接被丢弃，不想要计算
     *   因此无论如何，每次都应该移动较短的那个位置的指针
     */
    private static int solve1(int[] height) {
        int i = 0, j = height.length - 1;
        int max = 0;
        while (i < j) {
            System.out.println("元素: " + height[i] + ", " + height[j]);
            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            System.out.println("max area = " + max);
            if(height[i] < height[j])
                i++;
            else
                j--;
        }
        return max;
    }

}
