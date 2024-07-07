package algorithm.力扣.第125题_验证回文串;

/**
 * @author 天章
 * @Date 2024/1/28 0:17
 * Description:
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 */
public class Main {

    public static void main(String[] args) {
        String s = ".,";
        boolean var = solve1(s);
        System.out.println(var);
    }

    private static boolean solve1(String s) {
        char[] chars = s.toCharArray();
        if(chars.length <= 1)
            return true;
        int i = 0, j = chars.length - 1;
        while (i <= j) {
            // 先处理 i
            while (!isAscii(chars[i])) {
                i++;
                if(i >= chars.length)
                    return true;
            }
            if(i > chars.length - 1)
                break;
            char a = chars[i];
            a = (char) ((a >= 65 && a <= 90) ? (a + 32) : a);

            while (j >= 0 && !isAscii(chars[j])) {
                j--;
                if(j < 0)
                    return true;
            }
            char b = chars[j];
            b = (char) ((b >= 65 && b <= 90) ? (b + 32) : b);
            System.out.println(a + ", " + b);
            if(a != b)
                return false;
            i++;
            j--;
        }
        return true;
    }

    private static boolean isAscii(char c) {
        if(c >= 48 && c <= 57)
            return true;
        if(c >= 65 && c <= 90)
            return true;
        if(c >= 97 && c <= 122)
            return true;
        return false;
    }
}
