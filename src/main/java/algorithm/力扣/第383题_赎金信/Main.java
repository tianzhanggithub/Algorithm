package algorithm.力扣.第383题_赎金信;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 天章
 * @Date 2024/2/6 22:33
 * Description:
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 */
public class Main {

    public static void main(String[] args) {
        String ransomNote = "a";
        String magazine = "ab";
        boolean var = solve2(ransomNote, magazine);
        System.out.println(var);
    }

    /**
     * 方法 1：直接把 magazine 中的所有字符做成 字典，每个 ransomNote 的字符到里面去找
     */
    public static boolean solve1(String ransomNote, String magazine) {
        Map<Character, Integer> dict = new HashMap<>();

        {  // 将 magazine 中所有字符生成为字典
            for (char c : magazine.toCharArray()) {
                Integer count = dict.getOrDefault(c, 0);
                count++;
                dict.put(c, count);
            }
        }

        {   // 执行判定
            for (char c : ransomNote.toCharArray()) {
                Integer count = dict.get(c);
                if (count == null || count <= 0)
                    return false;
                count--;
                dict.put(c, count);
            }
        }
        return true;
    }

    /**
     * 方法 2：方法 1 的改进版
     *   由于方法 1 先将 magazine 中的所有字符遍历一遍，形成字典，但极端情况下
     *   例如：  magazine = "afdjalkjxkljfkdja;kfdl;a";
     *          ransomNote = "a";
     *   则后续遍历都是没有意义的，所以优化为：需要的时候再遍历，而不是一次性遍历完
     */
    public static boolean solve2(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length())
            return false;
        if(ransomNote.length() == 0)
            return true;

        char[] rans = ransomNote.toCharArray();
        char[] mags = magazine.toCharArray();
        Map<Character, Integer> dict = new HashMap<>();
        int pointer = -1;
        for (char c : rans) {
            // 每一个字符，先看看 dict 里有没有
            Integer remain = dict.get(c);
            if(remain == null || remain <= 0) {  // 没有的话，就继续遍历 magazine 剩余的部分
                while (true) {   // 逐个遍历，直到找到需要的字符，或超界
                    pointer++;
                    if (pointer >= mags.length) // 超界
                        return false;
                    if(mags[pointer] == c) {   // 是要找的这个
                        break;      // 这个字符可以了，下一个
                    } else {   // 不是当前要找的字符，那就把这个存入 dict 备用
                        Integer count = dict.getOrDefault(mags[pointer], 0);
                        count++;
                        dict.put(mags[pointer], count);
                    }
                }
            } else {   // dict 里有的话，就拿来用
                remain--;
                dict.put(c, remain);
            }
        }

        return true;
    }

}
