import java.io.*;
import java.util.*;

public class Main2247_도서관 {
	static class Bar implements Comparable<Bar> {
		int s, e;
		
		public Bar(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Bar o) {
			if(this.s < o.s) return -1;
			else if(this.s > o.s) return 1;
			else if(this.e < o.e) return -1;
			else if(this.e > o.e) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Bar[] bars = new Bar[N];
		for(int n=0; n<N; n++) {
			String[] line = br.readLine().split(" ");
			
			bars[n] = new Bar(Integer.parseInt(line[0]),Integer.parseInt(line[1]));
		}
		Arrays.sort(bars);
		
		int Fill = Integer.MIN_VALUE;
		int Blank = Integer.MIN_VALUE;
		int s = 0, e = 0;
		for(int n=0; n<N; n++) {
			if(bars[n].s <= e) {
				e = Math.max(e, bars[n].e);
			} else {
				if(e != 0) {
					Blank = Math.max(Blank, bars[n].s-e);
				}
				
				s = bars[n].s;
				e = bars[n].e;
			}
			
			Fill = Math.max(Fill, e-s);
		}
		System.out.printf(Fill+" "+Blank);
	}
}
