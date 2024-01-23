package datastruct.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        //创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key=' '; //接受用户的输入
        Scanner scanner= new Scanner(System.in);

        boolean loop =true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch(key){
            case 's':
                    arrayQueue.showQueue();
                    break;
            case 'a':
                    System.out.println("输出一个数");
                    int value =scanner.nextInt();
                    arrayQueue.add(value);
                    break;
            case 'g':
                    try{
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());

                    }
                    break;
            case 'h': //查看队列头的数据
                    try{
                        int res=arrayQueue.headQueue();
                        System.out.printf("队列头数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
            case 'e': //退出
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出————");



    }
}

//使用数组模拟队列--编写一个ArrayQueue
class ArrayQueue{
    private int maxSize; //表示数组的最大容量
    private int front; //队列头
    private int rear; //队列尾部
    private int[] arr;//该数据用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=-1; //指向队列头部，分析出front是指向队列头部的前一个位置
        rear=-1;  //指向队列尾部，指向队列尾的数据（即队列的最后一个位置）

    }

    //判断队列是否是满的
    public boolean isFull(){
        return rear==maxSize-1;
    }

    //判断队列是否空的
    public boolean isEmpty(){
        return rear==front;
    }

    //添加数据到队列中
    public void add(int n){

        //判断队列是否是满的
        if(isFull()){
            System.out.println("队列是满的，不能加入数据-");
            return ;
        }
        rear++;  //让rear后移
        arr[rear]=n; //加入数据n
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否是空的
        if(isEmpty()){
            //通过抛异常处理
            throw new RuntimeException("队列空,不能取数据");
        }
        front++;  //front后移
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空的，没有数据");
            return ;
        }
        for(int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列的头数据
    public int headQueue(){
        if(isEmpty()){
            System.out.println("队列空的");
            throw new RuntimeException("队列空的，没有数据");
        }
        return arr[front+1];
    }

}

//1) 目前数组使用一次就不能用， 没有达到复用的效果
//2) 将这个数组使用算法，改进成一个环形的队列 取模：%