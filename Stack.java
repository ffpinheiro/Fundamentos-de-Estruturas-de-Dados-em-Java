public class Stack <T> implements  StackInterface<T> {
    private Node<T> top;
    @Override
    public boolean empty() {
        return (top == null);
    }

    @Override
    public T peek() {
        if (top != null){
            return top.getInfo();
        }else{
            return null;
        }
    }

    @Override
    public T pop() {
        T info = this.top.getInfo();
        if(this.top != null){
            this.top = this.top.getNext();
            return info;
        }
        return null;
    }

    @Override
    public T push(T element){
        Node<T> auxNode = new Node<>(element);
        if(this.top == null){
            this.top = auxNode;
        }else{
            auxNode.setNext(top);
            this.top = auxNode;
        }
        return this.peek();
    }

    @Override
    public int search(Object element) {
        int index=-1;
        Node<T> auxNode = top;
        while(auxNode != null){
            index++;
            if(auxNode.getInfo().equals(element)){
                return index;
            }
            auxNode = auxNode.getNext();
        }
        return index;
    }
}
