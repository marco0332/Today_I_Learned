/*N의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다.
우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다.
그러면 A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.
입력 
첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다.
그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다.
그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다.
출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.

출력 
첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
예제 입력 1 복사 
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5
예제 출력 1 복사 
4*/

import java.io.*;
import java.util.*;

public class Main1916_최소비용구하기 {
	static class Edge implements Comparable<Edge>{
		int node;
		int cost;
		
		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge e) {
			if(this.cost < e.cost) return -1;
			else if(this.cost > e.cost) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
		for(int n=0; n<=N; n++) {
			adj.add(new ArrayList<Edge>());
		}		
		for(int m=0; m<M; m++) {
			String[] input = br.readLine().split(" ");
			
			int u = Integer.parseInt(input[0]);
			int v = Integer.parseInt(input[1]);
			int w = Integer.parseInt(input[2]);
			
			adj.get(u).add(new Edge(v, w));
		}
		
		String[] line = br.readLine().split(" ");
		int start = Integer.parseInt(line[0]);
		int end = Integer.parseInt(line[1]);
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		
		q.add(new Edge(start, 0));
		dp[start] = 0;
		while(!q.isEmpty()) {
			Edge cur = q.poll();
			dp[cur.node] = cur.cost;
			
			if(cur.node == end)
				break;
			
			for(int i=0, size=adj.get(cur.node).size(); i<size; i++) {
				if(dp[adj.get(cur.node).get(i).node] > dp[cur.node] + adj.get(cur.node).get(i).cost) {
					dp[adj.get(cur.node).get(i).node] = dp[cur.node] + adj.get(cur.node).get(i).cost; 
					q.add(new Edge(adj.get(cur.node).get(i).node, dp[cur.node] + adj.get(cur.node).get(i).cost));
				}
			}
		}
		
		System.out.println(dp[end]);
	}
}
