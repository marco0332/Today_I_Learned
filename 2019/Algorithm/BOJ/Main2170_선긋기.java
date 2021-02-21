import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2170 {
   static int N, start, end, length;
   static StringTokenizer st;
   public static void main(String[] args) throws Exception{
//      BufferedReader br = new BufferedReader(new FileReader("res/Main_2170_선긋기.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      line[] linearr = new line[N];
      for(int i=0; i<N; i++) {
         st = new StringTokenizer(br.readLine());
         start = Integer.parseInt(st.nextToken());
         end = Integer.parseInt(st.nextToken());   
         
         linearr[i] = new line(start, end);         
      }
      Arrays.sort(linearr);
      
      int min = linearr[0].start;
      int max = linearr[0].end;
      
      for(int i=1; i<N; i++) {
         if(linearr[i].start<=max) {
            if(linearr[i].end<=max) {
               continue;
            }
            max = linearr[i].end;
            continue;
         }else {
            length += max-min;
            min = linearr[i].start;
            max = linearr[i].end;
         }
      }
      
      length += max-min;
      System.out.println(length);
      
   }
   
   static class line implements Comparable<line>{
      private int start, end;
      
      public line(int start, int end) {
         this.start = start;
         this.end = end;
      }

      @Override
      public int compareTo(line o) {
         if(this.start < o.start)return -1;
         else if(this.start > o.start) return 1;
         else if(this.end < o.end)return -1;
         else if(this.end > o.end)return 1;
         else return 0;
      }
      
   }
}