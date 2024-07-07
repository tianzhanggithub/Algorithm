package algorithm.通用算法.排序.归并排序;

import java.util.Arrays;

/**
 * @author 天章
 * @Date 2024/6/15 21:07
 * Description: ...
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[] {4,2,5};
        merge(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void merge(int[] arr, int L, int R) {
        // 返回条件
        if(L >= R)
            return;
        // 递归处理左右
        int mid = L + ((R - L) >> 1);
        merge(arr, L, mid);
        merge(arr, mid + 1, R);
        // 得到左右结果，归并
        int[] temp = new int[R - L + 1];
        int p1 = L, p2 = mid, p3 = mid + 1, p4 = R;
        int pointer1 = p1, pointer2 = p3;
        for (int i = 0; i < temp.length; i++) {
            if(pointer1 <= p2) {
                if(pointer2 <= p4 && arr[pointer1] >= arr[pointer2]) {
                    temp[i] = arr[pointer2++];
                } else {
                    temp[i] = arr[pointer1++];
                }
            } else {
                temp[i] = arr[pointer2++];
            }
        }
        // 把 temp 存储的结果放回 arr
        for (int i = 0; i < temp.length; i++) {
            arr[L + i] = temp[i];
        }
    }

}
