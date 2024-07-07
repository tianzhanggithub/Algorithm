package algorithm.通用算法.排序.插入排序;

/**
 * @author 天章
 * @Date 2024/1/26 1:04
 * Description:
 *   插入排序
 *   时间复杂度 = (n-1)n/2 --> n^2
 *   空间复杂度 = 1，原地排序
 */
public class Main {

    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {   // 不必从第 1 个开始，第 2 个开始即可
            int key = arr[i];   // 当前处理值
            int j = i - 1;
            // 将比 key 大的元素往后移动
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

}
