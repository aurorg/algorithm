package datastruct.linkedlist;

public class JosepfuDemo {
    public static void main(String[] args) {

        //测试构建环形链表和遍历
        CircleSingleLInkedList circleSingleLInkedList = new CircleSingleLInkedList();
        circleSingleLInkedList.addBoy(5); //加入5个小孩结点
        circleSingleLInkedList.showBoy();

        //测试小孩出圈
        circleSingleLInkedList.countBoy(1,2,5);
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

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩
     */
    //根据用户的输入，计算出小孩出圈的顺序
    public void countBoy(int startNo,int countNum ,int nums){
        //先对数据进行校验
        if(first==null || startNo <1 || startNo >nums ){
            System.out.println("参数输入有误，重新输入");
            return;
        }

        //创建一个辅助指针
        Boy helper =first;

        //事先应该让helper指向环形链表的最后一个节点
        while(true){
            if(helper.getNext()==first){ //说明helper指向的是最后一个节点
                break;
            }
            helper=helper.getNext();
        }
        //报数之前，先让first 和helper 移动 k-1次（startNo -1 次）
        for(int i=0;i<startNo -1;i++){
            first=first.getNext();
            helper=helper.getNext();
        }

        //当小孩报数时，让 first 和 helper 指针同时 的移动m- 1 (countNum-1次)次, 然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while(true){
            if(helper==first){ //说明圈中只有一个节点
                break;
            }

            //让 first 和 helper 指针同时 的移动 countNum - 1
            for(int i=0;i<countNum-1;i++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //此时first指向的节点，就是出圈的小孩节点
            System.out.printf("小孩 %d 出圈 \n",first.getNo());

            //将first指向的小孩节点出圈
            first=first.getNext();
            helper.setNext(first);

        }
        System.out.printf("最后留在圈中的小孩编号%d",first.getNo());
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
