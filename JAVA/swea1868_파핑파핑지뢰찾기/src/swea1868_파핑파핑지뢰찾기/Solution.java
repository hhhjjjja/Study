package swea1868_파핑파핑지뢰찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int T, N;
	static char [][] map;
	static int [][] landMine;
	static boolean[][] visited;
	
	//왼위부터 시계방향
	//대각선까지 탐색해야하므로 8
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		
		for(int t=1; t<=T; ++t) {
			answer = 0;			//클릭 수 초기화
			N = Integer.parseInt(br.readLine().trim());
			
			map = new char [N][N];				//지뢰찾기 input맵
			landMine = new int[N][N];			//주변에 있는 지뢰 개수
			visited = new boolean[N][N];		//방문확인
			
			//input
			for(int i=0; i<N; ++i) {
				map[i] = br.readLine().toCharArray();
			}
			
			//주변 지뢰개수 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//지뢰칸 찾으면 주변 칸에 지뢰 개수 +1
					if(map[i][j] == '*') {
						for(int d=0; d<8; ++d) {
							int nr = i + dr[d];
							int nc = j + dc[d];
							
							//nr,nc좌표가 맵 범위 내면
							if(nr>=0 && nr<N && nc>=0 && nc<N) {
								landMine[nr][nc]++;
							}
							//지뢰가 있는 칸은 방문했다고 침
							visited[i][j] = true;
						}
					}
				}
			}
			
			check();
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	static void check() {
		//0부터 찾아서 처리
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(landMine[i][j] == 0 && !visited[i][j]) {
					answer++;	//클릭수 한번 늘리기
					visited[i][j] = true;
					
					//주변에 있는 0들은 한번클릭으로 다 열기 위해서 bfs사용
					Queue<Point> bfs  = new LinkedList<>();
					bfs.offer(new Point(i, j));
					
					while(!bfs.isEmpty()) {
						Point p = bfs.poll();
						
						for(int d=0; d<8; ++d) {
							int nr = p.r + dr[d];
							int nc = p.c + dc[d];
							
							//탐색하는 좌표가 맵 범위 내이고, 지뢰아니고 방문한적도 없으면
							if(nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc] != '*' && !visited[nr][nc]) {
								visited[nr][nc] = true;
								
								if(landMine[nr][nc] == 0) {
									bfs.offer(new Point(nr, nc));
								}
							}
						}
					}
				}
			}
		}
		
		//0이외 나머지 칸들 열기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != '*' && !visited[i][j]) {
					visited[i][j] = true;
					answer++;
				}
			}
		}
		
	}
}
