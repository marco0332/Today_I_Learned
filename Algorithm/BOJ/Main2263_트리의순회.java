import java.io.*;
import java.util.*;

public class Main2263_트리의순회 {
	static int N, idx;
	static int[] inorder, postorder, preorder;
	
	public static void go(int inLeft, int inRight, int postLeft, int postRight, int size) {
		int root = postorder[postRight];
		preorder[idx++] = root;
		
		if(size == 1) return;
		
		int rIdx = -1;
		for(int i=inLeft; i<inLeft+size; i++) {
			if(inorder[i] == root) {
				rIdx = i;
				break;
			}
		}
		
		if(rIdx-inLeft > 0) go(inLeft, rIdx-1, postLeft, rIdx-1-(inRight-postRight), rIdx-inLeft);
		if(postRight-rIdx+(inRight-postRight) > 0) go(rIdx+1, inRight, rIdx-(inRight-postRight), postRight-1, postRight-rIdx+(inRight-postRight));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		inorder = new int[N];
		postorder = new int[N];
		preorder = new int[N];
		
		String[] line = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			inorder[i] = Integer.parseInt(line[i]);
		}
		
		line = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			postorder[i] = Integer.parseInt(line[i]);
		}
		
		go(0, N-1, 0, N-1, N);
		for(int i=0; i<N; i++) {
			sb.append(preorder[i]).append(" ");
		}
		
		System.out.println(sb.toString());
	}
}