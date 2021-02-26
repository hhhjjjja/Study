package boj19236_청소년상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Info {
		int r, c, direct;
		
		public Info(int r, int c, int direct) {
			this.r = r;
			this.c = c;
			this.direct = direct;
		}
	}
	
	//1부터 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		int[][] map = new int[4][4];
		Info[] fishes = new Info[17];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<4; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<4; ++j) {
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				fishes[s] = new Info(i, j, d);		//s번 물고기의 방향 d
				map[i][j] = s;			//i, j에 s번 물고기 있음
			}
		}
		
		int answer = map[0][0];		//(0, 0) 물고기 먹었으니까 물고기 번호+
		Info shark = new Info(0, 0, fishes[map[0][0]].direct);		//상어 시작위치는 0,0
		
		fishes[0] = new Info(0, 0, -1);		//null칸 채워주기
		
		fishes[map[0][0]] = new Info(0, 0, -1);	//먹었으니 지우기
		map[0][0] = -1;				//상어위치 state -1
		
		dfs(map, fishes, answer, shark);
		
		System.out.println(max);
	}
	
	static void dfs(int[][] map, Info[] fishes, int answer, Info shark) {
		//원래 데이터 유지위해서 데이터 카피
		int[][] map2 = new int [4][4];
		Info[] fishes2 = new Info[17];
		
		for(int i=0; i<4; ++i) {
			System.arraycopy(map[i], 0, map2[i], 0, 4);
		}
		
		for(int i=1; i<17; ++i) {
			fishes2[i] = new Info(fishes[i].r, fishes[i].c, fishes[i].direct);
		}
		
		//물고기 이동 1번부터 16번까지
		for(int i=1; i<=16; ++i) {
			if(fishes2[i].direct == -1) continue;
			
			Info fish = fishes2[i];		//지금 물고기
			
			int nd = fish.direct;
			//이동할 수 있는 방향 찾기
			for(int d=0; d<8; ++d) {
				nd %= 8;
				
				int nr = fish.r + dr[nd];
				int nc = fish.c + dc[nd];
				
				//맵에서 벗어나거나 상어가 있으면 반시계 45도 회전
				if(nr<0 || nr>=4 || nc<0 || nc>=4 || map[nr][nc] == -1) {
					nd++;
					continue;
				}
		
				if(map2[nr][nc] == 0) {
					map2[fish.r][fish.c] = 0;
					map2[nr][nc] = i;
					fishes2[i] = new Info(nr, nc, nd);
					break;
				} else if(map2[nr][nc] > 0 ) {
					map2[fish.r][fish.c] = map2[nr][nc];
					
					//빈칸이 아니면 물고기 정보 서로 교환
					fishes2[map2[nr][nc]] = new Info(fish.r, fish.c, fishes2[map2[nr][nc]].direct);	//이동할 좌표에 지금물고기 정보로 갱신
					map2[nr][nc] = i;
					fishes2[i] = new Info(nr, nc, nd);		//지금물고기 있던 칸으로 이동한좌표에 있던 물고기 정보 갱신
					break;
				}
			}
		}
		
		boolean ff = false;
		
		//상어 이동
		for(int i=1; i<=4; ++i) {
			
			int nr = shark.r + dr[shark.direct]*i;
			int nc = shark.c + dc[shark.direct]*i;
			
			//맵에서 벗어나면 break
			if(nr<0 || nr>=4 || nc<0 || nc>=4) break;
			
			int tmp = map2[nr][nc];
			
			//물고기 있는 칸이면
			if(tmp > 0) {
				ff = true;
				
				map2[shark.r][shark.c] = 0;
				
				//상어 이동, 정보갱신
				Info s = new Info(nr, nc, fishes2[tmp].direct);
				//(nr, nc)좌표에 있던 물고기 먹었으니까 물고기 지우기
				fishes2[tmp] = new Info(0, 0, -1);
				map2[nr][nc] = -1;
				
				dfs(map2, fishes2, answer+tmp, s);
				
				map2[nr][nc] = tmp;		//값 되돌려놓기
				fishes[tmp] = new Info(nr, nc, s.direct);
				map2[shark.r][shark.c] = -1;
			}
		}
		
		if(!ff) max = Math.max(max, answer);
	}
}