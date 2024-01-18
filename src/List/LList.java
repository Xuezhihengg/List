package List;

/*
单链表
 */
class Link<T>{
    //结点类，包含2个成员变量，2个构造方法，4个get与set方法
    private T value;
    private Link<T> next;
    Link(T value,Link<T> next){
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
public class LList<T> implements List<T> {
    private Link<T> head;
    private Link<T> tail;
    private Link<T> cur;
    public LList(){
        setup();
    }
    private void setup(){
        head = tail = cur = new Link<T>(null);  //初始化就是将head与cur均指向哑结点
    }
    @Override
    public void insert(T item) {  //在cur后插入结点,但在逻辑上是在“当前结点”前插入，这里的cur指向的是当前结点的前驱结点
        //如果cur为null应报错
        assert cur!=null : "No cur";
        //先生成一个新结点，其next指向cur的next，再将cur的next指向这个新结点
        cur.setNext(new Link<T>(item,cur.next()));
        //如果cur就是尾结点，则应更新tail
        if(cur ==tail){
            tail=tail.next();
        }
    }

    @Override
    public void append(T item) {
        tail.setNext(new Link<T>(item,null));
        tail=tail.next();

    }

    @Override
    public T remove() {  //删去cur.next()，但在逻辑上是删去“当前结点”
        //如果cur不合法应报错  (由于会用到cur.next().next()，所以不能使cur.next()为null，这在isInList()有所体现)
        assert isInList() : "Bad cur";
        T item = cur.next().getValue();
        //如果要删的结点是尾结点，则先将尾结点前移一个位置
        if(tail == cur.next()) tail = cur;
        cur.setNext(cur.next().next());
        return item;
    }

    @Override
    public void setFirst() {
        cur = head;
    }

    @Override
    public void next() {
        cur = cur.next();
    }

    @Override
    public void prev() {
        //如果cur为null或head，则无前驱结点
        if(cur==null||cur==head) return;
        //创建一个临时结点temp，从头开始遍历链表直至找到cur的前驱结点
        Link<T> temp = head;
        while (temp!=null&&temp.next()!=cur){ //temp不等于null，否则就没有temp.next()
            temp = temp.next();
        }
        cur = temp;
    }

    @Override
    public void setPos(int pos) {
        cur = head;
        for(int i=0;cur!=null&&i<pos;i++){
            cur = cur.next();
        }
    }

    @Override
    public void setValue(T item) {  //cur指向的是当前结点的前驱结点
        //cur和cur.next()不能为null
        assert isInList() : "Bad cur";
        cur.next().setValue(item);
    }

    @Override
    public T currValue() {
        //cur和cur.next()不能为null
        assert isInList() : "Bad cur";
        return cur.next().getValue();
    }
    @Override
    public boolean isEmpty() {
        return head.next()==null;
    }

    @Override
    public boolean isInList() {  //用于remove方法中检测是否合法
        return cur!=null&&cur.next()!=null;
    }

    @Override
    public int length() {
        int length = 0;
        Link temp = head.next(); //哑结点不算在内
        while (temp !=null){
            temp = temp.next();
            length++;
        }
        return length;
    }

    @Override
    public void print() {
        System.out.print("( ");
        for(setFirst();isInList();next()){
            System.out.print(currValue()+" ");
        }
        System.out.print(")");
    }
}
