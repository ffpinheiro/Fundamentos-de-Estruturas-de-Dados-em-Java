public class HashSetClosedAddressing<T> implements SetInterface<T> {
    private final int DEFAULT_CAPACITY = 8;
    private LinkedList<T>[] table;
    private float LOAD_FACTOR = 0.75f;
    private int size;

    public HashSetClosedAddressing(){
        clear();
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
        table = new LinkedList[table.length * 2];
        size=0;
        for (Object entry : oldTable){
            if(entry == null){
                continue;
            }else{
                LinkedList<T> elements = (LinkedList<T>) entry;
                for(T element : elements.toArray()){
                    addToTable(element);
                }
            }
        }
    }
    private void addToTable(T element){
        int index = getHash(element);
        if (table[index] == null){
            table[index] = new LinkedList<>();
        }
        table[index].add(element);
        size++;
    }
    private int getHash(T element){
        return Math.abs(element.hashCode()) % table.length;
    }
    private boolean isLoadFactorExceeded(){
        return (float) (size / table.length) > LOAD_FACTOR;
    }

    @Override
    public boolean remove(T element) {
        int index = getHash(element);
        if(table[index] == null){
            return false;
        }
        if (this.contains(element)){
            table[index].remove(element);
            size--;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.table = new LinkedList[DEFAULT_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            this.table[i] = new LinkedList<>();
        }
        size=0;
    }

    @Override
    public boolean contains(T element) {
        int index = getHash(element);
        LinkedList<T> linkedList = table[index];
        if (linkedList == null){
            return false;
        }else{
            return linkedList.contains(element);
        }
    }

    @Override
    public int size() {
        return (this.size);
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }
}
