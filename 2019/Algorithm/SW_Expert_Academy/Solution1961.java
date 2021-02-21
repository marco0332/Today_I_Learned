import java.io.*;
import java.util.*;

public class Solution1961 {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int tn=Integer.parseInt(br.readLine());
        StringTokenizer st=null;
         
        for(int i=1;i<=tn;i++) {
            int N=Integer.parseInt(br.readLine());
            int[][] NbyN=new int [N][N];
            int[][] NbyN1=new int[N][N];
            int[][] NbyN2=new int[N][N];
            int[][] NbyN3=new int[N][N];
             
            for(int j=0;j<N;j++) {
                st=new StringTokenizer(br.readLine());
                for(int k=0;k<N;k++) {
                    NbyN[j][k]=Integer.parseInt(st.nextToken());
                }
            }
             
            for(int j=0;j<N;j++) {
                for(int k=0;k<N;k++) {
                    NbyN1[j][k]=NbyN[N-k-1][j];
                    NbyN2[j][k]=NbyN[N-j-1][N-k-1];
                    NbyN3[j][k]=NbyN[k][N-j-1];
                }
            }
             
            System.out.println("#"+i);
            for(int j=0;j<N;j++) {
                for(int k=0;k<N;k++) {
                    System.out.print(NbyN1[j][k]);
                }
                System.out.print(" ");
                for(int k=0;k<N;k++) {
                    System.out.print(NbyN2[j][k]);
                }
                System.out.print(" ");
                for(int k=0;k<N;k++) {
                    System.out.print(NbyN3[j][k]);
                }
                System.out.println();
            }
        }
	}
}
