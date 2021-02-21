import java.io.*;
import java.util.*;

public class Main1655_가운데를말해요 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> leftMax = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> rightMin = new PriorityQueue<>();
		
		leftMax.add(-987654321);
		rightMin.add(987654321);
		
		for(int n=0; n<N; n++) {
			int val = Integer.parseInt(br.readLine());
			
			if(leftMax.size() == rightMin.size()) {
				leftMax.add(val);
			}
			else {
				rightMin.add(leftMax.peek());
				leftMax.add(val);
			}
			bw.write(leftMax.peek()+"\n");
		}
		bw.flush();
		bw.close();
	}
}
