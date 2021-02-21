import java.io.*;
import java.util.*;

class Dot implements Comparable<Dot> {
	int x, y;
	
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(Dot d1) {
		return this.x - d1.x;
	}
}

class yCompare implements Comparator<Dot> {
	@Override
	public int compare(Dot d1, Dot d2) {
		if(d1.y != d2.y) return d1.y - d2.y;
		else return d1.x - d2.x;
	}
}

public class Main2261_가장가까운두점 {
	static int N;
	static ArrayList<Dot> arr;
	
	public static int dist(Dot d1, Dot d2) {
		return (d1.x - d2.x)*(d1.x - d2.x) + (d1.y - d2.y)*(d1.y - d2.y); 
	}
	
	public static int go(int start, int end) {
		if(end == start)
			return Integer.MAX_VALUE;
		if(end - start == 1) 
			return dist(arr.get(start), arr.get(end));
		
		int mid = (start+end)/2;
		int min = Math.min(go(start, mid), go(mid+1, end));
		int d = (int) Math.sqrt(min) + 1;
		
		double centerLine = (arr.get(mid).x + arr.get(mid+1).x)/2.0;
		ArrayList<Dot> tmpList = new ArrayList<Dot>();
		
		/** Left */
		for(int i=mid; i>=0; i--) {
			if(centerLine - arr.get(i).x >= d)
				break;
			
			tmpList.add(arr.get(i));
		}
		/** Right */
		for(int i=mid+1; i<=end; i++) {
			if(centerLine - arr.get(i).x >= d)
				break;
			
			tmpList.add(arr.get(i));
		}
		
		Collections.sort(tmpList, new yCompare());
		for(int i=0,size=tmpList.size(); i<size-1; i++) {
			Dot d1 = tmpList.get(i);
			for(int j=i+1; j<size; j++) {
				Dot d2 = tmpList.get(j);
				if(d <= d1.y-d2.y) break;
				
				int val = dist(d1, d2);
				if(min > val) {
					min = val;
					d = (int) Math.sqrt(min) + 1;
				}
			}
		}
		
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList<Dot>();
		for(int n=0; n<N; n++) {
			String[] input = br.readLine().split(" ");
			
			arr.add(new Dot(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
		}
		Collections.sort(arr);
		
		int answer = go(0, N-1);
		if(answer == Integer.MAX_VALUE) answer = 0; 
		System.out.println(answer);
	}
}