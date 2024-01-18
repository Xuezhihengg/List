package Queue;

public interface Queue<T> {
    public void enqueue(T itme);  //入队
    public T dequeue();           //出队
    public boolean isEmpty();
    public boolean isFull();
}
