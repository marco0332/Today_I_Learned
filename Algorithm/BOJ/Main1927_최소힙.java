import java.io.*;
import java.util.*;

public class Main1927_최소힙 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());
		while(N-- > 0) {
			int input = Integer.parseInt(br.readLine());
			if(input == 0) {
				if(q.isEmpty()) System.out.println(0);
				else System.out.println(q.poll());
			}
			else
				q.add(input);
		}
	}
}
