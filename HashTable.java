import java.util.Iterator;

public class HashTable<K, V> implements Table<K, V>{
    class Node<E, F> {
        public E key;
        public F value;
        public Node<E, F> next;
        public Node(E key, F value, Node<E, F> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] hashTable;
    private HashTableIterator<K> it;
    
    public HashTable(int size){
        hashTable = new Node[size];
        it = new HashTableIterator<K>();
    }

    public void put(K key, V value) {
        int slot = Math.abs(key.hashCode() % hashTable.length);
        if (hashTable[slot] == null) {
            hashTable[slot] = new Node<K, V>(key, value, null);
            it.add(key);
        } 
        else {
            Node<K, V> current = hashTable[slot];
            while (current.next != null) {
                if(current.key.equals(key)){ //
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            if(current.key.equals(key)){ //
                current.value = value;
            }
            else{
                current.next = new Node<K, V>(key, value, null);
                it.add(key);
            }
        }
    }

    public V get(K key) {
        int index = Math.abs(key.hashCode() % hashTable.length);
        Node<K, V> current = hashTable[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public Iterator<K> iterator() {
        return it;
    }
}