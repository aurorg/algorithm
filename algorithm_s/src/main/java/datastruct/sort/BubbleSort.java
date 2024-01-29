package datastruct.sort;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {

//        int arr[] = {3, 9, -1, 10, 20};
//
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));


        //为了容量理解
        //测试一下冒泡排序的速度0（n^2）,给80000个数据测试
        //创建给80000个的随机的数组

        int[] arr = new int[80000];
        for(int i=0;i<80000;i++){
            arr[i] =(int)(Math.random() *8000000); //生成一个【0，8000000）数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

        String data1Str =simpleDateFormat.format(data1);
        System.out.println("排序前的时间：" + data1Str);

        bubbleSort(arr);

        Date data2 = new Date();
        String data2Str =simpleDateFormat.format(data2);
        System.out.println("排序后的时间：" + data2Str);


//        //测试冒泡排序
//        bubbleSort(arr);
//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));


    }

    //把冒泡排序封装成一个方法
    public static void bubbleSort(int[] arr) {
        //时间复杂度O（n^2）
        int temp = 0; //临时变量

        boolean flag = false; //标识变量，表示是否进行过交换

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第" + (i + 1) + "趟排序后的数组");
//            System.out.println(Arrays.toString(arr));

            if (!flag) {  //在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false;  //重置flag,进行下次判断
            }
        }
    }

}

//        //第一趟排序就是把最大的数排在最后
//        for(int j=0;j<arr.length-1-0;j++){
//            //如果前面的比后面的大，就交换
//            if(arr[j]>arr[j+1]){
//                temp=arr[j];
//                arr[j]=arr[j+1];
//                arr[j+1]=temp;
//            }
//        }
//        System.out.println("第一趟排序");
//        System.out.println(Arrays.toString(arr));
//
//        //第二趟排序，就是把第二大的排在倒数第二
//        for(int j=0;j<arr.length-1-1;j++){
//            if(arr[j]>arr[j+1]){
//                temp=arr[j];
//                arr[j]=arr[j+1];
//                arr[j+1]=temp;
//            }
//        }
//        System.out.println("第二趟排序");
//        System.out.println(Arrays.toString(arr));
//
//        //第三趟排序，就是把第三大的排在倒数第三
//        for(int j=0;j<arr.length-1-2;j++){
//            if(arr[j]>arr[j+1]){
//                temp=arr[j];
//                arr[j]=arr[j+1];
//                arr[j+1]=temp;
//            }
//        }
//        System.out.println("第三趟排序");
//        System.out.println(Arrays.toString(arr));
//
//        //第四趟排序，就是把第四大的排在倒数第四
//        for(int j=0;j<arr.length-1-3;j++){
//            if(arr[j]>arr[j+1]){
//                temp=arr[j];
//                arr[j]=arr[j+1];
//                arr[j+1]=temp;
//            }
//        }
//        System.out.println("第四趟排序");
//        System.out.println(Arrays.toString(arr));


