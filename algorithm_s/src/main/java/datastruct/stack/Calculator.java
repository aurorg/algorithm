package datastruct.stack;

//有问题
public class Calculator {
    public static void main(String[] args) {

        //完成表达式的运算
        String expression ="7-2*3+1";  //存在问题，如何计算多位数的问题

        //创建两个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义需要的相关变量
        int index=0;//用于扫描表达式，取到数字或者符合
        int num1=0;
        int num2=0;
        int oper=0; //接受操作符
        int res=0;
        char ch=' '; //将每次扫描的char保存到ch中
        String keepNum =""; //用于拼接多位数

        //开始while循环扫描expression
        while(true){
            //依次得到expression 的每一个字符
            ch =expression.substring(index,index+1).charAt(0);
            //判断ch 是什么 ，然后做相应的处理
            if(operStack.isOper(ch)){ //如果是运算符
                //判断当前的符号栈是否是空的
                if(!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数字栈中pop两个数（num1，num2）
                    //再从符号站中pop出一个符号，进行运算，将得到的结果，入数字栈，然后将当前的操作符入符号栈
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        //把运算的结果入 数字栈
                        numStack.push(res);
                        //然后将当前的操作符入 符号栈
                        operStack.push(ch);
                    }else{
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else{
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            }else{
                //如果是数字，则直接入数字栈
               // numStack.push(ch-48);//ch-48是因为ascii码【“1+3” =》 ‘1’】

                //当处理多位数时，不能发现是一个数就入栈，因为可能是多位数
                //在处理数，需要向表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //需要定义一个变量 字符串，用来拼接

                //处理多位数
                keepNum += ch;
                //如果 ch 已经是 expression 的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                //注意是看后一位，不是 index++
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
                    //如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));
                    //重要的!!!!!!, keepNum 清空
                        keepNum = "";
                    }
                }
            }
            //让index+1，并且判断是否扫描到expression最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从 数字栈 和符号栈 中pop出相应的数字和符号，并且运行
        while(true){
            //如果符号栈是空的，则计算到最后的结果，数字栈只有一个数字【就是最后的结果】
            if(operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res); //入栈
        }
        //将数字栈中的最后一个数pop 就是结果
        int res2=numStack.pop();
        System.out.printf("表达式 %s =%d",expression,res2);

    }
}

//先创建一个栈，用原来的栈，然后扩展功能
class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈，数据就放在数组里面
    private int top = -1; //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];  //初始化
    }

    //增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
    public int peek(){
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否满了
        if (isFull()) {
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈,将栈顶的数据返回
    public int pop() {
        //先判断栈是否是空的
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈【遍历栈】,遍历时需要从栈顶开始显示
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空的");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，数字越大，则优先级越高
    public int priority(int oper){
        if(oper=='*' || oper =='/'){
            return 1;
        }else if(oper=='+' || oper=='-'){
            return 0;
        }else{
            return -1; //假定目前只有加减乘除
        }
    }

    //判断是否是一个运算符
    public boolean isOper(char val){
        return val=='+' || val== '-' || val=='*' || val =='/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res =0; //res用于存放计算的结果
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1; //注意顺序
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2 /num1; //注意顺序
                break;
            default:
                break;
        }
        return  res;
    }

}

