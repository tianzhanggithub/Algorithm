package algorithm.通用算法.排序.选择排序;

/**
 * @author 天章
 * @Date 2024/1/25 2:46
 * Description:
 *   选择排序
 *   每次遍历所有剩余数字，将最小的一个，交换到前面去
 *   时间复杂度 = (n+2)(n-1)/2 --> n^2
 *   空间复杂度 = 1，原地排序
 */
public class Main {

    protected static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {   // 外层循环控制未排序部分，每次找到其中的最小值的位置
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            // 将找到的最小元素与已排序部分的末尾交换位置
            swap(arr, i, minIndex);
        }
    }

    // 交换数组里指定的两个索引位置的值
    private static void swap(int[] arr, int i, int j) {
        // 异或操作是最快的交换方式，但注意，他要求 a != b，否则结果全为 0
        if (arr[i] != arr[j]) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }

}
