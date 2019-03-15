import java.io.*;
import java.util.*;

public class Main15973_두박스 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Task solver = new Task(br,bw);
		solver.solve();
		bw.close();
	}
	
	static class Task {
		BufferedReader br;
		BufferedWriter bw;
		
		public Task(BufferedReader br, BufferedWriter bw) {
			this.br = br;
			this.bw = bw;
		}
		
		public void solve() throws IOException {
			String[] line = br.readLine().split(" ");
			Box b1 = new Box(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));
			line = br.readLine().split(" ");
			Box b2 = new Box(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));
			
			bw.write(go(b1,b2)+"\n");
		}
		
		public String go(Box b1, Box b2) {
			if((b1.x1 == b2.x2 && b1.y1 == b2.y2) ||
					(b1.x2 == b2.x1 && b1.y1 == b2.y2) || 
					(b1.x1 == b2.x2 && b1.y2 == b2.y1) ||
					(b1.x2 == b2.x1 && b1.y2 == b2.y1)) {
				return "POINT";
			}
			else if(b2.x2 < b1.x1 ||
					b1.x2 < b2.x1 ||
					b1.y1 > b2.y2 ||
					b2.y1 > b1.y2) {
				return "NULL";
			}
			else if(b1.x1 == b2.x2 ||
					b1.x2 == b2.x1 ||
					b1.y1 == b2.y2 ||
					b1.y2 == b2.y1) {
				return "LINE";
			}
			else
				return "FACE";
		}
	}
	
	static class Box {
		int x1, y1, x2, y2;
		
		public Box(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
}
