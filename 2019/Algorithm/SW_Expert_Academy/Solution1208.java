import java.io.*;
import java.util.*;
 
public class Solution1208 {
    static int[] boxes;
     
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        for(int t=1; t<=10; t++) {
            int D = Integer.parseInt(br.readLine());
            int Max = 0, Min = 987654321;
            boxes = new int[101];
             
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<100; i++) {
                int val = Integer.parseInt(st.nextToken());
                boxes[val]++;
                 
                Max = Math.max(Max, val);
                Min = Math.min(Min, val);
            }
             
            while(D>0) {
                boxes[Max]--; boxes[Max-1]++;
                boxes[Min]--; boxes[Min+1]++;
                D--;
                
                if(Max-Min <= 1) break;
                if(boxes[Max] < 1) {
                	while(boxes[Max] < 1) Max--;
                }
                if(boxes[Min] < 1) {
                	while(boxes[Min] < 1) Min++;
                }
            }
             
            System.out.printf("#%d %d\n", t, Max-Min);
        }
    }
}