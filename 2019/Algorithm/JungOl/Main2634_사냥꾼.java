import java.io.*;
import java.util.*;

public class Main2634_사냥꾼 {
	static class Animal implements Comparable<Animal> {
		int x, y;
		
		public Animal(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Animal o) {
			if(this.x < o.x) return -1;
			else if(this.x > o.x) return 1;
			else if(this.y < o.y) return -1;
			else if(this.y > o.y) return 1; 
			else return 0;
		}
	}
	
	static ArrayList<Animal> arr;
	static int[] sadae;
	static int M, N, L;
	
	public static int binarySearch(int val) {
		int s = 0;
		int e = M;
		
		while(s<=e) {
			int mid = (s+e)/2;
			
			if(sadae[mid] == val) return mid;
			else if(sadae[mid] < val) s = mid+1;
			else if(sadae[mid] > val) e = mid-1;
		}
		
		if(s == -1) s = 0;
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] MNL = br.readLine().split(" ");
		M = Integer.parseInt(MNL[0]);
		N = Integer.parseInt(MNL[1]);
		L = Integer.parseInt(MNL[2]);
		sadae = new int[M];
		arr = new ArrayList<Animal>();
		
		String[] sadaes = br.readLine().split(" ");
		for(int m=0; m<M; m++) {
			sadae[m] = Integer.parseInt(sadaes[m]);
		}
		Arrays.sort(sadae);
		
		for(int n=0; n<N; n++) {
			String[] input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			if(y <= L) {
				arr.add(new Animal(x,y));
			}
		}
		Collections.sort(arr);
		
		int answer = 0;
		for(int n=0, size=arr.size(); n<size; n++) {
			int idx = binarySearch(arr.get(n).x) - 1;
			
			for(int i=0; i<3; i++) {
				if(0<=idx+i && idx+i<M) {
					if(Math.abs(arr.get(n).x-sadae[idx+i])+arr.get(n).y <= L) {
						answer++;
						break;
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}
