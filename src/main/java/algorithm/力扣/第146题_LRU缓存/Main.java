package algorithm.力扣.第146题_LRU缓存;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 天章
 * @Date 2024/3/7 14:48
 * Description:
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 */
public class Main {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 0); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int var1 = lRUCache.get(1);// 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        int var2 = lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        int var3 = lRUCache.get(1);    // 返回 -1 (未找到)
        int var4 = lRUCache.get(3);    // 返回 3
        int var5 = lRUCache.get(4);    // 返回 4
    }

    // 我的 LRUCache 类
    private static class LRUCache {

        private Sentinel sentinel;

        public LRUCache(int capacity) {
            this.sentinel = new Sentinel(capacity);
        }

        public void put(int key, int value) {
            sentinel.addNew(key, value);
        }

        public int get(int key) {
            return sentinel.get(key);
        }

        private static class Sentinel {

            private final int threshold;
            private Map<Integer, Node> nodeMap = new HashMap<>();
            private Node head = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE);
            private Node end = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE);

            public Sentinel(int threshold) {
                this.threshold = threshold;
                head.next = end;
                end.last = head;
            }

            public void addNew(int key, int value) {
                // 先看是否存在
                Node exist = nodeMap.getOrDefault(key, null);
                if(exist != null) {  // 存在
                    exist.value = value;
                    putToHead(exist);
                    return;
                }
                // 不存在
                if(nodeMap.size() >= threshold) {
                    del();
                }
                Node newOne = new Node(key, value);
                nodeMap.put(key, newOne);
                Node old = head.next;
                head.next = newOne;
                newOne.last = head;
                newOne.next = old;
                old.last = newOne;
            }

            public void del() {   // 删除队列尾的数据
                Node tail = end.last;   // 从双向队列中删除
                Node tailLast = tail.last;
                tailLast.next = end;
                end.last = tailLast;
                nodeMap.remove(tail.key);  // 从 nodeMap 中删除
            }

            public int get(int key) {
                Node value = nodeMap.getOrDefault(key, null);
                if(value != null) {
                    putToHead(value);
                    return value.value;
                }
                return -1;
            }

            public void putToHead(Node node) {
                Node last = node.last;
                Node next = node.next;
                last.next = next;
                next.last = last;
                // 把 node 放到队头
                Node old = head.next;
                head.next = node;
                node.next = old;
                node.last = head;
                old.last = node;
            }

            private static class Node {
                int key;
                int value;
                Node next;
                Node last;

                public Node(int key, int value) {
                    this.key = key;
                    this.value = value;
                }
            }

        }
    }

}
