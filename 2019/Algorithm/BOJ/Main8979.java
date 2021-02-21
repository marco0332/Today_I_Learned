import java.io.*;
import java.util.*;

public class Main8979 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		long[][] total = new long[N][2];
//		long[][] total = new long[N][4];
		
		for(int n=0; n<N; n++) {
			total[n][1] = sc.nextInt();
			int gold = sc.nextInt();
			int silv = sc.nextInt();
			int bron = sc.nextInt();
//			total[n][3] = sc.nextInt();
//			total[n][0] = sc.nextInt();
//			total[n][1] = sc.nextInt();
//			total[n][2] = sc.nextInt();
			
			total[n][0] = gold*100000 + silv*1000 + bron;
		}
		
		Arrays.sort(total, new Comparator<long[]>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				for(int i=0; i<o1.length; i++) {
					if(o2[i] - o1[i] != 0)
						return (int)(o2[i] - o1[i]);
				}
				return 0;
			}
		});
		
		for(int i=0; i<N; i++) {
			if(total[i][1] == K) {
				while(i-1>=0 && total[i][0] == total[i-1][0])
					i--;
				System.out.println(i+1);
				break;
			}
		}
	}
}
