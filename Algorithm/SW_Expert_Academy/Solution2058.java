import java.util.Scanner;

public class Solution2058 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int val = sc.nextInt();
		int sum = 0;

		while (val > 0) {
			sum += val%10;
			val/=10;
		}
		System.out.println(sum);
	}
}