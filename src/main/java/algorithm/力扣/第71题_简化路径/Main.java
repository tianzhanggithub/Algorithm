package algorithm.力扣.第71题_简化路径;

import java.util.LinkedList;

/**
 * @author 天章
 * @Date 2024/2/10 21:35
 * Description:
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
 * 两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 *
 * 示例 1：
 * 输入：path = "/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 *
 * 示例 2：
 * 输入：path = "/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
 *
 * 示例 3：
 * 输入：path = "/a/./b///../../c/"
 * 输出："/c"
 */
public class Main {

    public static void main(String[] args) {
        //String path = "/a/./b///../../c/";
        String path = "/home//add";
        solve1(path);
    }

    /**
     * 方法 1：栈管理
     *   此方法 使用多次 string + string 操作，性能低，但碍于栈的特性，又无法使用 StringBuilder
     *   除非自定义一个同时实现栈和堆的类
     */
    public static String solve1(String path) {
        String[] factors = path.split("/");
        LinkedList<String> pipe = new LinkedList<>();
        for (String factor : factors) {
            if(".".equals(factor) || "".equals(factor))   // 不做任何处理
                continue;
            else if("..".equals(factor)) {  // 返回一级
                if (!pipe.isEmpty())
                    pipe.pop();
            }
            else {
                pipe.push(factor);
            }
        }
        if(pipe.isEmpty())
            return "/";
        StringBuilder sb = new StringBuilder();
        while (!pipe.isEmpty()) {
            sb.append("/").append(pipe.poll());
        }
        return sb.toString();
    }

}
