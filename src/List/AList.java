package List;

/*
顺序表
 */
public class AList<T> implements List<T> {
    private static final int defaultSize = 10; //默认顺序表的最大长度
    private int MaxSize;                       //顺序表的最大长度
    private int NumsInList;                    //顺序表中实际的元素个数
    private int cur;                           //当前元素指针
    private T[] listArray;                //数组

    public AList() {
        setup(defaultSize);
    }

    public AList(int size) {
        setup(size);
    }

    private void setup(int size) {
        MaxSize = size;
        NumsInList = cur = 0;
        listArray = (T[]) new Object[MaxSize];
    }

    @Override
    public void insert(T item) {
        //如果表满了应报错
        assert !isFull() : "List.List.AList is full";   //这里不能等于，等于就是表满了，不能再添加元素了
        //如果cur位置不合法应报错
        assert isInList() : "Bad value for cur";
        //cur以后每个元素都向后移一格，这个操作应从后向前
        for (int i = NumsInList; i > cur; i--) {
            listArray[i ] = listArray[i -1];
        }
        listArray[cur] = item; //为了保证cur指向的元素不变，cur要自加
        NumsInList++;            //更新表中元素个数
    }

    @Override
    public void append(T item) {
        //如果表满了应报错
        assert !isFull() : "List.List.AList is full";
        listArray[NumsInList++] = item;   //append操作和cur无关
    }

    @Override
    public T remove() {
        //如果表是空的应报错
        assert !isEmpty() : "Can't delete from empty list";
        //如果cur位置不合法应报错
        assert isInList() : "Bad value for cur";
        //先将cur所指元素的值记下，后面返回要用到
        T it = listArray[cur];
        //从cur开始(不包括cur)每一个元素都前移一格，将cur所指的元素覆盖掉
        for (int i = cur; i < NumsInList - 1; i++) {
            listArray[i] = listArray[i + 1];
        }
        NumsInList--;
        return it;
    }

    @Override
    public void setFirst() {
        cur = 0;
    }

    @Override
    public void next() {
        cur++;
    }

    @Override
    public void prev() {
        cur--;
    }

    @Override
    public void setPos(int pos) {  //pos就是新“当前结点”的下标(下标从0开始计算)
        cur = pos;
    }

    @Override
    public void setValue(T item) {
        //如果cur位置不合法应报错
        assert isInList() : "Bad value for cur";
        listArray[cur] = item;
    }

    @Override
    public T currValue() {
        //如果cur位置不合法应报错
        assert isInList() : "Bad value for cur";
        return listArray[cur];
    }

    public boolean isFull() {
        return NumsInList >= MaxSize;
    }

    @Override
    public boolean isEmpty() {
        return NumsInList == 0;
    }

    @Override
    public boolean isInList() {  //判断cur是否合法
        return cur >= 0 && cur <= NumsInList;
    }

    @Override
    public int length() {       //返回表中元素的实际个数
        return NumsInList;
    }
    @Override
    public void print() {
        System.out.print("( ");
        for (setFirst(); isInList(); next()) {
            System.out.print(currValue() + " ");
        }
        System.out.print(")");

    }
}
