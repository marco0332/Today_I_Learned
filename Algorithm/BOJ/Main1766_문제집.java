import java.io.*;
import java.util.*;

public class Main1766_문제집 {
	static int N, M;
	static ArrayList<ArrayList<Integer>> adj;
	static int[] indegree;
	static PriorityQueue<Integer> q;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void go(int cur) throws IOException {
		bw.write(cur+" ");
		
		for(int i=0, size=adj.get(cur).size(); i<size; i++) {
			int idx = adj.get(cur).get(i);
			indegree[idx]--;
			
			if(indegree[idx] == 0)
				q.add(idx);
		}
	}
	
	public static void main(String[] args) throws IOException {
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		
		adj = new ArrayList<ArrayList<Integer>>();
		indegree = new int[N+1];
		q = new PriorityQueue<Integer>();
		for(int n=0; n<=N; n++) {
			adj.add(new ArrayList<Integer>());
		}
		
		String[] tmp;
		int u=0,v=0;
		for(int m=0; m<M; m++) {
			tmp = br.readLine().split(" ");
			u = Integer.parseInt(tmp[0]);
			v = Integer.parseInt(tmp[1]);
			
			adj.get(u).add(v);
			indegree[v]++;
		}
		
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			go(q.poll());
		}
		
		bw.flush();
		bw.close();
	}
}
