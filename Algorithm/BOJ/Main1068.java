import java.io.*;
import java.util.*;

public class Main1068{
	
	static ArrayList<nodes> adj;
	static int Root, removeV;
	
	public static int go(int ver) {
		int cnt = 0;
		nodes vertex = adj.get(ver);
		for(int i=0, size=vertex.size; i<size; i++) {
			if(vertex.childs.get(i) != removeV)
				cnt += go(vertex.childs.get(i));
			else {
				vertex.childs.remove(i);
				i--;
				size--;
				vertex.size--;
			}
		}
		
		if(vertex.size == 0) 
			cnt = 1;
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int ans = 0;
		
		adj = new ArrayList<nodes>(N);
		for(int n=0; n<N; n++) {
			adj.add(new nodes());
		}
		
		Root = 0;
		for(int n=0; n<N; n++) {
			int parent = sc.nextInt();
			
			if(parent == -1) Root = n;
			else {
				adj.get(parent).childs.add(n);
				adj.get(parent).size++;
			}
		}
		removeV = sc.nextInt();
		
		if(removeV == Root) ans = 0;
		else ans = go(Root);
		
		System.out.println(ans);
	}
}

//class nodes{
//	int size;
//	ArrayList<Integer> childs;
//	
//	public nodes() {
//		size = 0;
//		this.childs = new ArrayList<Integer>();
//	}
//}