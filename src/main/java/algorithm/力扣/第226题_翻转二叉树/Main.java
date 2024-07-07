package algorithm.力扣.第226题_翻转二叉树;

/**
 * @author 天章
 * @Date 2024/3/5 21:49
 * Description:
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 */
public class Main {

    public static void main(String[] args) {
        // 构建示例二叉树
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(2);
    }

    public static TreeNode invertTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return root;
        TreeNode mid = root.left;  // 交换左右子树
        root.left = root.right;
        root.right = mid;
        if(root.left != null)   // 递归
            invertTree(root.left);
        if(root.right != null)
            invertTree(root.right);
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
