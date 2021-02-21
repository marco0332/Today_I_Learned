import java.io.*;
import java.util.*;

public class Solution1949 {
	static int Answer;
	static int N, K;
	
	public static void main(String args[]) throws IOException {
		Go(); // 입력 및 결과 출력
	}
	
	// 입력 및 결과 출력 함수
	public static void Go() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken()); // 테스트케이스 개수
		for (int t = 1; t <= T; t++) {
			Answer = 0; // 답 초기화
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 등산 맵 크기
			K = Integer.parseInt(st.nextToken()); // 굴삭할 수 있는 최대 크기

			int[][] mountain = new int[N][N]; // 등산 맵 생성

			int HIGHEST = 0; // 가장 큰 봉우리부터 시작해야해서, 그 값을 보관하기 위한 변수
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					mountain[r][c] = Integer.parseInt(st.nextToken());
					
					// 가장 높은 봉우리 찾기
					if (HIGHEST < mountain[r][c])
						HIGHEST = mountain[r][c];
				}
			}
			
			// 가장 높은 봉우리들의 위치를 보관할 리스트배열 생성
			ArrayList<node> h_arr = new ArrayList<node>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (mountain[r][c] == HIGHEST)
						h_arr.add((new node(c, r))); // (Y좌표, X좌표) 순으로 저장
				}
			}
			
			boolean[][] check = new boolean[N][N];
			for(int i=0; i<h_arr.size(); i++) {
				check[h_arr.get(i).r][h_arr.get(i).c] = true; // 현재 위치 방문했다고 기록
				check[h_arr.get(i).r][h_arr.get(i).c] = true;
				
				// 현재 봉우리에서 가장 긴 등산로 찾는 함수. DFS이용
				MakeRoad(h_arr.get(i).c, h_arr.get(i).r, mountain, check, false, 1);
				
				check[h_arr.get(i).r][h_arr.get(i).c] = false; // 나왔으니까 기록 해제
			}
			
			// 답 출력
			System.out.println("#"+t+" "+Answer);
		}
	}
	
	static final int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // 상,하,좌,우
	
	// 현재 봉우리에서 가장 긴 등산로 찾는 함수. DFS이용
	public static void MakeRoad(int c, int r, int[][] mountain, boolean[][] check, boolean isDig, int dist) {
		for(int i=0; i<4; i++) {
			int nr = r + dir[i][0]; // 현재 위치에서 Y좌표 이동
			int nc = c + dir[i][1]; // 현재 위치에서 X좌표 이동
			
			// 다음 위치가 범위를 벗어나지 않고, 방문한 적이 없을 때 DFS 진행
			if(0<=nr && nr<N && 0<=nc && nc<N && check[nr][nc] == false) {
				int diff = mountain[nr][nc] - mountain[r][c]; // 다음 위치의 높이와 현재 높이의 차이를 저장
				
				// 다음 봉우리가 현재 봉우리보다 높을 경우
				// 굴삭해서 들어갈 수 있기 때문에 if문으로 나누었음. 굴삭은 딱 한 번만 할 수 있음
				// 굴삭했는지 여부는 isDig를 통해 확인
				if(diff >= 0) {
					// 굴삭한적 없고, 차이+1이 K이하일 경우 진행
					// diff+1인 이유는
					// 다음 봉우리가 5이고, 현재 봉우리가 3일 때 다음 봉우리가 2가 되어야 내려갈 수 있으므로
					// 차이+1 만큼 굴삭해야하므로.
					if(isDig == false && diff+1 <= K) {
						check[nr][nc] = true; // 다음 봉우리 방문했다고 기록
						int tmp = mountain[nr][nc]; // 다음 봉우리 높이 임시저장 (굴삭하면 높이 달라지므로)
						mountain[nr][nc] = mountain[r][c] - 1; // 다음 봉우리를 현재 봉우리높이 -1로 저장(높을수록 좋으니까)
						isDig = true; // 굴삭했다고 기록
						
						MakeRoad(nc, nr, mountain, check, isDig, dist+1);
						
						isDig = false; // 굴삭 기록 해제
						mountain[nr][nc] = tmp; // 원래 높이로 복원
						check[nr][nc] = false; // 방문 기록 해제
					}
				}
				// 다음 봉우리가 현재 봉우리보다 낮을 경우
				else {
					check[nr][nc] = true; // 다음 봉우리 방문했다고 기록
					
					MakeRoad(nc, nr, mountain, check, isDig, dist+1);
					
					check[nr][nc] = false; // 방문 기록 해제
				}
			}
		}
		
		Answer = Math.max(Answer, dist); // 현재 보관하고 있는 답과 현재 구한 답 중 큰 것을 저장.
	}
}

