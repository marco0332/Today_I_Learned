import java.io.*;
import java.util.*;

public class Main4641_Doubles {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		
		while(true) {
			answer = 0;
			String[] input = br.readLine().split(" ");
			if(input.length == 1) break;
			
			int[] data = new int[input.length-1];
			for(int i=0; i<input.length-1; i++) {
				data[i] = Integer.parseInt(input[i]);
			}
			
			Arrays.sort(data);
			for(int i=0; i<data.length-1; i++) {
				for(int j=i+1; j<data.length && data[i]*2 >= data[j]; j++) {
					if(data[i]*2 == data[j]) answer++;
				}
			}
			System.out.println(answer);
		}
	}
}
