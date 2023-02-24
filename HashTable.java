import java.util.Iterator;

/**
 * HashTable
 */
public class HashTable<K, V> implements Table<K, V>{
    class Node{
        private K key;
        private V value;
        private Node next;
        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    
    // assuming objects used as keys correctly implement
    // hashCode, equals, and toString methods
    private Node[] hashTable;
    
    public HashTable(int size){
        hashTable = (Node[]) new Object[size];
    }

    public void put(K key, V value) {
        int slot = key.hashCode() % hashTable.length;
        if (hashTable[slot] == null) {
            hashTable[slot] = new Node(key, value, null);
        } else {
            // chaining
            Node current = hashTable[slot];
            while (current.next != null) {
                if(current.key.equals(key)){ // key exists
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            // final comparison on last element
            if(current.key.equals(key)){
                current.value = value;
            }
            else{
                current.next = new Node(key, value, null);
            }
        }
    }

    public V get(K key) {
        int index = key.hashCode() % hashTable.length;
        Node current = hashTable[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public Iterator<K> iterator() {
        // returns iterator object, allowing you to get each of the key values in turn.
        return new HashTableIterator<K>();
    }

    
}