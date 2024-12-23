public class HashSetOpenAddressing <T> implements SetInterface<T>{
    private Object[] table;
    private final float LOAD_FACTOR = 0.75f;
    private final int INITIAL_CAPACITY;
    private int size;
    public HashSetOpenAddressing(int initialCapacity){
        if(initialCapacity <= 0){
            throw new IllegalArgumentException("Table capacity should be greater tha zero: " + initialCapacity);
        }
        INITIAL_CAPACITY = initialCapacity;
        table = new Object[INITIAL_CAPACITY];
    }
    private boolean isLoadFactorExceeded(){
        return (double) size / table.length > LOAD_FACTOR;
    }
    private int getHash(T element){
        return Math.abs( element.hashCode() ) % table.length;
    }

    @Override
    public void add(T element) {
        if (contains(element)){
            return;
        }
        if (isLoadFactorExceeded()){
            resizeTable();
        }
        addToTable(element);
    }
    private void resizeTable(){
        Object[] oldTable = table;
        table = new Object[table.length * 2];
        for (Object entry : oldTable){
            if(entry == null){
                continue;
            }else{
                T element = (T) entry;
                addToTable(element);
            }
        }
    }
    private void addToTable(T element){
        int index = getHash(element);
        while (table[index] != null){
            index = (index + 1) % table.length;
        }
        table[index] = element;
        size++;
    }
    @Override
    public boolean remove(T element) {
        int index = getHash(element);
        int checkedElements = 0;
        while(checkedElements < table.length){
            if (table[index] != null && table[index].equals(element)){
                table[index] = null;
                size--;
                return true;
            }
            index = (index + 1) % table.length;
            checkedElements++;
        }
        return false;
    }

    @Override
    public void clear() {
        table = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        int index = getHash(element);
        int checkedElements = 0;
        while(checkedElements < table.length){
            if (table[index] != null && table[index].equals(element)){
                return true;
            }
            index = (index + 1) % table.length;
            checkedElements++;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
}
