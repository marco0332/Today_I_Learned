import java.io.*;
import java.util.*;

public class Solution1206 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 총 10개
		for(int t=0; t<10; t++) {
			// 가로길이 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int width = Integer.parseInt(st.nextToken());
			
			// 건물 높이, 현재 위치기준 MAX(왼쪽2개), 현재 위치기준 MAX(오른쪽2개)를 저장할 배열 선언
			int[] buildings = new int[width];
			int[] l_diff = new int[width];
			int[] r_diff = new int[width];
			int answer = 0;
			
			// 건물 높이 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<width; i++) {
				buildings[i] = Integer.parseInt(st.nextToken());
			}
			// left
			for(int i=2; i<width-2; i++) {
				l_diff[i] = buildings[i-1] > buildings[i-2] ? buildings[i-1] : buildings[i-2];
			}
			// right
			for(int i=width-3; i>=2; i--) {
				r_diff[i] = buildings[i+2] > buildings[i+1] ? buildings[i+2] : buildings[i+1];
			}
			// ans
			// 현재 위치 기준으로 l_diff와 r_diff 중에서 큰 값이 현재 빌딩 높이보다 작다면, 그 차이만큼 answer에 더함.
			for(int i=2; i<width-2; i++) {
				int val = l_diff[i] > r_diff[i] ? l_diff[i] : r_diff[i];
				
				if(buildings[i] > val) answer += buildings[i] - val;
			}
			
			System.out.println("#"+(t+1)+" "+answer);
		}
	}
}
