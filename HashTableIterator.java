import java.util.Iterator;

/**
 * HashTableIterator
 */
public class HashTableIterator<K> implements Iterator<K>{
    class Node{
        private K key;
        private Node next;
        public Node(K key, Node next){
            this.key = key;
            this.next = next;
        }
    }
    Node head;

    public HashTableIterator(){
        head = null;
    }

    public void add(K element){
        head = new Node(element, head);
    }
    
    public boolean hasNext() {
        return head != null;
    }

    public K next() {
        K output = head.key;
        head = head.next;
        return output;
    }
}