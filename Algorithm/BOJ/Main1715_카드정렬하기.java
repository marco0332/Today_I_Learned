import java.io.*;
import java.util.*;

public class Main1715_카드정렬하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		int answer = 0;
		for(int n=0; n<N; n++) {
			q.add(Integer.parseInt(br.readLine()));
		}
		
		while(q.size()>1) {
			int v1 = q.poll();
			int v2 = q.poll();
			answer += v1+v2;
			q.add(v1+v2);
		}
		System.out.println(answer);
	}
}
