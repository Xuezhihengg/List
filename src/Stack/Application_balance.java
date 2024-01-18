package Stack;

public class Application_balance {
    public static boolean isBalance(String input) {
        AStack stack = new AStack();
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            //如果是开符号，压入栈
            if (temp == '(' || temp == '[' || temp == '{') {
                stack.push(temp);
            }
            //如果是闭符号，栈中弹出一个开符号，如果二者匹配则继续，不匹配直接返回false
            else if (temp == ')' || temp == ']' || temp == '}') {
                char popChar = (char) stack.pop();
                if (popChar == '(' && temp == ')') continue;
                if (popChar == '[' && temp == ']') continue;
                if (popChar == '{' && temp == '}') continue;
                else return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        String s = "({[]}){}";
        System.out.println(isBalance(s));
    }
}
