public class HashMapClosedAddressing <K,V> implements MapInterface<K,V> {
    private final int DEFAULT_CAPACITY = 8;
    private LinkedList<Entry<K,V>>[] table;
    private float LOAD_FACTOR = 0.75f;
    private int size;

    public HashMapClosedAddressing() {
        initHashMap(DEFAULT_CAPACITY);
    }

    private int getHash(K key){
        return Math.abs(key.hashCode()) % table.length;
    }
    private boolean isLoadFactorExceeded(){
        return (double) size / table.length > LOAD_FACTOR;
    }

    @Override
    public V put(K key, V value) {
        if (isLoadFactorExceeded()){
            resizeTable();
        }
        return addOrUpdateToTable(key,value);
    }
    private void resizeTable(){
        LinkedList<Entry<K,V>>[] oldTable = table;
        initHashMap(table.length * 2);
        for (LinkedList<Entry<K,V>> tableItems : oldTable){
            if (tableItems == null){
                continue;
            }
            for (Entry<K,V> entry : tableItems.toArray(Entry.class)){
                addOrUpdateToTable(entry.getKey(), entry.getValue());
            }
        }
    }
    private V addOrUpdateToTable(K key, V value){
        int index = getHash(key);
        Entry<K,V> entry = new Entry<>(key, value);
        LinkedList<Entry<K,V>> tableItems = table[index];
        if (tableItems.contains(entry)){
            tableItems.remove(entry);
        }else {
            size++;
            tableItems.add(entry);
        }
        return entry.getValue();
    }

    @Override
    public V get(K key) {
        int index = getHash(key);
        for (Entry<K,V> entry : table[index].toArray(Entry.class)){
            if (entry != null && entry.getKey().equals(key)){
                table[index].remove(entry);
                size--;
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = getHash(key);
        for (Entry<K,V> entry : table[index].toArray(Entry.class)){
            if (entry != null && entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getHash(key);
        for (Entry<K,V> entry : table[index].toArray(Entry.class)){
            if (entry != null && entry.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (LinkedList<Entry<K,V>> tableItems : table){
            for (Entry<K,V> entry : tableItems.toArray(Entry.class)){
                if (entry != null && entry.getValue().equals(value)){
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public SetInterface<K> keySet() {
        SetInterface<K> keySet = new HashSetOpenAddressing<>(size);
        for (LinkedList<Entry<K,V>> tableItems : table){
            for (Entry<K,V> entry : tableItems.toArray(Entry.class)){
                if (entry != null){
                    keySet.add(entry.getKey());
                }
            }
        }
        return keySet;
    }

    @Override
    public SetInterface<Entry<K, V>> entrySet() {
        SetInterface<Entry<K,V>> entrySet = new HashSetOpenAddressing<>(size);
        for (LinkedList<Entry<K,V>> tableItems : table){
            for (Entry<K,V> entry : tableItems.toArray(Entry.class)){
                if (entry != null){
                    entrySet.add(entry);
                }
            }
        }
        return entrySet;
    }

    @Override
    public int size() {
        return (this.size);
    }
    private void initHashMap(int tableSize){
        if (tableSize <= 0){
            return;
        }
        table = new LinkedList[tableSize];
        size=0;
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }
}
