package algorithm.通用算法.排序.冒泡排序;

/**
 * @author 天章
 * @Date 2024/1/25 2:57
 * Description:
 *   冒泡排序
 *   每次遍历剩余的所有数字，每当发现 当前元素 > 下一元素，则交换两者位置；每一轮，都会将剩余数字中的最大值交换到最右边
 *   时间复杂度 = n(n-1)/2 --> n^2，总的来说，还是比选择排序快那么一丢丢
 *   空间复杂度 = 1，原地排序
 */
public class Main {

    public static void sort(int[] arr) {
        int end = arr.length - 2;   // 比较的结束坐标，最开始时，等于数组里倒数第二个数的坐标
        while(end >= 0) {
            for (int i = 0; i <= end; i++) {
                if(arr[i] > arr[i + 1])
                    swap(arr, i, i + 1);
            }
            end--;
        }
    }

    // 交换数组里指定的两个索引位置的值
    private static void swap(int[] arr, int i, int j) {
        // 异或操作是最快的交换方式，a = b 时不交换
        if (arr[i] != arr[j]) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }

}
