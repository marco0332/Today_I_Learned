import java.io.*;
import java.util.*;

public class Main1766_문제집 {
	static int N, M, ansIdx;
	static ArrayList<ArrayList<Integer>> adj;
	static int[] answer;
	
	public static void go(int cur) {
		for(int i=0,size=adj.get(cur).size(); i<size; i++) {
			go(adj.get(cur).get(i));
		}
		answer[ansIdx++] = cur;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		adj = new ArrayList<ArrayList<Integer>>();
		answer = new int[N];
		for(int n=0; n<=N; n++) {
			adj.add(new ArrayList<Integer>());
		}
		
		for(int m=0; m<M; m++) {
			String[] input = br.readLine().split(" ");
			
			adj.get(Integer.parseInt(input[1])).add(Integer.parseInt(input[0]));
		}
		
		for(int n=1; n<=N; n++) {
			Collections.sort(adj.get(n));
		}
		
		for(int n=1; n<=N; n++) {
			if(adj.get(n).size() != 0) {
				go(n);
			}
		}
		
		for(int n=0; n<N; n++) {
			System.out.print(answer[n]+" ");
		}
	}
}
