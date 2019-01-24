import java.util.Scanner;

public class Solution2068 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int maxVal = Integer.MIN_VALUE;
			for (int i = 0; i < 10; i++) {
				int val = sc.nextInt();
				if (maxVal < val)
					maxVal = val;
			}
			System.out.printf("#%d %d\n", t, maxVal);
		}
	}
}
