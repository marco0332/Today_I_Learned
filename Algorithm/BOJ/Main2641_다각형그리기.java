import java.io.*;
import java.util.*;

public class Main2641_다각형그리기 {
	static ArrayList<String> arr = new ArrayList<String>();
	static int[] input, inputInverse;
	static int L;
	static int[] dir = {0,1,2,3,4,1,2};
	
	public static void makeString(int idx) {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		int after = idx-1;
		for(int i=idx; i<L; i++) {
			sb1.append(input[i]);
			sb2.append(inputInverse[i]);
		}
		for(int i=0; i<=after; i++) {
			sb1.append(input[i]);
			sb2.append(inputInverse[i]);
		}
		
		arr.add(sb1.toString());
		arr.add(sb2.toString());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		L = Integer.parseInt(br.readLine());
		input = new int[L];
		inputInverse = new int[L];
		String[] line = br.readLine().split(" ");
		for(int l=0; l<L; l++) {
			input[l] = Integer.parseInt(line[l]);
			inputInverse[L-1-l] = dir[input[l]+2];
		}
		
		for(int l=0; l<L; l++) {
			makeString(l);
		}
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<String> answer = new ArrayList<String>();
		for(int n=0; n<N; n++) {
			String cur = br.readLine().replace(" ","");
			
			boolean flag = false;
			for(int i=0,size=arr.size(); i<size; i++) {
				if(arr.get(i).equals(cur)) {
					flag = true;
					answer.add(cur);
					break;
				}
			}
		}
		
		bw.write(answer.size()+"\n");
		for(int i=0,size=answer.size(); i<size; i++) {
			for(int j=0; j<L; j++) {
				bw.write(answer.get(i).charAt(j)+" ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}
