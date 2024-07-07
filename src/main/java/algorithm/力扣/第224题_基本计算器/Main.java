package algorithm.力扣.第224题_基本计算器;

/**
 * @author 天章
 * @Date 2024/2/10 22:14
 * Description:
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 *
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 *
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 */
public class Main {

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        int res = calculate(s);
        System.out.println("计算结果: " + res);
    }

    /**
     * 方法 1： 类似于，操作数栈？
     * 要注意，一旦遇到 ( 则代表开启一个子计算，直到 ) 结束
     */
    public static int calculate(String s) {
        if(s.length() == 0)
            return 0;
        char[] chars = s.toCharArray();
        int res = subCalc(chars, 0).res;   // 最终结果
        return res;
    }

    private static Frame subCalc(char[] chars, int begin) {
        int tempRes = 0;
        int tempLongFigure = 0;
        boolean lastIsFigure = false;   // 上一个字符是数字？如果连续数字，就是超过1位的数字了，要处理才能得到
        boolean minusWait = false;     // 记录下一次计算是否是 减法； （加法不用特意记，因为不是 - ，就是能是 + 了）
        for (int i = begin; i < chars.length; i++) {
            char c = chars[i];
            if(' ' == c)  // 跳过任意 空格
                continue;
            // 正式处理内容
            // 是数字部分
            if(c >= '0' && c <= '9') {   // 是数字，可能：1. 是第一个数字，也就是第一个字符，那就直接 + 上；2. 是其余地方的数字，那么肯定有一个提前的 + 或 - 等待
                // 如果还没有超界，并且下一个字符还是数字，那么暂时就不能处理，得等到把数字看完
                if(i + 1 < chars.length && chars[i + 1] >= '0' && chars[i + 1] <= '9') {
                    tempLongFigure = tempLongFigure * 10 + (c - '0');
                } else if(minusWait) {
                    tempLongFigure = tempLongFigure * 10 + (c - '0');
                    tempRes = tempRes - tempLongFigure;
                    tempLongFigure = 0;
                    minusWait = false;
                } else {
                    tempLongFigure = tempLongFigure * 10 + (c - '0');
                    tempRes = tempRes + tempLongFigure;
                    tempLongFigure = 0;   // 临时长数字变量归零，等待下一次处理
                }
            }
            // 反正不是数字部分
            else {
                if ('(' == c) {   // 开启一个子计算，得到一个返回的数字
                    Frame subRes = subCalc(chars, i + 1);// 从 ( 的下一个字符开始
                    if (minusWait) {
                        tempRes = tempRes - subRes.res;
                        minusWait = false;
                    } else {
                        tempRes = tempRes + subRes.res;
                    }
                    i = subRes.nextIndex - 1;   // 抵扣自动的 i++
                } else if (')' == c) {   // 结束当前子计算
                    Frame frame = new Frame(i + 1, tempRes);
                    return frame;
                } else if ('+' == c) {   // 加法。题中已经说明，不会有两个连续的操作符
                    minusWait = false;
                } else if ('-' == c) {
                    minusWait = true;
                }
            }
        }
        return new Frame(-1, tempRes);   // 结束
    }

    private static class Frame {
        public int nextIndex;
        public int res;

        public Frame(int nextIndex, int res) {
            this.nextIndex = nextIndex;
            this.res = res;
        }
    }

}
