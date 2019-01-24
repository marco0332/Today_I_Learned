import java.util.Scanner;

public class Solution2019 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int cur = 1;
		for(int i=0; i<=N; i++) {
			System.out.print(cur+" ");
            cur *= 2;
		}
	}
}