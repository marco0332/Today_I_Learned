import java.io.*;
import java.util.*;

public class Solution1228 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int t=1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine());
			
			ArrayList<String> origin = new ArrayList<String>();
			for(String text : br.readLine().split(" ")) {
				origin.add(text);
			}
			
			int O = Integer.parseInt(br.readLine());
			String[] orders = br.readLine().trim().split("I ");
			for(int o=1; o<=O; o++) {
				String[] order = orders[o].split(" ");
				
				int idx = Integer.parseInt(order[0]);
				int len = Integer.parseInt(order[1]);
				
				ArrayList<String> insert = new ArrayList<String>();
				for(int i=0; i<len; i++) {
					insert.add(order[2+i]);
				}
				origin.addAll(idx,insert);
			}
			
			bw.write("#"+t+" ");
			for(int i=0; i<10; i++) {
				bw.write(origin.get(i)+" ");
			}
			bw.write("\n");
			bw.flush();
		}
	}
}
