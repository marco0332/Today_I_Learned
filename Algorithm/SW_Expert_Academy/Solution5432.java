import java.io.*;
import java.util.*;

public class Solution5432 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		int num, answer, len;
		char befoCh, ch;
		for(int t=1; t<=T; t++) {
			StringBuffer arr = new StringBuffer(br.readLine());
			len = arr.length();
			
			num = 0;
			answer = 0;
			befoCh = ' ';
			for(int i=0; i<len; i++) {
				ch = arr.charAt(i);
				if(ch=='(') {
					num++;
				}
				else {
					num--;
					if(befoCh == '(')
						answer += num;
					else {
						answer++;
					}
				}
				befoCh = ch;
			}
			writer.write("#"+t+" "+answer + "\n");
		}
		writer.flush();
        writer.close();
	}
}
