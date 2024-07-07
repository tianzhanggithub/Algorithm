package algorithm.力扣.第105题_从前序与中序遍历序列构造二叉树;

import java.util.Arrays;

/**
 * @author 天章
 * @Date 2024/3/5 22:05
 * Description:
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * 实例 1：
 * 输入: preorder = [], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 */
public class Main {

    public static void main(String[] args) {
        int[] preorder = new int[] {3,1,2,4};
        int[] inorder = new int[] {1,2,3,4};
        TreeNode tree = buildTree(preorder, inorder);
    }

    /**
     * 前序：中 - 左 - 右
     * 中序：左 - 中 - 右
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 校验
        if(preorder == null || inorder == null || preorder.length == 0)
            return null;
        // 正式处理
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode tree = buildSubTree(preorder, inorder, root);
        return tree;
    }

    // 递归，逐步构建子树
    // root: 当前根节点（不是指二叉树的根节点，而是每次执行 子树构建 时 子树的根节点）
    // preorder: 仅传递当前子树拥有的节点
    // inorder: 仅传递当前子树拥有的节点
    public static TreeNode buildSubTree(int[] preorder, int[] inorder, TreeNode root) {
        // 第 0 步，校验，如果 inorder 只有一个值，那么不需要继续往下了
        if(inorder.length == 1)
            return root;
        // 第 1 步，从中序遍历中找到根节点位置
        int rootIndex = findIndex(inorder, root.val);
        // 第 2 步，拆分中序遍历数组，根节点左边为左子树，右边为右子树
        System.out.println("准备划分中序遍历数组: " + Arrays.toString(inorder) + "\n根节点: " + root.val);
        int[] lefts = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] rights = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        System.out.println("划分结果: \n  左子树: " + Arrays.toString(lefts) + "\n  右子树: " + Arrays.toString(rights));
        // 第 3 步，分别找到 左子树、右子树 的根节点。
        // 以左子树为例：先找到 左子树的 根节点，这需要结合 lefts 到 preorder 中去找
        // lefts 中有 N 个节点，N = 0的话就不说了
        // 否则，preorder 中，从左往右，第一个出现的 存在于 lefts 中的数字就对应左子树的根节点
        // 右子树同理。一次循环就可以把两个根节点都确定出来
        TreeNode leftRoot = null, rightRoot = null;
        int leftIndex = -1, rightIndex = -1;
        for (int i = 0; i < preorder.length; i++) {
            int var = preorder[i];
            if(leftRoot == null && arrContains(lefts, var))    // 如果左子树里存在这个节点，说明这个节点是左子树的根节点
                leftRoot = new TreeNode(var);
            if(rightRoot == null && arrContains(rights, var))   // 右子树一样
                rightRoot = new TreeNode(var);
            // 判断是否左右子树都找到根节点了，如果都找到了，就退出。（如果左/右子树为空，则也视为找到了）
            if((leftRoot != null || lefts.length == 0) && (rightRoot != null || rights.length == 0))
                break;
        }
        // 第 4 步，对于 左子树 和 右子树，找到根节点者说明需要继续划分，否则不需要继续向下
        TreeNode leftSubTree = null, rightSubTree = null;
        if(leftRoot != null)
            leftSubTree = buildSubTree(preorder, lefts, leftRoot);
        if(rightRoot != null)
            rightSubTree = buildSubTree(preorder, rights, rightRoot);
        // 第 5 步，将构建的左右子树接到 root 上
        root.left = leftSubTree;
        root.right = rightSubTree;
        return root;
    }

    // 寻找数组中，指定数字的索引（不存在重复数字）
    public static int findIndex(int[] arr, int figure) {
        int index = 0;
        while (index < arr.length) {
            if(arr[index] == figure)
                return index;
            index++;
        }
        return -1;  // 没找到
    }

    public static boolean arrContains(int[] arr, int var) {
        for (int i = 0; i < arr.length; i++) {
            if(var == arr[i])
                return true;
        }
        return false;
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
