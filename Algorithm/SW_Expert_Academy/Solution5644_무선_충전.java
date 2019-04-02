import java.io.*;
import java.util.*;

public class Solution5644_무선_충전 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br, bw);
		solver.solve();
		bw.close();
	}

	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		int M, A, N = 10;
		int[] aPath, bPath;
		int[] dr = { 0, -1, 0, 1, 0 };
		int[] dc = { 0, 0, 1, 0, -1 };
		BC[] bcList;
		int ay, ax, by, bx;
		int answer;

		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}

		public void solve() throws IOException {
			int T = Integer.parseInt(br.readLine());

			for (int t = 1; t <= T; t++) {
				preprocess();

				for (int m = 0; m < M; m++) {
					answer += calc();
					
					ax += dc[aPath[m]];
					ay += dr[aPath[m]];
					bx += dc[bPath[m]];
					by += dr[bPath[m]];
				}
				answer += calc();
//				System.out.println();
				bw.write("#" + t + " " + answer + "\n");
			}
		}

		public int calc() {
			ArrayList<BC> containA = new ArrayList<>();
			ArrayList<BC> containB = new ArrayList<>();

			for (int a = 0; a < A; a++) {
				if (distCheck(ay, ax, bcList[a])) {
					containA.add(bcList[a]);
				}
				if (distCheck(by, bx, bcList[a])) {
					containB.add(bcList[a]);
				}
			}

			int val = 0;
			if (containA.size() == 0 && containB.size() == 0) {
				;
			} else if (containA.size() == 0) {
				for (int j = 0, bSize = containB.size(); j < bSize; j++) {
					val = Math.max(val, containB.get(j).p);
				}
			} else if (containB.size() == 0) {
				for (int i = 0, aSize = containA.size(); i < aSize; i++) {
					val = Math.max(val, containA.get(i).p);
				}
			} else {
				for (int i = 0, aSize = containA.size(); i < aSize; i++) {
					for (int j = 0, bSize = containB.size(); j < bSize; j++) {
						if (containA.get(i).num == containB.get(j).num) {
							val = Math.max(val, containA.get(i).p);
						} else {
							val = Math.max(val, containA.get(i).p + containB.get(j).p);
						}
					}
				}
			}
//			System.out.println("ay:"+ay+", ax:"+ax+", by:"+by+", bx:"+bx+"  " +val+" ");

			return val;
		}

		public boolean distCheck(int y, int x, BC cur) {
			int d = Math.abs(y - cur.y) + Math.abs(x - cur.x);

			return d <= cur.c ? true : false;
		}

		public void preprocess() throws IOException {
			answer = 0;
			ay = 0;
			ax = 0;
			by = N - 1;
			bx = N - 1;

			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			aPath = new int[M];
			bPath = new int[M];
			bcList = new BC[A];
			String[] path = br.readLine().split(" ");
			for (int m = 0; m < M; m++) {
				aPath[m] = Integer.parseInt(path[m]);
			}
			path = br.readLine().split(" ");
			for (int m = 0; m < M; m++) {
				bPath[m] = Integer.parseInt(path[m]);
			}
			for (int a = 0; a < A; a++) {
				String[] line = br.readLine().split(" ");
				bcList[a] = new BC(Integer.parseInt(line[0])-1, Integer.parseInt(line[1])-1, Integer.parseInt(line[2]),
						Integer.parseInt(line[3]), a + 1);
			}
		}
	}

	static class BC {
		int y, x, c, p, num;

		public BC(int x, int y, int c, int p, int num) {
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
			this.num = num;
		}
	}
}
