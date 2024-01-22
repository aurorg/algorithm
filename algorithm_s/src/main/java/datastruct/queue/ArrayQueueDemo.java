package datastruct.queue;

public class ArrayQueueDemo {
    public static void main(String[] args) {

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
            System.out.println("队列是满的");
        }
        rear++;  //让rear后移
        arr[rear]=n; //加入数据n
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否是空的
        if(isEmpty()){
            //通过抛异常处理
            throw new RuntimeException("队列空");
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