import java.io.*;
import java.util.*;

public class Solution5986_새샘이와_세소수 {
	static ArrayList<Integer> primeNum;
	static int[] answer;
	static int N;
	
	public static void go(int idx, int sum, int cnt) {
		if(sum > 999) return;
		if(cnt == 3) {
			answer[sum]++;
			return;
		}
		
		for(int i=idx, size=primeNum.size(); i<size; i++) {
			go(i, sum+primeNum.get(i), cnt+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		primeNum = new ArrayList<Integer>();

		boolean[] numList = new boolean[1000];
		answer = new int[1000];
		Arrays.fill(numList, true);
		numList[0] = numList[1] = false;
		for (int i = 2; i < 1000; i++) {
			if (numList[i]) {
				primeNum.add(i);
				for (int j = i*2; j < 1000; j += i) {
					numList[j] = false;
				}
			}
		}
		go(0, 0, 0);

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			sb.append("#"+t+" "+answer[N]+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
