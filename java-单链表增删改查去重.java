
/**
 * Created by pbc on 2019/8/9.
 */
//结点类Node
class Node {
    // TODO: 2019/8/9 重写equals 比较 
    @Override
    public boolean equals(Object obj) {
        Node n = (Node) obj;
        return data == n.data;
    }

    boolean ist;
    //数据
    int data;
    //指针，或者可以说是下一个结点
    Node next;

    //创建一个无参的构造，便于初始化时使用
    public Node() {
    }

    //创建一个有参数的构造方法，便于给结点传入数据
    public Node(int data) {
        this.data = data;
    }
}

//接口myList类，用来定义存储链表数据的方法
interface myList {
    //存储
    void add(int e);

    //插入
    void insert(int e, int index);

    //删除
    void delete(int index);

    //获取元素
    int get(int index);

    Node getNode(int index);

    //修改
    void modify(int e, int index);

    //获取元素个数
    int getSize();
}

public class ListNodeTest implements myList {
    //头结点，尾节点
    Node head, tail;
    //元素个数
    int size;

    public ListNodeTest() {
        head = new Node();
        tail = head;
        size = 0;
    }

    @Override
    //存储整数
    public void add(int e) {//形参e表示要添加的整数
        // TODO Auto-generated method stub
        //创建一个新的结点
        Node node = new Node(e);
        //设置尾结点的下一个节点为新结点
        tail.next = node;
        //设置尾结点为新结点
        tail = node;
        //元素个数加一
        size++;
    }

    @Override
    //插入整数
    public void insert(int e, int index) {//形参index表示结点所处位置的下标
        // TODO Auto-generated method stub
        checkindex(index);
        Node node = head.next;
        //找到下标index
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        //找到下标index-1
        Node node1 = head.next;
        for (int i = 0; i < index - 1; i++) {
            node1 = node1.next;
        }
        //创建一个新节点，为要插入的节点
        Node node2 = new Node(e);
        if (index == 0) {//如果插入的位置是第一个节点
            head.next = null;
            head.next = node2;
        } else {
            node1.next = null;
            node1.next = node2;
        }
        node2.next = node;
        size++;

    }

    @Override
    //删除整数
    public void delete(int index) {
        // TODO Auto-generated method stub
        checkindex(index);
        Node node = head.next;
        //找到下标index
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        //找到下标index-1
        Node node1 = head.next;
        for (int i = 0; i < index - 1; i++) {
            node1 = node1.next;
        }
        if (index != 0) {//假设index不等于0；不是第一个节点
            if (index == size - 1) {//假设是最后一个
                node1.next = null;
            } else {//假设不是最后一个
                node1.next = null;
                node1.next = node.next;
            }
            //设置node的指针为空，node的值为空
            node.next = null;
            node.data = 0;
        } else {//假设index等于0
            head.next = null;
            head.next = node.next;
            node.next = null;
            node.data = 0;
        }
        size--;
    }

    @Override
    //获取整数
    public int get(int index) {
        // TODO Auto-generated method stub
        //找到下表index
        Node node = head.next;
        //从头节点开始，依次向后查找
        for (int i = 0; i < index; i++) {

            node = node.next;
        }

        return node.data;
    }

    @Override
    //获取整数
    public Node getNode(int index) {
        // TODO Auto-generated method stub
        //找到下表index
        Node node = head.next;
        //从头节点开始，依次向后查找
        for (int i = 0; i < index; i++) {

            node = node.next;
        }

        return node;
    }

    @Override
    //修改整数
    public void modify(int e, int index) {
        // TODO Auto-generated method stub
        //找到下表index
        Node node = head.next;
        //从头节点开始，依次向后查找
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        // Node node1 = new Node(e);
        node.data = e;
    }

    //标记重复元素----
    // TODO: 2019/8/9 下一步遍历删除 ist=true的 节点
    public void cf(Node n) {
        // TODO Auto-generated method stub
        //找到下表index
        Node node = head.next;
        int j = 0;
        //从头节点开始，依次向后查找
        for (int i = 0; i < size; i++) {
            if (node.equals(n)) {
                j = ++j;
                System.out.println(node.toString());
                if (j >= 2)
                    node.ist = true;
            }
            node = node.next;
        }
        // Node node1 = new Node(e);
    }

    @Override
    //获取元素个数
    public int getSize() {
        // TODO Auto-generated method stub
        return size;
    }

    //检查index值是否合格
    public void checkindex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("参数不符合要求");
        }
    }

    public static void main(String[] args) {
        ListNodeTest link = new ListNodeTest();
        //添加数据
        link.add(2);
        link.add(3);
        link.add(1);
        link.add(3);
        link.add(9);
        link.add(3);
        //遍历列表
        for (int i = 0; i < link.getSize(); i++) {
            //取出元素
            int e = link.get(i);
            //输出元素
            System.out.println("第" + i + "个元素是：" + e);

        }
        for (int i = 0; i < link.getSize(); i++) {
            link.cf(link.getNode(i));
        }
        // TODO: 2019/8/9 遍历删除重复元素
        for (int i = 0; i < link.getSize(); i++) {
            Node n = link.getNode(i);
            if (n.ist)
                link.delete(i);
        }
        link.modify(3, 2);
    }
}
