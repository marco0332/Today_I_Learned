import java.io.*;
import java.util.*;

public class Main2643_색종이_올려_놓기 {
	static int N, answer;
	static Box[] list;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		list = new Box[N];
		for(int n=0; n<N; n++) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			if(x < y) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			list[n] = new Box(x, y);
		}
		
		Arrays.sort(list, new xSort());
		dp = new int[N];
		dp[0] = 1;
		for(int i=1; i<N; i++) {
			dp[i] = 1;
			for(int j=i-1; j>=0; j--) {
				if((list[j].y <= list[i].y && list[j].x <= list[i].x) || (list[j].x <= list[i].y && list[j].y <= list[i].x)) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		for(int n=0; n<N; n++) {
			answer = Math.max(answer, dp[n]);
		}
		System.out.println(answer);
	}

	static class Box {
		int x, y;

		public Box(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class xSort implements Comparator<Box> {
		@Override
		public int compare(Box o1, Box o2) {
			if (o1.x != o2.x)
				return o1.x - o2.x;
			else
				return o1.y - o2.y;
		}
	}
}