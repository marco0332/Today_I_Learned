import java.io.*;
import java.util.*;

public class Main2912_백설공주와난장이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] NC = br.readLine().split(" ");
		int N = Integer.parseInt(NC[0]);
		int C = Integer.parseInt(NC[1]);
		
		int[] hatColor = new int[N+1];
		String[] hats = br.readLine().split(" ");
		for(int n=1; n<=N; n++) {
			hatColor[n] = Integer.parseInt(hats[n-1]);
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int m=0; m<M; m++) {
			String[] input = br.readLine().split(" ");
			int A = Integer.parseInt(input[0]);
			int B = Integer.parseInt(input[1]);
			
			int[] cnt = new int[C+1];
			for(int i=A; i<=B; i++) {
				cnt[hatColor[i]]++;
			}
			
			boolean check = false;
			for(int c=1; c<=C; c++) {
				if((B-A+1)/2 < cnt[c]) {
					bw.write("yes " + c);
					bw.write("\n");
					check = true;
				}
			}
			if(!check)
				bw.write("no\n");
		}
		bw.flush();
		bw.close();
	}
}
