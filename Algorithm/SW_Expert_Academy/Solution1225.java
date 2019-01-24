import java.io.*;
import java.util.*;

public class Solution1225 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int t = 1; t <= 10; t++) {
			br.readLine();

			Queue<Integer> q = new LinkedList<>();

			String[] input = br.readLine().split(" ");
			int[] arr = new int[8];
			int idx = 0;
			boolean check = true;
			
			int minDiv = 987654321;
			for (String s : input) {
				arr[idx++] = Integer.parseInt(s);
				
				minDiv = Math.min(minDiv, arr[idx-1]/15);
			}
			
			minDiv--;
			for(int i=0; i<8; i++) {
				q.add(arr[i]-minDiv*15);
			}

			check = false;
			while (true) {
				for (int i = 1; i <= 5; i++) {
					int val = q.poll();

					if (val - i <= 0) {
						q.add(0);
						check = true;
						break;
					}
					q.add(val - i);
				}
				if (check)
					break;
			}

			bw.write("#" + t + " ");
			for (int i = 0; i < 8; i++) {
				bw.write(q.poll() + " ");
			}
			bw.write("\n");
			bw.flush();
		}
	}
}
