import java.io.*;
import java.util.*;
 
public class Solution1223 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         
        int T = 10;
        for(int t=1; t<=T; t++) {
            int L = Integer.parseInt(br.readLine());
            Stack<Long> Operand = new Stack<Long>();
            Stack<Character> Operator = new Stack<Character>();
             
            long v1=0, v2=0;
            String input = br.readLine();
            for(int l=0; l<L; l++) {
                if(l%2==0)
                    Operand.push((long) (input.charAt(l)-'0'));
                else if(input.charAt(l) == '*')
                    Operator.push('*');
                else if(input.charAt(l) == '+' && !Operator.empty() && Operator.peek() == '*') {
                    while(!Operator.empty() && (Operator.peek() == '*')) {
                        v2 = Operand.pop();
                        v1 = Operand.pop();
                         
                        Operand.push(v1*v2);
                        Operator.pop();
                    }
                    Operator.push('+');
                }
                else
                    Operator.push(input.charAt(l));
            }
            while(!Operator.empty()) {
                v2 = Operand.pop();
                v1 = Operand.pop();
                 
                if(Operator.peek() == '*')
                    Operand.push(v1*v2);
                else
                    Operand.push(v1+v2);
                Operator.pop();
            }
             
            System.out.println("#"+t+" "+Operand.pop());
        }
    }
}