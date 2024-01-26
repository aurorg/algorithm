package datastruct.linkedlist;

public class JosepfuDemo {
    public static void main(String[] args) {

        //测试构建环形链表和遍历
        CircleSingleLInkedList circleSingleLInkedList = new CircleSingleLInkedList();
        circleSingleLInkedList.addBoy(5); //加入5个小孩结点
        circleSingleLInkedList.showBoy();
    }
}

//创建一个环形的单向链表
class CircleSingleLInkedList{

    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);
    //添加小孩结点，构建成环形链表
    public void addBoy(int nums){
        //对nums做一个数据校验
        if(nums<1){
            System.out.println("nums不正确");
            return ;
        }
        Boy curBoy =null; //辅助指针，帮助构建环形链表
        //使用for循环创建环形链表
        for(int i=1;i<=nums;i++){
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i==1){
                first=boy;
                first.setNext(first); //构成环
                curBoy=first; //让curBoy指向第一个小孩‘’
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }

        }
    }

    //遍历当前的环形列表
    public void showBoy(){
        //判断空
        if(first==null){
            System.out.println("空的");
            return ;
        }
        //因为first不能动，使用一个辅助指针
        Boy curBoy =first;
        while(true){
            System.out.printf("小孩的编号%d \n",curBoy.getNo());
            if(curBoy.getNext()==first){ //说明以及遍历完了
                break;
            }
            curBoy=curBoy.getNext(); //curBoy后移
        }
    }

}


//创建一个Boy类，表示一个节点
class Boy{
    private int no; //编号
    private Boy next; //指向下一个节点，默认是null
    public Boy(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy getNext() {
        return next;
    }
}
