import java.util.*;

public class Solution1966 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int N = sc.nextInt();
			
			int[] arr = new int[N];
			for(int n=0; n<N; n++) {
				arr[n] = sc.nextInt();
			}
			Arrays.sort(arr);
			System.out.print("#"+t+" ");
			for(int n=0; n<N; n++) {
				System.out.print(arr[n]+" ");
			}
			System.out.println();
		}
	}
}
