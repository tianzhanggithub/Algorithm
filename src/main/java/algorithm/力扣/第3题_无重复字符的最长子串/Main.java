package algorithm.力扣.第3题_无重复字符的最长子串;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 天章
 * @Date 2024/1/29 16:40
 * Description:
 *   给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Main {

    /**
     * 方法 1：滑动窗口
     * 原理很简单，从左往右遍历，没有遇到重复的时，遇到一个加一个，遇到重复的时，把重复的及其之前的截断
     * 由于已经记录了最大的长度，而截断的子串再长也不可能超过记录的长度，因此不必再考虑
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> hash = new HashMap<>();
        char[] chars = s.toCharArray();
        int max = 0;
        int left = 0;
        for (int right = 0; right < chars.length; right++) {
            Integer repeatIndex = hash.get(chars[right]);
            if(repeatIndex != null) {
                max = Math.max(max, hash.size());
                for (left = left; left <= repeatIndex; left++)
                    hash.remove(chars[left]);
            }
            hash.put(chars[right], right);
        }
        // 最后一批
        max = Math.max(max, hash.size());
        return max;
    }

}
