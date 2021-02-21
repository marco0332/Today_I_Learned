import java.io.*;
import java.util.*;
 
public class Solution1218{
    static int T = 10;
    static int Answer;
    public static void main(String[] args) throws Exception{
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int t=1; t<=T; t++) {
            int size = Integer.parseInt(br.readLine());
            Stack<Character> stk = new Stack<>();
             
            String str = br.readLine();
            boolean check = true;
            for(int i=0; i<size; i++) {
            	if(!check) break;
            	
                char c = str.charAt(i);
                switch (c) {
                case '(':
                case '<':
                case '{':
                case '[':
                	stk.push(c);
                    break;
                case ')':
                	if(stk.peek() == '(') {
                		stk.pop();
                	}
                	else
                		check = false;
                    break;
                case '}':
                	if(stk.peek() == '{') {
                		stk.pop();
                	}
                	else
                		check = false;
                    break;
                case ']':
                	if(stk.peek() == '[') {
                		stk.pop();
                	}
                	else
                		check = false;
                    break;
                case '>':
                	if(stk.peek() == '<') {
                		stk.pop();
                	}
                	else
                		check = false;
                    break;
                }
            }
            if(check)
                Answer = 1;
            else
                Answer = 0;
            bw.write("#"+t+" "+Answer+"\n");
        }
        bw.flush();
    }
}