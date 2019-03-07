import java.io.*;
import java.util.*;

public class Main16917_양념반_후라이드반 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int A = Integer.parseInt(input[0]);
		int B = Integer.parseInt(input[1]);
		int C = Integer.parseInt(input[2]);
		int X = Integer.parseInt(input[3]);
		int Y = Integer.parseInt(input[4]);
		
		int cur = 0;
		int answer = Integer.MAX_VALUE;
		for(int i=Math.max(X, Y); i>=0; i--) {
			cur = i*2*C;
			
			if(X>i) cur += (X-i)*A;
			if(Y>i) cur += (Y-i)*B;
			
			answer = Math.min(answer, cur);
		}
		System.out.println(answer);
	}
}
