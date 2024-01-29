package datastruct.recursion;

public class Queue8 {

    //定义一个Max表示共有多少个皇后
    int max=8;

    //定义一个数组array，保存皇后放置位置的结果
    int array[]=new int[max];

    static int count=0;
    static int judgeCount=0;
    public static void main(String[] args) {

        //测试一次，看8皇后是否正确
        Queue8 queue8= new Queue8();
        queue8.check(0);
        System.out.println("解法的次数="+count);
        System.out.println("冲突的次数="+judgeCount);

    }

    //编写一个方法，放置第n个皇后
    //!!!check是每一次递归时，进入到check中 都有for(int i=0;i<max;i++)  因此会有回溯
    private void check(int n){
        if(n==max){ //n=8,其实8个皇后就已经放好了
            print();
            return;
        }

        //依次放入皇后，并且判断是否冲突
        for(int i=0;i<max;i++){
            //先把当前这个皇后n，放到该行的第一列
            array[n]=i;

            //判断当放值第n个皇后到i列时，是否冲突
            if(judge(n)){  //不冲突
                //接着放n+1个皇后，开始递归
                check(n+1);
            }

            //如果冲突，就继续执行array[n]=i;  即：将第n个皇后，放置在本行的 后移一个位置

        }
    }

    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    /**
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        for(int i=0;i<n;i++){
            //1.array[i]==array[n] 表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            //2.Math.abs(n-i) ==Math.abs(array[n] -array[i]) 表示判断第n个皇后是否和第i个皇后在同一斜线上
            //3.判断是否在同一行，没有必要，因为每次都在递增
            if(array[i]==array[n] || Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;

    }

    //写一个方法：将皇后摆放的位置输出
    private void print(){
        count++;
        for(int i=0;i<array.length;i++){
            System.out.printf(array[i] + " ");
        }
        System.out.println();
    }
}
