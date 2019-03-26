import java.io.*;
import java.util.*;

public class Main11508_2더하기1_세일 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<>();
		for(int n=0; n<N; n++) {
			arr.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(arr, Collections.reverseOrder());
		int cnt = 0;
		int answer = 0;
		for(int n=0; n<N; n++) {
			cnt++;
			if(cnt % 3 == 0) continue;
			answer += arr.get(n);
		}
		System.out.println(answer);
	}
}
