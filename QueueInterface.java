public interface QueueInterface <T>{
    void enqueue(T element);
    T dequeue();
    T peek();
    int size();
}
