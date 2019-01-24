import java.io.*;
import java.util.*;

public class Main1991 {
	static mynode[] Tree;
	
	public static void pre(mynode n) {		
		System.out.print(n.val);
		if(n.left != -1) pre(Tree[n.left]);
		if(n.right != -1) pre(Tree[n.right]);
	}
	
	public static void mid(mynode n) {
		if(n.left != -1) mid(Tree[n.left]);
		System.out.print(n.val);
		if(n.right != -1) mid(Tree[n.right]);
	}
	
	public static void post(mynode n) {
		if(n.left != -1) post(Tree[n.left]);
		if(n.right != -1) post(Tree[n.right]);
		System.out.print(n.val);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Tree = new mynode[N];
		
		for(int n=0; n<N; n++) {
			String[] input = br.readLine().split(" ");
			int l = input[1].charAt(0)-'A';
			int r = input[2].charAt(0)-'A';
			int idx = input[0].charAt(0)-'A';
			
			if(0<=l && l<26 && 0<=r && r<26)
				Tree[idx] = new mynode(l, r, input[0].charAt(0));
			else if(0<=l && l<26)
				Tree[idx] = new mynode(l, -1, input[0].charAt(0));
			else if(0<=r && r<26)
				Tree[idx] = new mynode(-1, r, input[0].charAt(0));
			else
				Tree[idx] = new mynode(-1, -1, input[0].charAt(0));
		}
		
		pre(Tree[0]); System.out.println();
		mid(Tree[0]); System.out.println();
		post(Tree[0]); System.out.println();
	}
	
//	class mynode {
//		int left, right;
//		char val;
//		
//		mynode(int left, int right, char val){
//			this.left = left;
//			this.right = right;
//			this.val = val;
//		}
//	}
}