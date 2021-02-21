import java.io.*;
import java.util.*;

public class Solution1224 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        String a;
        char c;
         
        Stack<Character> stack = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();
         
        for (int testcase = 1; testcase <= 10; testcase++) {
            N = Integer.parseInt(br.readLine().trim());
            a = br.readLine().trim();
             
            StringBuilder sb = new StringBuilder();
             
            for (int i = 0; i < N; i++) {
                c = a.charAt(i);
                 
                if(c == ' ') continue;
                else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                    if(stack.isEmpty()) {
                        stack.push(c);
                        continue;
                    }
                    switch (c) {
                    case '+':
                    case '-':
                        if(stack.peek() == '+' || stack.peek() == '-') {
                            sb.append(stack.pop());
                        }
                        else if(stack.peek() == '*' || stack.peek() == '/') {
                            sb.append(stack.pop());
                        }
                        stack.push(c);
                        break;
                         
                    case '*':
                    case '/':
                        if(stack.peek() == '*' || stack.peek() == '/') {
                            sb.append(stack.pop());
                        }
                        stack.push(c);
                        break;
                    case '(':
                        stack.push(c);
                        break;
                    case ')':
                        while(stack.peek() != '(') {
                            sb.append(stack.pop());
                        }
                        stack.pop();
                    }
                }
                else if(c>'0' && c <= '9') {
                    sb.append(c);
                }
            }
             
            if(!stack.isEmpty()) {
                while(!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
            }
             
            a = sb.toString();
            int e, f;
            N = a.length();
             
            for (int i = 0; i < N; i++) {
                c = a.charAt(i);
                if (c > '0' && c <= '9') { // if안에 Character.isDigit(c)이라고 해도 됨
                    stack1.push(c - '0');
                } else {
                    f = stack1.pop();
                    e = stack1.pop();
                    switch (c) {
                    case '+':
                        stack1.push(e+f);
                        break;
                    case '*':
                        stack1.push(e*f);
                        break;
                    case '/':
                        stack1.push(e/f);
                        break;
                    case '-':
                        stack1.push(e-f);
                        break;
                    }
                }
            }
            System.out.println("#"+testcase+" "+stack1.pop());
        }
    }
}
