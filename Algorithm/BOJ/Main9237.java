import java.io.*;
import java.util.*;

public class Main9237 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		String[] input = br.readLine().split(" ");
		for(String s : input) {
			arr.add(Integer.parseInt(s));
		}
		Collections.sort(arr, Collections.reverseOrder());
		
		int answer = 0;
		for(int n=0; n<N; n++) {
			answer = Math.max(answer, arr.get(n)+n+1);
		}
		System.out.println(answer+1);
	}
}
