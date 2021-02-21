import java.io.*;
import java.util.*;

public class Main1326_폴짝폴짝 {
	static class Node implements Comparable<Node> {
		int idx;
		int cnt;
		
		public Node(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			if(this.cnt < o.cnt) return -1;
			else if(this.cnt > o.cnt) return 1;
			else return 0;
		}
	}
	
	static int N;
	static int[] dp, arr;
	static int start, target;
	static PriorityQueue<Node> q = new PriorityQueue<Node>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		dp = new int[N];
		String[] input = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		input = br.readLine().split(" ");
		start = Integer.parseInt(input[0])-1;
		target = Integer.parseInt(input[1])-1;
		
		Arrays.fill(dp, -1);
		
		q.add(new Node(start,0));
		while(!q.isEmpty()) {
			go();
		}
		
		System.out.println(dp[target]);
	}
	
	static void go() {
		Node cur = q.poll();
		int point = cur.idx;
		
		for(int i=0; i<point; i++) {
			if(dp[i] == -1 && (point-i)%arr[point] == 0) {
				q.add(new Node(i,cur.cnt+1));
				dp[i] = cur.cnt+1;
			}
		}
		
		for(int i=point+1; i<N; i++) {
			if(dp[i] == -1 && (i-point)%arr[point] == 0) {
				q.add(new Node(i,cur.cnt+1));
				dp[i] = cur.cnt+1;
			}
		}
	}
}
