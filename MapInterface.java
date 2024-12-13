public interface MapInterface <K,V> {
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    SetInterface<K> keySet();
    SetInterface<Entry<K,V>> entrySet();
    int size();
}
