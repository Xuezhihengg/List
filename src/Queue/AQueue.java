package Queue;
/*
顺序队列
 */
public class AQueue<T> implements Queue<T>{
    private static final int defaultSize = 10;
    private int size;   //需要创建的数组的大小，这个大小是比实际能存储的数据大1的(见setup方法)，因为需要多一个空间来表示一种状态
    private int front;  //队首元素的前驱
    private int rear;   //队尾元素
    private T[] listArray;
    public AQueue(){setup(defaultSize);}
    public AQueue(int sz){setup(sz);} //这里输入的sz是希望能储存的元素个数
    private void setup(int sz) {
        size = sz + 1;
        front = rear = 0;
        listArray = (T[]) new Object[size];
    }
    @Override
    public void enqueue(T itme) {
        //入队如果队列满了应报错
        assert !isFull() : "Queue is full";
        rear = (rear+1)%size;
        listArray[rear] = itme;
    }

    @Override
    public T dequeue() {
        //出队如果队列是空应报错
        assert !isEmpty() : "Queue is empty";
        front = (front+1)%size;
        return listArray[front];
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public boolean isFull() {
        return (rear+1)%size==front;
    }
}
