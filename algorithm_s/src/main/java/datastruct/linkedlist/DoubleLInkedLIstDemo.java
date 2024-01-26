package datastruct.linkedlist;

public class DoubleLInkedLIstDemo {
    public static void main(String[] args) {

        //双向链表的测试
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改测试
        HeroNode2 newHeroNode = new HeroNode2(4,"--","--");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的");
        doubleLinkedList.list();

        //删除测试
        doubleLinkedList.del(3);
        System.out.println("删除后");
        doubleLinkedList.list();



    }
}


//创建一个双向链表的类
class DoubleLinkedList{

    //先初始化一个头结点，头结点不动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头结点
    public HeroNode2 getHead(){
        return head;
    }

    //遍历双向链表的方法
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表的空的");
            return ;
        }
        //因为头结点不能动，所以需要一个辅助节点来遍历
        HeroNode2 temp =head.next;
        while(true){
            //判断是否到了链表的最后
            if(temp==null){
                break;
            }
            //输出结点的信息
            System.out.println(temp);

            //后移temp
            temp=temp.next;
        }
    }


    //添加一个结点到双向链表的最后
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode2 temp=head;

        //遍历链表找到最后
        while(true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        //当退出循环时，temp就指向了链表的最后
        temp.next=heroNode;
        heroNode.pre=temp;
    }

    //修改一个结点的内容
    public void update(HeroNode2 newheroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表是空的");
            return ;
        }

        //找到需要修改的节点，根据no编写
        //定义一个辅助变量
        HeroNode2 temp =head.next;
        boolean flag =false; //表示是否找到了这个节点
        while(true){
            if(temp==null){
                break; //遍历完链表
            }
            if(temp.no== newheroNode.no){
                //找到了
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.name=newheroNode.name;
            temp.nickname= newheroNode.nickname;
        }else{
            System.out.println("没有找到");
        }

    }


    //从双向链表中删除一个节点
    //对于双向链表，直接找到要删除的节点，找到后，自我删除
    public  void del(int no){
        //判断空
        if(head.next==null){
            System.out.println("链表空的");
            return ;
        }
        HeroNode2 temp=head.next;
        boolean flag = false;  //表示是否找到该节点
        while(true){
            if(temp.next==null){  //已经到最后了
                break;
            }
            if(temp.no==no){ //找到了待删除节点的前一个节点temp
                flag=true;
                break;

            }
            temp=temp.next;
        }

        //判断flag
        if(flag){  //找到
            //删除
            temp.pre.next=temp.next;

            //如果是删除最后一个节点，就不需要执行下面这句话，否则出现空指针异常
            if(temp.next!=null) {
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("要删除的 %d 节点不存在\n",no);
        }
    }


}


//定义HeroNode2，每一个HeroNode对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点，默认为Null
    public HeroNode2 pre; //指向前一个节点,默认为mull

    //构造器
    public HeroNode2(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    //为了显示方法，重新toString


    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}