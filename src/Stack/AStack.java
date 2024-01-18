package Stack;

/*
顺序栈
 */
public class AStack<T> implements Stack<T> {
    private static final int defaultSize = 10;
    private int size;
    private int top;     //顺序栈的top指向栈顶元素的下一个元素
    private T[] listArray;

    public AStack() {
        setup(defaultSize);
    }

    public AStack(int size) {
        setup(size);
    }

    private void setup(int size) {
        this.size = size;
        top = 0;
        listArray = (T[]) new Object[size];
    }

    @Override
    public void push(T item) {
        //如果栈满了应报错
        assert top < size : "Stack is full";
        listArray[top++] = item;
    }

    @Override
    public T pop() {
        //如果表空了应报错
        assert !isEmpty() : "Stack is empty";
        return listArray[--top];
    }

    @Override
    public T topValue() {
        //如果表空了应报错
        assert !isEmpty() : "Stack is empty";
        return listArray[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }
}
