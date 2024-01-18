package Stack;
public interface Stack<T> {
    public void push(T item); //入栈
    public T pop();           //出栈
    public T topValue();      //返回栈顶元素
    public boolean isEmpty();      //判断是否为空
}
