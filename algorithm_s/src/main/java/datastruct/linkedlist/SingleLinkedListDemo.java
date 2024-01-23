package datastruct.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();


        //测试
        //先创建节点
//        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
//        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
//        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
//        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");



        //按照编号加入
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        //显示
        singleLinkedList.list();



        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "卢--", "玉--");
        singleLinkedList.update(newHeroNode);

        //显示
        System.out.println("修改后的-");
        singleLinkedList.list();

        //删除一个节点
        singleLinkedList.del(4);
        singleLinkedList.del(3);
        singleLinkedList.del(2);
        singleLinkedList.del(1);

        System.out.println("删除后的-");
        singleLinkedList.list();

    }
}

//定义一个SingleLinkedList 管理英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动  ,不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向列表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next 指向 新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp =head;
        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if(temp.next==null){
               break;
            }
            //如果没有找到最后，将temp后移
            temp=temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向 新的节点
        temp.next=heroNode;
    }

    //第二种添加英雄的方式，根据排名将英雄插入到指定位置（如果有这个排名，则添加失败，并且给出提示）
    public void addByOrder(HeroNode heroNode){
        //因为头结点 不能动，所以我们需要一个辅助指针（变量）来帮助找到添加的位置
        //因为是单链表，所以我们找的temp是位于添加位置的前一个节点 ，否则插入不了
        HeroNode temp =head;
        boolean flag =false; //标志添加的编号是否存在，默认为false

        while(true){
            if(temp.next==null){ //说明temp已经在链表的最后
                break;
            }
            if(temp.next.no >heroNode.no){ //位置找到了，就在temp的后面插入
                break;
            }else if(temp.next.no == head.no){ //说明希望添加的heroNode的编号已经存在
                flag=true; //说明编号存在
            }
            temp=temp.next;  //后移，遍历当前链表

        }
        //判断flag的值
        if(flag){ //说明编号存在，不能添加
            System.out.printf("准备插入的英雄的标号%d已经存在,不能加入\n",heroNode.no);
        }else{
            //插入到链表中，temp的后面
            heroNode.next =temp.next;
            temp.next =heroNode;
        }

    }

    //修改结点的信息，根据no编号来修改【根据newHeroNode 的no来修改就行】，no编号不能改
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空——");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;  //表示是否找到该节点
        while (true) {
            if (temp == null) {  //到了链表的最后
                break; //已经遍历完该链表
            }
            if (temp.no == newHeroNode.no) {
                //找到了该节点
                flag = true;
                break;
            }
            temp = temp.next;

        }
        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else{
            System.out.printf("没有找到编号%d 的节点，不能修改\n",newHeroNode.no);
        }
    }


    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空——");
            return;
        }
        //因为头结点不能动，所以需要一个辅助变量来遍历
        HeroNode temp=head.next;
        while(true){
            //判断是否到链表最后
            if(temp ==null){
                break;
            }
            //输出节点 的信息
            System.out.println(temp);
            //将next后移
            temp=temp.next;
        }
    }

    //删除节点
    //head节点不能动，用辅助节点temp找到待删除节点的前一个节点
    //（说明比较的时候，是 temp.next.no 和 需要删除的节点no比较）
    public  void del(int no){
        HeroNode temp=head;
        boolean flag = false;  //表示是否找到该节点
        while(true){
            if(temp.next==null){  //已经到最后了
                break;
            }
            if(temp.next.no==no){ //找到了待删除节点的前一个节点temp
                flag=true;
                break;

            }
            temp=temp.next;
        }

        //判断flag
        if(flag){  //找到
            temp.next=temp.next.next; //删除
        }else{
            System.out.printf("要删除的 %d 节点不存在\n",no);
        }
    }


}


//定义一个HeroNode ,每个HeroNode 对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    //为了显示方便，重写toString

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}' +'\n';
    }
}
