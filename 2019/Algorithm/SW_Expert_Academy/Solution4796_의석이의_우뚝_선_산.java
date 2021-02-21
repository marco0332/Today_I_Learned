import java.io.*;
import java.util.*;

public class Solution4796_의석이의_우뚝_선_산 {
	static BufferedReader br;
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Scanner sc = new Scanner(System.in);
		
//		int T = Integer.parseInt(br.readLine());
		int T = numGetter();
		for(int t=1; t<=T; t++) {
//			int N = Integer.parseInt(br.readLine());
			int N = numGetter();
			
			int[] arr = new int[N];
//			String[] line = br.readLine().split(" ");
			for(int n=0; n<N; n++) {
//				arr[n] = Integer.parseInt(line[n]);
				arr[n] = numGetter();
			}
			
			int[] check1 = new int[N];
			int[] check2 = new int[N];
			for(int n=0; n<N-1; n++) {
				if(arr[n] < arr[n+1]) {
					if(n>0) check1[n] = check1[n-1]-1;
					else check1[n] = -1;
				}
			}
			
			for(int n=N-1; n>0; n--) {
				if(arr[n-1] > arr[n]) {
					if(n<N-1) check2[n] = check2[n+1]+1;
					else check2[n] = 1;
				}
			}
			
			int answer = 0;
			for(int n=1; n<N-1; n++) {
				if(check1[n] == 0 && check2[n] == 0) {
					int l = -check1[n-1];
					int r = check2[n+1];
					
					if(l > 0 && r > 0)
						answer += l * r;
				}
			}
			bw.write("#"+t+" "+answer+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static int numGetter() throws Exception {
        int rd = br.read();
        int num = 0;
        boolean beep = false;
        while (!beep) {
            num = num * 10 + (rd - '0');
            rd = br.read();
             
            if('0' > rd || rd > '9' ) {
                beep = true;
            }
            if(rd == '\r') {
                rd = br.read();
            }
        }
        return num;
    }
}
