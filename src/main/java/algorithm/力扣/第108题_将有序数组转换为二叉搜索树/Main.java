package algorithm.力扣.第108题_将有序数组转换为二叉搜索树;

/**
 * @author 天章
 * @Date 2024/3/27 17:15
 * Description:
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵平衡二叉搜索树。
 */
public class Main {

    public static void main(String[] args) {

    }

    /**
     * 数组 --> 二叉搜索树  其实挺简单的
     * 只需要不断递归:
     * 每次取数组中间的元素作为根节点，然后：
     * 左边的所有元素去构建左子树；右边的所有元素去构建右子树
     * 循环执行，直到无法继续
     */
    public static TreeNode solve(int[] nums, int begin, int end) {
        // 0. 先处理特殊情况，如果只包含一个需要处理的元素，则返回本身即可
        if(begin == end)
            return new TreeNode(nums[begin]);
        if(begin > end)   // 无效范围返回 null
            return null;
        // 1. 取中间节点构建根节点
        int rootIndex = (begin + end) / 2;
        TreeNode root = new TreeNode(nums[rootIndex]);
        // 2. 构建左子树
        root.left = solve(nums, begin, rootIndex - 1);
        // 3. 构建右子树
        root.right = solve(nums, rootIndex + 1, end);
        return root;
    }


    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
