package Stack;

/*
链式栈
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
public class LStack<T> implements Stack<T>{
    private Link<T> top; //top是链表的头，同时也是栈顶
    public LStack(){
        top = null; //链式栈不需要哑结点，所以top初始化为null
    }

    @Override
    public void push(T item) {  //push为链表头部新添一个结点
        top = new Link<T>(item,top);
    }

    @Override
    public T pop() {   //pop抛弃掉原来链表的头
        //如果链表为空应报错
        assert !isEmpty() : "Stack is empty";
        T temp = top.getValue();
        top = top.next();
        return temp;
    }

    @Override
    public T topValue() {
        //如果链表为空应报错
        assert !isEmpty() : "Stack is empty";
        return top.getValue();
    }

    @Override
    public boolean isEmpty() {
        return top==null;
    }
}
