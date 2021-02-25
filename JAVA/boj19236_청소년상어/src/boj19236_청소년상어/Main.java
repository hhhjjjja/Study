package boj19236_청소년상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
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
	
	static int[][] map = new int[4][4];
	static Info[] fishes = new Info[17];
	
	//[0]은 사용x, 1부터 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	
	static int answer = 0;
	static int sr, sc, sd;			//상어 좌표 r, c, 상어방향 sd
	
	public static void main(String[] args) throws IOException {
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
		
		sr = sc = 0;		//상어 위치 (0, 0)
		sd = fishes[map[0][0]].direct;		//상어 방향 (0,0)에 있던 물고기의 방향
		
		answer += map[0][0];		//(0, 0) 물고기 먹었으니까 물고기 번호+
		fishes[map[0][0]] = null;	//먹었으니 지우기
		map[0][0] = -1;				//상어위치 state -1
		
		dfs();
		
		System.out.println(answer);
	}
	
	static void dfs() {
		//물고기 이동 1번부터 16번까지
		for(int i=1; i<=16; ++i) {
			Info fish = fishes[i];		//지금 물고기
			
			if(fish == null) continue;
			
			int nd = fish.direct;
			//이동할 수 있는 방향 찾기
			while(true) {
				boolean flag = false;
				
				int nr = fish.r + dr[fish.direct];
				int nc = fish.c + dc[fish.direct];
				
				//맵에서 벗어나거나 상어가 있으면 반시계 45도 회전
				if(nr<0 || nr>=4 || nc<0 || nc>=4 || map[nr][nc] == -1) {
					fish.direct = (fish.direct + 1) % 8;
					flag = true;
					continue;
				}
				
				Info t = fishes[map[nr][nc]];
				//물고기 없는칸으로 이동하면 지금 물고기 정보만 갱신
				if(t == null) {
					fishes[i] = new Info(nr, nc, fish.direct);
				} else {
					//빈칸이 아니면 물고기 정보 서로 교환
					fishes[map[nr][nc]] = new Info(fish.r, fish.c, t.direct);	//이동할 좌표에 지금물고기 정보로 갱신
					fishes[i] = new Info(nr, nc, fish.direct);		//지금물고기 있던 칸으로 이동한좌표에 있던 물고기 정보 갱신
				}
				
				int mtmp = map[nr][nc];
				map[nr][nc] = i;
				map[fish.r][fish.c] = mtmp;
				
				//돌아간 적이 있고 시작했던 방향(nd) == 마지막 방향(fish.direct)이면 이동할수있는 칸이 없음
				if(flag && nd == fish.direct) break;
			}
		}
		
		//상어 이동
		for(int i=1; i<4; ++i) {
			int nr = sr + dr[sd]*i;
			int nc = sc + dr[sd]*i;
			
			if(nr<0 || nr>=4 || nc<0 || nc>=3) break;
			if(map[nr][nc] == 0) continue;
		}
	}
}