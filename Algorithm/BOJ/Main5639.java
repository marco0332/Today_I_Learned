import java.io.*;
import java.util.*;

public class Main5639 {
	
	static BufferedWriter bw;
	
	public static void postOrder(BinarySearchTree node) throws IOException {
		if(node.left != null) postOrder(node.left);
		if(node.right != null) postOrder(node.right);
		bw.write(node.node+"\n");
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		BinarySearchTree bst = new BinarySearchTree(-1);
		
		String line;
		while((line = br.readLine()) != null) {
			int val = Integer.parseInt(line);
			
			bst.add(bst, val);
		}
		postOrder(bst);
		
		bw.flush();
	}
}

class BinarySearchTree{
	int node;
	BinarySearchTree left, right;
	
	public BinarySearchTree(int node) {
		this.node = node;
		left = null;
		right = null;
	}
	
	public void add(BinarySearchTree cur, int val) {
		if(cur.node == -1) cur.node = val;
		else if(cur.node < val) {
			if(cur.right != null) add(cur.right, val);
			else cur.right = new BinarySearchTree(val);
		}
		else if(cur.node > val) {
			if(cur.left != null) add(cur.left, val);
			else cur.left = new BinarySearchTree(val);
		}
	}
}