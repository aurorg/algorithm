package datastruct.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotationDemo {

    public static void main(String[] args) {
        //先创建一个逆波兰表达式
        //（3+4)x5-6  => 3 4 + 5 x 6 -  结果29
        //（30+4)x5-6  => 3 4 + 5 x 6 - 结果164
        //4 * 5 - 8 + 60 + 8 / 2 =》76
        //后缀表达式： 4 5 * 8 - 60 + 8 2 / +
        //为了方便，逆波兰表达式的数字和符号用空格隔开
        String suffixExpression="4 5 * 8 - 60 + 8 2 / +";

        //1.先将表达式放到ArrayList中
        //2.将ArrayList 传递给一个方法，遍历ArrayList 配合栈 完成计算

        List<String> rpnList =getListString(suffixExpression);
        System.out.println(rpnList);

        int res=calculate(rpnList);
        System.out.println("结果是" + res);

    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suddixExpression按照空格分割

        String[] split =suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for(String ele:split){
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    /*
        * 1)从左至右扫描，将 3 和 4 压入堆栈；
        2)遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
        3)将 5 入栈；
        4)接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
        5)将 6 入栈；
        6)最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
    */
    public static int calculate(List<String> ls){
        //创建一个栈
        Stack<String> stack = new Stack<String>();

        //遍历list
        for(String item:ls){
            //使用正则表达式来取出一个数
            if(item.matches("\\d+")){ //匹配多位数
                //入栈
                stack.push(item);
            }else{
                //如果是运算符，pop两个数，运算，入栈
                int num2 =Integer.parseInt(stack.pop());
                int num1 =Integer.parseInt(stack.pop());

                int res=0;
                if(item.equals("+")){
                    res=num1+num2;
                } else if (item.equals("-")) {
                    res=num1-num2;
                } else if(item.equals("*")){
                    res=num1*num2;
                } else if (item.equals("/")) {
                    res=num1 / num2;
                }else {
                    throw new RuntimeException("运算符有问题");
                }

                //把res入栈
                stack.push(res + ""); //把res整数转成字符串

            }

        }

        //最后栈中的数据就是结果

        return Integer.parseInt(stack.pop());
    }


}
