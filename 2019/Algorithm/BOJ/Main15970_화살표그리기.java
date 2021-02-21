import java.io.*;
import java.util.*;

public class Main15970_화살표그리기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
		for(int n=0; n<=N; n++) {
			arr.add(new ArrayList<Integer>());
		}
		
		for(int n=0; n<N; n++) {
			String[] line = br.readLine().split(" ");
			arr.get(Integer.parseInt(line[1])).add(Integer.parseInt(line[0]));
		}
		for(int n=0; n<=N; n++) {
			Collections.sort(arr.get(n));
		}
		
		int ans = 0;
		for(int n=1; n<=N; n++) {
			for(int i=0,size=arr.get(n).size(); i<size && size>=2; i++) {
				if(i == 0) {
					ans += arr.get(n).get(i+1) - arr.get(n).get(i);
				}else if(i == size-1) {
					ans += arr.get(n).get(i) - arr.get(n).get(i-1);
				}else {
					ans += Math.min(arr.get(n).get(i+1) - arr.get(n).get(i), arr.get(n).get(i) - arr.get(n).get(i-1));
				}
			}
		}
		bw.write(ans+"\n");
		bw.close();
	}
}
