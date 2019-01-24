import java.io.*;
import java.util.*;

public class Solution1983 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		double[] rate = {0.35, 0.45, 0.2};
		String[] degree = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
		for(int t=1; t<=T; t++) {
			String[] str = br.readLine().split(" ");
			int[] NK = new int[2];
			for(int i=0; i<2; i++) {
				NK[i] = Integer.parseInt(str[i]);
			}
			
			double[][] student = new double[NK[0]][2];
			for(int n=0; n<NK[0]; n++) {
				String[] scores = br.readLine().split(" ");
				double score = 0;
				for(int i=0; i<3; i++) {
					int val = Integer.parseInt(scores[i]);
					score += val*rate[i];
				}
				student[n][0] = score;
				student[n][1] = n+1;
			}
			
			Arrays.sort(student, new Comparator<double[]>() {
	            @Override
	            public int compare(double[] o1, double[] o2) {
	                return (int)(o1[0]*100 - o2[0]*100);
	            }
	        });
			
			int rank = 0;
			int jump = NK[0]/10;
			for(int i=student.length-1; i>=0; i--) {
				if(student[i][1] == NK[1]) break;
				else rank++;
			}
			System.out.printf("#%d %s\n", t, degree[rank/jump]);
		}
	}
}
