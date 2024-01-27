package datastruct.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotationDemo {

    public static void main(String[] args) {

        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)×4)-5 => 转成 1 2 3 + 4 × + 5 –
        //2. 因为直接对 str 进行操作，不方便，因此 先将"1+((2+3)×4)-5" =》 中缀的表达式对应的 List ,
        //   即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的 List => 后缀表达式对应的 List
        //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]

        String expression="1+((2+3)*4)-5";
        List<String> infixExpressionList=toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list " +infixExpressionList);   //"1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]

        List<String> suffixExpressionList=parseSuffixExpressionLIst(infixExpressionList);

        System.out.println("后缀表达式对应的list" + suffixExpressionList);

        System.out.println("计算的结果" + calculate(suffixExpressionList));




        //先创建一个逆波兰表达式
        //（3+4)x5-6  => 3 4 + 5 x 6 -  结果29
        //（30+4)x5-6  => 3 4 + 5 x 6 - 结果164
        //4 * 5 - 8 + 60 + 8 / 2 =》76
        //后缀表达式： 4 5 * 8 - 60 + 8 2 / +
        //为了方便，逆波兰表达式的数字和符号用空格隔开
//        String suffixExpression="4 5 * 8 - 60 + 8 2 / +";
//
//        //1.先将表达式放到ArrayList中
//        //2.将ArrayList 传递给一个方法，遍历ArrayList 配合栈 完成计算
//
//        List<String> rpnList =getListString(suffixExpression);
//        System.out.println(rpnList);
//
//        int res=calculate(rpnList);
//        System.out.println("结果是" + res);

    }

    //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]
    //方法：将得到的中缀表达式对应的list =》后缀表达式对应的list
    public static List<String> parseSuffixExpressionLIst(List<String> ls){
        //定义两个栈
        Stack<String> s1=new Stack<String>(); //符号栈

        //因为s2这个栈，在整个转换过程中，没有pop操作，而且后面还需要逆序输出
        //因此比较麻烦，所以用List<Sring> s2 代替s2这个栈
        //Stack<String> s2=new Stack<String>(); //存放中间结果的栈
        List<String> s2 = new ArrayList<String>();

        //遍历ls
        for(String item: ls){
            //如果是一个数，就加入到s2
            if(item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是一个右括号，则依次弹出s1栈顶的运算符，并且压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop(); //将 （ 弹出s1栈，消除小括号
            }else{
                //当 item的优先级 小于等于 s1栈顶运算符的优先级；
                //将s1栈顶的运算符弹出并且加入到s2中，再次转到（4.1）与 s1 中的新的栈顶运算符比较

                //缺少一个优先级高低的方法
                while(s1.size() !=0 && Operation.getValue(s1.peek()) >= Operation.getValue(item) ){
                    s2.add(s1.pop());
                }

                //还需要将item压入栈
                s1.push(item);

            }
        }

        //将s1中剩余的运算符依次弹出并且加入s2
        while(s1.size() !=0){
            s2.add(s1.pop());
        }

        return s2; //因为是存放到list，因此按顺序输出就是对应的后缀表达式对应的list

    }

    //方法：将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s){
        //定义一个List ，存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<String>();
        int i=0; //指针，用来遍历中缀表达式字符串
        String str; //对多位数进行拼接
        char c; //每遍历到一个字符，就放入到c

        do {
            //如果c是一个非数字，就需要加入到ls
            if((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57 ){
                ls.add("" +c); //转成字符串类型
                i++; //后移
            }else{ //如果是一个数，需要考虑多位数的问题
                str=""; //先将str置成"" 空串   ;'0'[48]-> '9'[57]
                 while(i <s.length() && (c=s.charAt(i)) >=48 && (c=s.charAt(i)) <=57){
                    str +=c; //拼接
                    i++;
                }
                ls.add(str);
            }

        }while(i <s.length());

        return ls;

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

//编写一个类Operation，可以返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应优先级的数字
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return  result;

    }
}
