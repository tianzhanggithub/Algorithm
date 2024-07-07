package algorithm.通用算法.排序.快速排序;

/**
 * @author 天章
 * @Date 2024/1/25 2:38
 * Description:
 *   快速排序
 *   时间复杂度 = nlogn
 *   空间复杂度 = n，使用 O(n) 的栈帧空间，但是原地排序，没有使用别的数组
 */
public class Main {

    // 快速排序入口
    public static void sort(int[] nums) {
        executeQuickSort(nums, 0, nums.length - 1);
    }

    /**
     * 让我们回顾一下 快速排序 :-)
     *   快速排序基于分治思想，每次选择一个基准值，将 < 基准值的所有数放入 左边；将 >= 基准值的所有数放入 右边，然后再对 左右 分别使用快排
     *   直到不能再继续分隔，然后将所有数组拼接起来即可。这里使用了递归
     */
    private static void executeQuickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(nums, left, right);
            executeQuickSort(nums, left, pivotIndex - 1);     // 基准值的位置不需要参与排序
            executeQuickSort(nums, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];    // 选择基准元素，选取方式可以变化，这里选择简单方式，直接选用最后一个数字
        int i = left - 1;          // 初始化一个索引变量，用于记录小于基准值的元素的位置
        // 遍历所有数字，这个循环后，可以保证，所有 < 基准值的数字，都会在左侧，而这个“左侧”有多长？由 i 记录
        // 但注意，这只能保证所有 < 基准值的数字都在左侧，而这些数字之间的顺序无法保证，因此，需要分治下去，继续用快排
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);   // 将基准元素放到正确的位置，即 i+1 处
        return i + 1;      // 返回基准元素最终的索引位置，这个基准值，下次，就不需要参与排序了
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
