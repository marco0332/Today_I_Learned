import java.io.*;
import java.util.*;

public class Main16235_나무재테크 {
	static int N, M, K;
	static int[][] addValues;
	static Trees[][] board;
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static Queue<Pos> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NMK = br.readLine().split(" ");
		N = Integer.parseInt(NMK[0]);
		M = Integer.parseInt(NMK[1]);
		K = Integer.parseInt(NMK[2]);
		
		queue = new LinkedList<Pos>();
		
		addValues = new int[N][N];
		board = new Trees[N][N];
		for(int r=0; r<N; r++) {
			String[] lines = br.readLine().split(" ");
			for(int c=0; c<N; c++) {
				addValues[r][c] = Integer.parseInt(lines[c]);
				board[r][c] = new Trees();
			}
		}
		
		for(int m=0; m<M; m++) {
			String[] lines = br.readLine().split(" ");
			int r = Integer.parseInt(lines[0]) - 1;
			int c = Integer.parseInt(lines[1]) - 1;
			int w = Integer.parseInt(lines[2]);
			
			board[r][c].tree.add(w);
		}
		
		int answer = go(0);
		System.out.println(answer);
	}
	
	public static int go(int year) {
		while(year < K) {
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					spring(r, c);
					summer(r, c);
					fall(r, c);
					winter(r, c);					
				}
			}
			newTrees();
			year++;
		}
		
		return check();
	}
	
	public static void spring(int r, int c) {
		Queue<Integer> q = new LinkedList<Integer>();
		int nutri_save = board[r][c].nutri_save;
		for(int index=0, size=board[r][c].size(); index<size; index++) {
			int cur = board[r][c].tree.poll();
			
			if(nutri_save >= cur) {
				nutri_save -= cur;
				q.add(cur+1);
			} else {
				board[r][c].nutri_add += cur/2;
			}
		}
		board[r][c].nutri_save = nutri_save;
		
		while(!q.isEmpty()) {
			board[r][c].tree.add(q.poll());
		}
	}
	
	public static void summer(int r, int c) {
		board[r][c].nutri_save += board[r][c].nutri_add;
		board[r][c].nutri_add = 0;
	}
	
	public static void fall(int r, int c) {
		Queue<Integer> q = new LinkedList<Integer>();
		while(!board[r][c].tree.isEmpty()) {
			int age = board[r][c].tree.poll();
			
			if(age % 5 == 0) {
				for(int iter=0; iter<8; iter++) {
					int nr = r + dr[iter];
					int nc = c + dc[iter];
					
					if(0<=nr && nr<N && 0<=nc && nc<N) {
						queue.add(new Pos(nr, nc));
					}
				}
				
			}
			q.add(age);
		}
		
		while(!q.isEmpty()) {
			board[r][c].tree.add(q.poll());
		}
	}
	
	public static void winter(int r, int c) {
		board[r][c].nutri_save += addValues[r][c];
	}
	
	public static void newTrees() {
		while(!queue.isEmpty()) {
			Pos pos = queue.poll();
			
			board[pos.r][pos.c].tree.add(1);
		}
	}
	
	public static int check() {
		int sum = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				sum += board[r][c].size();
			}
		}
		return sum;
	}
}

class Trees {
	PriorityQueue<Integer> tree;
	int nutri_add;
	int nutri_save;
	
	public Trees() {
		this.tree = new PriorityQueue<Integer>();
		this.nutri_add = 0;
		this.nutri_save = 5;
	}
	
	public int size() {
		return tree.size();
	}
}

class Pos {
	int r, c;
	
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
