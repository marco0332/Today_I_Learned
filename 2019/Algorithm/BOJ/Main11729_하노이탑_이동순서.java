import java.util.*;
import java.io.*;

public class Main {
	static BufferedWriter bw;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write((int)Math.pow(2, N) - 1 + "\n");
		hanoi(N, 1, 3);
		
		bw.flush();
		bw.close();

		sc.close();
	}
	
	public static void hanoi(int N, int position, int target) throws IOException {
		if (N == 1) {
			bw.write(position + " " + target + "\n");
			return;
		}
		int nextPosition = 6 - position - target;
		hanoi(N - 1, position, nextPosition);
		bw.write(position + " " + target + "\n");
		hanoi(N - 1, nextPosition, target);
	}
}