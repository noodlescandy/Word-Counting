import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * HashTable
 */
public class HashTable<K, V> implements Table<K, V>{

    /*class keyNode{
        private K key;
        private keyNode next;
        public keyNode(K key, keyNode next) {
            this.key = key;
            this.next = next;
        }
    }
    class valueNode{
        private V value;
        private valueNode next;
        public valueNode(V value, valueNode next) {
            this.value = value;
            this.next = next;
        }
    }*/
    /*class Node<E>{
        private E data;
        private Node<E> next;
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }*/
    class Node<E> {
        public E data;
        public Node<E> next;
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<K>[] keys;
    private Node<V>[] values;
    private HashTableIterator<K> it;
    
    public HashTable(int size){
        //keys = (Node[]) new Object[size];
        //values = (Node[]) new Object[size];
        keys = new Node[size];
        values = new Node[size];
        it = new HashTableIterator<K>();
    }

    public void put(K key, V value) {
        int slot = Math.abs(key.hashCode() % keys.length);
        if (keys[slot] == null) {
            keys[slot] = new Node<K>(key, null);
            values[slot] = new Node<V>(value, null);
            it.add(key);
        } 
        else {
            Node<K> currentKey = keys[slot];
            Node<V> currentValue = values[slot];
            while (currentKey.next != null) {
                if(currentKey.data.equals(key)){ // key exists
                    currentValue.data = value;
                    return;
                }
                currentKey = currentKey.next;
                currentValue = currentValue.next;
            }
            // final comparison on last element
            if(currentKey.data.equals(key)){
                currentValue.data = value;
            }
            else{
                //currentKey.next = new Node(key, value, null);
                currentKey.next = new Node<K>(key, null);
                currentValue.next = new Node<V>(value, null);
                it.add(key);
            }
        }
    }

    public V get(K key) {
        int index = Math.abs(key.hashCode() % keys.length);
        Node<K> currentKey = keys[index];
        Node<V> currentValue = values[index];
        while (currentKey != null) {
            if (currentKey.data.equals(key)) {
                return currentValue.data;
            }
            currentKey = currentKey.next;
            currentValue = currentValue.next;
        }
        return null;
    }

    /* 
    public void printKeys(){
        System.out.println("--------\nKeys:");
        for (Node<K> node : keys) {
            if(node != null){
                System.out.println(node.data);
                Node c = node;
                while (c.next != null) {
                    c=c.next;
                    System.out.println(c.data);
                }
            }
            else{
                System.out.println("null");
            }
        }
        System.out.println("---------");
    }

    public void printVals(){
        System.out.println("--------\nValues:");
        for (Node<V> node : values) {
            if(node != null){
                System.out.println(node.data);
                Node c = node;
                while (c.next != null) {
                    c=c.next;
                    System.out.println(c.data);
                }
            }
            else{
                System.out.println("null");
            }
        }
        System.out.println("---------");
    }
*/
    public Iterator<K> iterator() {
        return it;
    }
}