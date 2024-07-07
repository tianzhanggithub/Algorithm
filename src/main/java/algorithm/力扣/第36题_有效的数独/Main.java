package algorithm.力扣.第36题_有效的数独;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 天章
 * @Date 2024/1/29 23:53
 * Description:
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 注意：
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用 '.' 表示
 * 示例：board =
 * [['5','3','.','.','7','.','.','.','.']
 * ,['6','.','.','1','9','5','.','.','.']
 * ,['.','9','8','.','.','.','.','6','.']
 * ,['8','.','.','.','6','.','.','.','3']
 * ,['4','.','.','8','.','3','.','.','1']
 * ,['7','.','.','.','2','.','.','.','6']
 * ,['.','6','.','.','.','.','2','8','.']
 * ,['.','.','.','4','1','9','.','.','5']
 * ,['.','.','.','.','8','.','.','7','9']]
 *
 * 横：  行 0 - 8 | 列 0 - 8
 * 竖：  列 0 - 8 | 行 0 - 8
 * 方格（右下角）：(2,2)、(2,5)、(2,8)
 *              (5,2)、(5,5)、(5,8)
 *              (8,2)、(8,5)、(8、8)
 */
public class Main {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        boolean var = solve1(board);
        System.out.println(var);
        boolean var1 = solve2(board);
        System.out.println(var1);
    }

    /**
     * 方法 1：只遍历了一次棋盘
     * 有了 Row、Column、Square 三个对象，一次完成不是梦
     */
    private static boolean solve1(char[][] board) {
        // 初始化行、列、小方格对象
        HashMap<Integer, Row> rows = new HashMap<>();
        HashMap<Integer, Column> columns = new HashMap<>();
        HashMap<Integer, Square> squares = new HashMap<>();
        for (int i = 0; i <= 8; i++) {
            Row row = new Row(i);
            rows.put(i, row);
            Column column = new Column(i);
            columns.put(i, column);
            Square square = new Square(i);
            squares.put(i, square);
        }
        // 遍历棋盘, '.' 的 ASCII 是 46
        for (int i = 0; i < board.length; i++) {   // i 代表行
            char[] row = board[i];
            for (int j = 0; j < row.length; j++) {  // j 代表列
                char cij = row[j];
                if(cij == '.')
                    continue;
                if(!rows.get(i).addFactor(cij))   // 检查行
                    return false;
                if(!columns.get(j).addFactor(cij))  // 检查列
                    return false;
                // 检查小方格
                int squareIndex = Square.judgeIndex(i, j);
                if(!squares.get(squareIndex).addFactor(cij))
                    return false;
            }
        }
        return true;
    }

    // 行，0 - 8
    private static class Row {
        public int index;
        // 这 map 的 value 放什么都行，主要是为了 contains 的效率
        public Map<Character, Integer> factors = new HashMap<>();

        public Row(int index) {
            this.index = index;
        }

        public boolean addFactor(char factor) {
            if(factors.containsKey(factor))
                return false;         // 该行不是有效行
            else {
                factors.put(factor, 1);
                return true;    // 该行暂时还是有效行
            }
        }
    }

    // 列，0 - 8
    private static class Column {
        public int index;
        // 这 map 的 value 放什么都行，主要是为了 contains 的效率
        public Map<Character, Integer> factors = new HashMap<>();

        public Column(int index) {
            this.index = index;
        }

        public boolean addFactor(char factor) {
            if(factors.containsKey(factor))
                return false;         // 该行不是有效行
            else {
                factors.put(factor, 1);
                return true;    // 该行暂时还是有效行
            }
        }
    }

    // 3×3 小方格，0 - 8，先行再列，先左后右
    private static class Square {
        public int index;
        // 这 map 的 value 放什么都行，主要是为了 contains 的效率
        public Map<Character, Integer> factors = new HashMap<>();

        public Square(int index) {
            this.index = index;
        }

        public boolean addFactor(char factor) {
            if(factors.containsKey(factor))
                return false;         // 该行不是有效行
            else {
                factors.put(factor, 1);
                return true;    // 该行暂时还是有效行
            }
        }

        public static int judgeIndex(int x, int y) {
            if(x >= 0 && x <= 2 && y >= 0 && y <= 2)
                return 0;
            else if(x >= 3 && x <= 5 && y >= 0 && y <= 2)
                return 1;
            else if(x >= 6 && x <= 8 && y >= 0 && y <= 2)
                return 2;
            else if(x >= 0 && x <= 2 && y >= 3 && y <= 5)
                return 3;
            else if(x >= 3 && x <= 5 && y >= 3 && y <= 5)
                return 4;
            else if(x >= 6 && x <= 8 && y >= 3 && y <= 5)
                return 5;
            else if(x >= 0 && x <= 2 && y >= 6 && y <= 8)
                return 6;
            else if(x >= 3 && x <= 5 && y >= 6 && y <= 8)
                return 7;
            else if(x >= 6 && x <= 8 && y >= 6 && y <= 8)
                return 8;
            else
                throw new RuntimeException("xxx");
        }
    }

    /**
     * 方法 2： 抄袭方法 1，但不用对象，用数学
     * 不是我说，方法 1 虽然好看，但效率低，占用空间大，我得做一些改进
     * HashMap<Integer, Integer>
     *     其中 key 代表对象，百位：1 = 行， 2 = 列， 3 = 小方格
     *                     十位，代表索引序号；0 - 8
     *                     个位，代表数字：1 - 9
     *                     例如：138，代表第 3 行的数字 8 的记数
     *                     这些数字从 101 - 389
     *     这多方便？还需要那么多对象？
     */
    private static boolean solve2(char[][] board) {
        Map<Integer, Integer> map = new HashMap<>();   // 其实还是老道理，value 的类型和内容不重要
        for (int i = 0; i < board.length; i++) {
            char[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                char cij = row[j];
                if(cij == '.')   // 空格不用检查
                    continue;
                // 检查行
                int key1 = 1 * 100 + i * 10 + cij % 48;   // ASCII 中 1-9 的值是 49 - 57，所以 % 48 正好
                if(map.containsKey(key1))
                    return false;
                else {
                    map.put(key1, 1);
                }
                // 检查列
                int key2 = 2 * 100 + j * 10 + cij % 48;
                if(map.containsKey(key2))
                    return false;
                else {
                    map.put(key2, 1);
                }
                // 检查小方格
                // k 代表第几个小方格，这个需要一点简单的计算
                //int k = judgeIndex(i, j);
                int k = (i / 3) * 3 + (j / 3);
                int key3 = 3 * 100 + k * 10 + cij % 48;
                if(map.containsKey(key3))
                    return false;
                else {
                    map.put(key3, 1);
                }
            }
        }
        return true;
    }

    public static int judgeIndex(int x, int y) {
        if(x >= 0 && x <= 2 && y >= 0 && y <= 2)
            return 0;
        else if(x >= 3 && x <= 5 && y >= 0 && y <= 2)
            return 1;
        else if(x >= 6 && x <= 8 && y >= 0 && y <= 2)
            return 2;
        else if(x >= 0 && x <= 2 && y >= 3 && y <= 5)
            return 3;
        else if(x >= 3 && x <= 5 && y >= 3 && y <= 5)
            return 4;
        else if(x >= 6 && x <= 8 && y >= 3 && y <= 5)
            return 5;
        else if(x >= 0 && x <= 2 && y >= 6 && y <= 8)
            return 6;
        else if(x >= 3 && x <= 5 && y >= 6 && y <= 8)
            return 7;
        else if(x >= 6 && x <= 8 && y >= 6 && y <= 8)
            return 8;
        else
            throw new RuntimeException("xxx");
    }

}

    