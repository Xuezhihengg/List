package Queue;

/*
链式队列
 */
class Link<T>{
    private T value;
    private Link<T> next;
    Link(T value, Link<T> next){
        this.value = value;
        this.next = next;
    }
    Link(Link<T> next){
        this.next = next;
    }
    Link<T> next(){
        return next;
    }
    void setNext(Link<T> next){
        this.next = next;
    }
    T getValue(){
        return value;
    }
    void setValue(T value){
        this.value = value;
    }
}
public class LQueue <T> implements Queue<T>{
    private Link<T> front; //队首(对应链表的头部)  因为队首需要删除操作，链表头部适于插入和删除操作
    private Link<T> rear;  //对尾(对应链表的尾部)  因为队尾需要插入操作，链表尾部适于插入操作
    public LQueue(){front=rear=null;}
    @Override
    public void enqueue(T itme) {
        //如果队列中无元素，则创建新结点
        if(rear==null) {
            front = rear = new Link<T>(itme, null);
        }else {
            rear.setNext(new Link<T>(itme, null));
            rear = rear.next();
        }
    }

    @Override
    public T dequeue() {
        //出队如果队列为空应报错
        assert !isEmpty() : "Queue is empty";
        T temp = front.getValue();
        front = front.next();
        //如果使队列中唯一的元素出队，则应更新rear
        if(front==null) rear = null;
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return front==null&&rear==null;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
