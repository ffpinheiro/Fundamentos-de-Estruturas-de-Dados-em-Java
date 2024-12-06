public interface StackInterface<T>{
    boolean empty();
    T peek();
    T pop();
    T push(T element);
    int search(T element);
}
