package algorithm.力扣.第25题_K个一组翻转链表;

/**
 * @author 天章
 * @Date 2024/3/5 13:22
 * Description:
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 */
public class Main {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2));
        reverseKGroup(head, 2);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode node = head;
        int length = 0;
        while (node != null) {   // 先遍历求一下 length，虽然可以放在处理过程中顺便统计操作，但是这样大大增加了代码逻辑复杂性
            length++;
            node = node.next;
        }
        // 开始处理
        int batch = 0;
        while ((batch + 1) * k <= length) {
            head = reverseInRange(head, batch * k, k);
            batch++;
        }
        showList(head);
        return head;
    }

    // 翻转一个链表指定范围内的节点，不考虑指定范围是否存在
    public static ListNode reverseInRange(ListNode head, int beginIndex, int count) {
        if(count <= 1)    // 翻转一个有什么意义？
            return head;
        // 先找到要反转的所有节点中，最头的节点的头节点，以便于后续续接整个链表
        ListNode leader = beginIndex == 0 ? null : findSpecNode(head, beginIndex - 1);
        ListNode begin = findSpecNode(head, beginIndex);
        ListNode last = begin;
        ListNode curr = begin.next;
        int num = 0;
        while (num < count - 1) {   // count 最小是 2, 所以这个 while 一定会至少执行一次
            begin.next = curr.next;
            curr.next = last;
            last = curr;
            curr = begin.next;
            num++;
        }
        // 续接"头"节点
        if(leader != null)
            leader.next = last;
        else
            head = last;
        return head;
    }

    // 从一个完整链表中，找到指定位置的节点，并返回引用. index 从 0 开始
    public static ListNode findSpecNode(ListNode head, int index) {
        if(index < 0)
            throw new RuntimeException("无效的节点序号");
        int count = 0;
        ListNode now = head;
        while (true) {   // 因为进来时 count <= index，所以必定会 return
            if(count == index)
                return now;
            now = now.next;
            count++;
        }
    }

    public static void showList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
