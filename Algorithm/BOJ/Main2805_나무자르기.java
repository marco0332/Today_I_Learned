import java.io.*;

public class Main2805_나무자르기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		int maxVal = Integer.MIN_VALUE;
		int minVal = 0;
		int[] arr = new int[N];
		String[] input = br.readLine().split(" ");
		for(int n=0; n<N; n++) {
			arr[n] = Integer.parseInt(input[n]);
			
			if(maxVal < arr[n]) maxVal = arr[n];
		}
		
		while(minVal<=maxVal) {
			int H = (maxVal+minVal)/2;
			long Sum = 0;
			
			for(int n=0; n<N; n++) {
				if(arr[n] > H)
					Sum += arr[n]-H;
			}
			
			if(Sum == M) {
				sb.append(H);
				break;
			} else if(Sum < M) {
				maxVal = H;
			} else if(minVal == H) {
				sb.append(H);
				break;
			} else if(Sum > M) {
				minVal = H;
			}
		}
		
		System.out.println(sb);
	}
}
