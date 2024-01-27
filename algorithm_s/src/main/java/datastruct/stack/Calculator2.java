package datastruct.stack;

/*
    与课堂讲解代码的几点不同：
        1. 将三层if-else循环优化为两层
        2. 将字符串表达式转换为字符数组进行向前遍历
        3. 使用Stringbuffer进行字符拼接并反转
        4. 将是否是运算符的判断从ArrayStack类中拿出，放入Calculator类
        5. 未添加浮点运算与括号运算的功能
 */
public class Calculator2 {

    public static void main(String[] args) {

        ArrayStack3 numberStack = new ArrayStack3(50);//数栈
        ArrayStack3 operatorStack = new ArrayStack3(50);//符号栈

        String expression = "700-2*5*4-4-12/6/2+4";//659
//        String expression = "700-6*20*2*2+3";//223
//        String expression = "7-6-5-4";//-8
//        String expression = "70-60/2/3/5/2+98";//167

        char[] arr = expression.toCharArray();//将表达式转换为字符数组

        for (int i = arr.length - 1; i >= 0; i--) {//向前遍历

            if (isOperator(arr[i])) {//如果是运算符

                if (!operatorStack.isEmpty()//3.3分析的三条符号直接入栈条件的代码表示
                        && (operatorStack.priority(arr[i]) != operatorStack.priority(operatorStack.peek())
                        || (arr[i] != '+' && arr[i] != '-'))
                        && operatorStack.priority(arr[i]) <= operatorStack.priority(operatorStack.peek())) {

                    int num1 = numberStack.pop();
                    int num2 = numberStack.pop();
                    int operator = operatorStack.pop();

                    if(arr[i] == '/' && operator == '/'){//进行连除时，需要将后一个'/'变为'*'
                        operator = '*';
                    }

                    int result = numberStack.cal(num1, num2, operator);
                    numberStack.push(result);

                }
                operatorStack.push(arr[i]);


            } else {//如果是数
                StringBuffer buffer = new StringBuffer();
                while (!isOperator(arr[i])) {//判断是否为符号
                    buffer.append(arr[i]);
                    i--;
                    if (i == -1) {//防止数组越界
                        break;
                    }
                }
                i++;//保证索引指示正确

                String s = buffer.reverse().toString();//反转buffer，并转换为字符串
                int num = Integer.parseInt(s);

                numberStack.push(num);
            }

        }


        while (!operatorStack.isEmpty()) {//遍历完成后进行计算
            int num1 = numberStack.pop();
            int num2 = numberStack.pop();
            int operator = operatorStack.pop();

            int result = numberStack.cal(num1, num2, operator);
            numberStack.push(result);
        }

        System.out.println(expression + " = " + numberStack.pop());

    }

    //判断是否是运算符
    private static boolean isOperator(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

}


class ArrayStack3 {//ArrayStack3类表示栈

    private int maxSize;//栈的大小
    private int[] stack;//数组，栈的数据存放在数组中
    private int top = -1;//栈顶，初始化为-1

    public ArrayStack3(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈：push
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = num;
    }

    //出栈：pop
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top--];
        return value;
    }

    //遍历栈
    public void show() {
        //从栈顶开始遍历
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        int subTop = top;
        while (subTop != -1) {
            System.out.println(stack[subTop]);
            subTop--;
        }
    }

    //获取栈顶
    public int peek() {
        return stack[top];
    }

    //返回运算符的优先级，数字越大，优先级越高
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 0;
        } else {
            return -1;
        }
    }


    //计算方法，注意'-'和'/'是num1在前，与课堂上讲的有所不同
    public int cal(int num1, int num2, int operator) {
        int result = 0;//存放计算结果

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }

        return result;
    }

}

