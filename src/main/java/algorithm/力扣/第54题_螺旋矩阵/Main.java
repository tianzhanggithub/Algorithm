package algorithm.力扣.第54题_螺旋矩阵;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 天章
 * @Date 2024/5/16 23:00
 * Description:
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class Main {

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> res = spiralOrder(matrix);
        res.forEach(System.out::print);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0)
            return res;

        int m = matrix.length, n = matrix[0].length;
        int up = 0, down = m - 1;   // 限制可访问的行数
        int left = 0, right = n - 1;   // 限制可访问的列数
        // 向下完成一轮，右边界减一；向上完成一轮，左边界加一（不包括当前正处于的该位置）
        // 向右完成一轮，上边界加一；向左完成一轮，下边界减一（不包括当前正处于的该位置）
        int step = 0;   // 0 - 右; 1 - 下; 2 - 左; 3 - 上;   需要对 4 取余
        int horPointer = -1, verPointer = 0;   // 横向指针；纵向指针
        while (true) {
            // 先判定是否需要结束
            if(up > down || left > right)
                break;

            // 根据 step 判断怎么操作
            switch (step % 4) {
                case 0:   // 向右
                    while(horPointer < right) {
                        horPointer++;  // 右移
                        res.add(matrix[verPointer][horPointer]);
                    }
                    up++;
                    break;
                case 1:   // 向下
                    while(verPointer < down) {
                        verPointer++;  // 下移
                        res.add(matrix[verPointer][horPointer]);
                    }
                    right--;
                    break;
                case 2:   // 向左
                    while(horPointer > left) {
                        horPointer--;  // 左移
                        res.add(matrix[verPointer][horPointer]);
                    }
                    down--;
                    break;
                case 3:   // 向上
                    while(verPointer > up) {
                        verPointer--;  // 上移
                        res.add(matrix[verPointer][horPointer]);
                    }
                    left++;
                    break;
            }
            step++;
        }

        return res;
    }

}
