import java.util.HashMap;
import java.util.Map;
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class Node {
    int value;
    int key;
    Node before;
    Node next;
    public Node() {};

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.before = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    Node head;
    Node end;

    public DoublyLinkedList() {
        head = new Node();
        end = new Node();
        this.head.next = this.end;
        this.end.before = head;
    }

    public void insertCache(Node n) {
        n.before = head;
        n.next = head.next;
        head.next.before = n;
        head.next = n;
    }

    public void remove(Node n) {
        n.before.next = n.next;
        n.next.before = n.before;
    }

    public int eliminateCache() {
        Node n = end.before;
        int key = n.key;
        remove(n);

        return key;
    }
}

public class LRUCache {
    Map<Integer, Node> cache;
    DoublyLinkedList list;
    int capacity;

    /**
     * Create the main components in LRU Cache.
     * @param capacity
     *      this integer is the limit of value storage.
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.list = new DoublyLinkedList();
    }

    /**
     * Output the result if this cache contains the input key integer that clients want.
     * @param key
     *      input key integer.
     * @return
     *      report the value in the cache of corresponding keys. if not then return -1.
     */
    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;
        update(key, cache.get(key));

        return cache.get(key).value;
    }

    /**
     * update the cache every call of get() method.
     * @param key
     *      key value.
     * @param node
     *      cache.
     */
    private void update(int key, Node node) {
        list.remove(node);
        list.insertCache(node);
        cache.put(key, node);
    }

    /**
     * inset the new node in the cache with new value and new key.
     * @param key
     *      Integer.
     * @param value
     *      Integer.
     */
    public void put(int key, int value) {
        Node node = new Node(key, value);
        if(cache.containsKey(key)) {
            list.remove(cache.get(key));
        } else if(cache.size() >= capacity){
            int tempK = list.eliminateCache();
            cache.remove(tempK);
        }
        list.insertCache(node);
        cache.put(key, node);
    }
}

