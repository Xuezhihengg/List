package List;

/*
双链表
 */
class DLink<T>{
    private T value;
    private DLink<T> next;
    private DLink<T> prev;
    DLink(T value,DLink next,DLink prev){
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
    DLink(DLink next,DLink prev){
        this.next = next;
        this.prev = prev;
    }
    public DLink<T> next(){
        return next;
    }
    public DLink<T> prev(){
        return prev;
    }
    public T getValue(){
        return value;
    }
    public void setNext(DLink<T> next){
        this.next = next;
    }

    public void setPrev(DLink<T> prev) {
        this.prev = prev;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
public class DList<T> implements List<T> {
    private DLink<T> head;
    private DLink<T> tail;
    private DLink<T> cur;
    public DList(){
        head = tail = cur = new DLink<T>(null,null);
    }


    @Override
    public void insert(T item) {
        assert cur != null : "No cur";
        cur.setNext(new DLink<T>(item, cur.next(), cur));
        //如果不是在表尾添加元素的话还需使原来是cur.next()（现在是cur.next().next()）的结点的prev指向新结点
        if (cur.next().next() != null) cur.next().next().setPrev(cur.next());
        //如果在表尾添加元素则应更新tail
        if (cur == tail) {
            tail = tail.next();
        }
    }

    @Override
    public void append(T item) {
        tail.setNext(new DLink<T>(item,null,tail));
        tail = tail.next();

    }

    @Override
    public T remove() {
        assert isInList() : "Bad cur";
        T temp = cur.next().getValue();
        //如果不是在表尾删除元素的话还需使cur.next().next()结点的prev指向cur结点
        if(cur.next().next()!=null){
            cur.next().next().setPrev(cur);
        }else {//如果在表尾删除元素则应更新tail
            tail = cur;
        }
        //使cur的next指向cur.next().next()
        cur.setNext(cur.next().next());
        return temp;
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
        cur = cur.prev();
    }

    @Override
    public void setPos(int pos) {
        cur = head;
        for(int i=0;i<pos&&cur.next()!=null;i++){
            cur = cur.next();
        }
    }

    @Override
    public void setValue(T item) {
        assert isInList() : "Bad cur";
        cur.next().setValue(item);

    }

    @Override
    public T currValue() {
        assert isInList() : "Bad cur";
        return cur.next().getValue();
    }

    @Override
    public boolean isEmpty() {
        return head == cur;
    }

    @Override
    public boolean isInList() {
        return cur!=null && cur.next()!=null;
    }

    @Override
    public int length() {
        int length = 0;
        DLink temp = head.next(); //哑结点不算在内
        while (temp!=null){
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
        System.out.println(")");
    }
}
