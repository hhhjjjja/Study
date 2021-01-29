package swea2115_벌꿀채취;
/*https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int[][] map;
	static int[][] worker;
	static boolean[] selected;
	static int[] money;
	static int C, M, N, result;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++){
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());	//선택할 수 있는 벌통 개수
			C = Integer.parseInt(st.nextToken());	//채취할 수 있는 꿀의 최대 양
			
			map = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			selected = new boolean[M];
			worker = new int[2][M];			//i:일꾼 2명 num,  j:선택한 벌통에 있는 꿀
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;
			selectHoneyWk(visited, 0, 0);
			
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	static void selectHoneyWk(boolean[][] visited, int start, int r) {
		if(r == 2) {
			money = new int[2];
			
			for(int i=0; i<2; i++) {
				honeyCombination(i, 0, 0);
			}
			//허니콤보 끝나면 total = money[0]+money[1]한 값 구하기
			//total중 가장 큰 토탈이 답
			
			int total = money[0] + money[1];
			result = Math.max(total, result);
			
			return;
		}
		
		for(int i=start; i<N; i++) {
			out : for(int j=0; j<N; j++) {
				//가로로 M개 선택이 불가능할 때 제외
				if(j+M-1 >= N) continue;
				
				//이미 선택한 벌통 제외
				for(int k=0; k<M; k++) {
					if (visited[i][j+k]) continue out;
				}

				//벌통선택
				for(int k=0; k<M; k++) {
					//(i, j), (i, j+1), ... 가로로 k개 선택
					visited[i][j+k] = true;
					worker[r][k] = map[i][j+k];
				}
				
				selectHoneyWk(visited, start, r+1);
				
				//선택된 벌통 해제
				for(int k=0; k<M; k++) {
					visited[i][j+k] = false;
				}
			}
		}
	}
	
	static void honeyCombination(int wkNum, int sum, int m) {
		//일꾼 1번등장
		//일꾼1번이 선택할 수 있는 벌통 중에서 가장 많은걸 찾아서 money[0]에 저장
		//일꾼 2번등장 ...
		
		money[wkNum] = Math.max(money[wkNum], m);
			
		for(int i=0; i<M; i++) {
			//선택된 벌통 통과
			if(selected[i]) continue;
			
			if(sum + worker[wkNum][i] <= C) {
				selected[i] = true;
				honeyCombination(wkNum, sum+worker[wkNum][i], m+worker[wkNum][i]*worker[wkNum][i]);
				selected[i] = false;
			}
		}
	}
}