import java.io.*;
import java.util.*;

public class Solution1234 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int t=1; t<=10; t++) {
			String[] Input = br.readLine().split(" ");
			Stack<Character> s = new Stack<>();
			
			int N =Integer.parseInt(Input[0]);
			for(int n=0; n<N; n++) {
				if(!s.empty() && s.peek() == Input[1].charAt(n)) s.pop();
				else s.push(Input[1].charAt(n));
			}
			
			int len = s.size();
			bw.write("#"+t+" ");
			for(int i=0; i<len; i++) {
				bw.write(s.elementAt(i));
			}
			bw.write("\n");
			
			bw.flush();
		}
	}
}
