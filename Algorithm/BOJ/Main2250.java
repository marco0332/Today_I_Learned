import java.io.*;
import java.util.*;

public class Main2250 {
	
	static ArrayList<nodes> tree;
	static int[] levelMax, levelMin;
	static int xAxis = 0;
	
	public static void midOrder(int cur, int level) {
		nodes vertex = tree.get(cur);
		
		if(vertex.left != -1) midOrder(vertex.left, level+1);
		
		xAxis++;
		if(levelMax[level] < xAxis) levelMax[level] = xAxis;
		if(levelMin[level] > xAxis) levelMin[level] = xAxis;
		
		if(vertex.right != -1) midOrder(vertex.right, level+1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] val = new int[3];
		int[] indegree = new int[N+1];
		String [] input;
		levelMax = new int[N+1]; levelMin = new int[N+1];
		Arrays.fill(levelMax, -1); Arrays.fill(levelMin, 987654321);
		
		tree = new ArrayList<>();
		for(int n=0; n<=N; n++) {
			tree.add(new nodes());
		}
		
		for(int n=0; n<N; n++) {
			input = br.readLine().split(" ");
			
			for(int i=0; i<3; i++) {
				val[i] = Integer.parseInt(input[i]);
			}
			
			nodes cur = tree.get(val[0]);
			if(val[1] != -1) {
				indegree[val[1]]++;
				cur.left = val[1]; 
				cur.size++;
			}
			if(val[2] != -1) {
				indegree[val[2]]++;
				cur.right = val[2]; 
				cur.size++;
			}
		}
		
		int Root = 0;
		for(int n=1; n<=N; n++) {
			if(indegree[n] == 0) {
				Root = n;
				break;
			}
		}
		
		midOrder(Root, 1);
		
		int answer = 0, ansLevel = -1;
		for(int n=1; n<=N; n++) {
			if(levelMax[n] != -1 && answer < levelMax[n]-levelMin[n]+1) {
				answer = levelMax[n]-levelMin[n]+1;
				ansLevel = n;
			}
			else if(levelMax[n] == -1) break;
		}
		
		System.out.println(ansLevel+" "+answer);
	}
}

//class nodes {
//	int left, right;
//	int size;
//	
//	public nodes() {
//		left = -1; right = -1;
//		size = 0;
//	}
//}