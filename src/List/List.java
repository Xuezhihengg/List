package List;

/*
List接口表示了线性表ADT的公有方法
 */
public interface List<T> {
    public void insert(T item); //在当前位置插入元素
    public void append(T item); //在表尾插入元素
    public T remove(); //在当前位置删除元素
    public void setFirst(); //将cur指向第一个元素
    public void next(); //将cur指向当前元素的下一个元素
    public void prev(); //将cur指向当前元素的前一个元素
    public void setPos(int pos); //将cur设置到指向第pos个元素的位置
    public void setValue(T item); //将当前元素的值修改为输入值
    public T currValue(); //返回当前元素的值
    public boolean isEmpty(); //判断表是否为空
    public boolean isInList(); //判断cur是否指向表内
    public int length(); //返回表内元素个数
    public void print(); //打印线性表
}
