package algorithm.力扣.第104题_二叉树的最大深度;

/**
 * @author 天章
 * @Date 2024/3/5 10:58
 * Description:
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
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
        // Test
        int max = maxDepth(root);
        System.out.println("最终结果: " + max);
    }

    /**
     * 要判断最大深度，在不知道二叉树构成的情况下，一次遍历是必需的
     * 看我手写一个中序遍历: 左 - 中 - 右
     */
    public static int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int max = midOrderTraversal(root, 1);
        return max;
    }

    // 中序遍历，返回最大深度
    public static int midOrderTraversal(TreeNode node, int nodeDepth) {
        int left = 0, right = 0;
        // 左子树
        if(node.left != null)
            left = midOrderTraversal(node.left, nodeDepth + 1);
        // 自己
        System.out.println("遍历: " + node.val + ", 我目前的深度: " + nodeDepth);
        // 右子树
        if(node.right != null)
            right = midOrderTraversal(node.right, nodeDepth + 1);
        int var = Math.max(Math.max(left, right), nodeDepth);
        System.out.println("该节点是: " + node.val + ", 返回值: " + var);
        return var;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }
    }

}
