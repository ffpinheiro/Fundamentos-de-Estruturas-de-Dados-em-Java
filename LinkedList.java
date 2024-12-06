public class LinkedList<T> implements List<T>{

    private Node<T> head;
    private int size;

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if(this.head == null){
            this.head = newNode;
        }else {
            Node<T> auxNode = head;
            while(auxNode.getNext() != null){
                auxNode = auxNode.getNext();
            }
            auxNode.setNext(newNode);
        }
        this.size++;
    }

    @Override
    public boolean remove(T element) {
        if (head == null){
            return false;
        }
        if(head.getInfo().equals(element)){
            head = head.getNext();
        }else{
            Node<T> auxNode = head;
            while (auxNode.getNext() != null){
//                Node<T> nextNode = auxNode.getNext();
                if(auxNode.getNext().getInfo().equals(element)){
                    auxNode.setNext(auxNode.getNext().getNext());
                    break;
                }else{
                    auxNode = auxNode.getNext();
                }
            }
        }
        size--;
        return true;
    }

    @Override
    public boolean contains(T element) {
        if (this.head == null){
            return false;
        }
        Node<T> auxNode = this.head;
        while (auxNode.getNext() != null){
            if (auxNode.getInfo().equals(element)){
                return true;
            }
            auxNode = auxNode.getNext();
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        int index = 0;
        if (this.head == null){
            return -1;
        }
        Node<T> auxNode = this.head;
        while (auxNode != null){
            if (auxNode.getInfo().equals(element)){
                return index;
            }
            auxNode = auxNode.getNext();
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T get(int index) {
        if(index>=0 && index <= this.size-1){
            int counter=0;
            Node<T> auxNode = this.head;
            while (auxNode != null){
                if (counter==index){
                    return auxNode.getInfo();
                }
                auxNode = auxNode.getNext();
                counter++;
            }
        }
        throw new IndexOutOfBoundsException("Index is out of bounds of list size: " +this.size);
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }
}
