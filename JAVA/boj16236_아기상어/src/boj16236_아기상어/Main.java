package boj16236_아기상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r, c, dist;
		
		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	static int n;
	static int[][] map;
	static int answer, eatCnt;
	static int sr, sc;
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
			
	static ArrayList<Point> fish;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		int babySharkState = 2;
		
		for(int i=0; i<n; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; ++j) {
				//0 : 빈칸
				//1,2,3,4,5,6 : 칸에 있는 물고기의 크기
				//9 : 아기상어의 위치
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 9) {
					//아기상어의 위치 (i,j)
					sr = i;
					sc = j;
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			Queue<Point> bfs = new LinkedList<>();			//bfs
			
			fish = new ArrayList<>();			//먹이 물고기 리스트
			boolean visited[][] = new boolean[n][n];
			
			bfs.add(new Point(sr, sc, 0));
			visited[sr][sc] = true;
			
			//BFS
			while(!bfs.isEmpty()) {
				Point babyShark = bfs.poll();
				
				//상하좌우 탐색
				for(int i=0; i<4; ++i) {
					int nr = babyShark.r + dy[i];
					int nc = babyShark.c + dx[i];
					
					if(nr<0 || nr>=n || nc<0 || nc>=n) continue;			//좌표 범위 벗어나면 다른좌표 탐색
					if(visited[nr][nc]) continue;							//방문한 좌표는 지나가기
					
					//먹이를 찾으면(먹이는 1~6까지)
					//아기상어 크기보다 작으면 먹을수있는 물고기
					if (1 <= map[nr][nc] && map[nr][nc] <= 6 && map[nr][nc] < babySharkState) {
						bfs.offer(new Point(nr, nc, babyShark.dist + 1));		//상어의 위치 갱신, 한칸 이동했으므로 거리+1
						fish.add(new Point(nr, nc, babyShark.dist + 1));		//먹이 리스트에 삽입
						visited[nr][nc] = true; //방문 표시
					}
					
					else if(map[nr][nc] == babySharkState || map[nr][nc] == 0) {
						bfs.offer(new Point(nr, nc, babyShark.dist + 1));		//상어 위치 갱신, 한칸 이동했으므로 거리+1
						visited[nr][nc] = true;									//방문 표시
					}
				}
			}
			
			//먹이 물고기가 없음
			if(fish.size() == 0) break;
			
			//먹이물고기가 여러마리면 가장 가까운 물고기 찾아서 먹기
			Point eat = fish.get(0);
			
			for(int i=1; i<fish.size(); ++i) {
				//가장 가까이 있는 물고기 찾아서 먹기
				if(eat.dist > fish.get(i).dist) {
					eat = fish.get(i);
				}
				
				//거리가 같은 물고기가 많으면 가장 위에있는 물고기 먹기(r이 가장 작은)
				//가장작은 r위치에 있는 물고기가 많으면 가장 왼쪽부터 (c가 가장 작은)
				if(eat.dist == fish.get(i).dist) {
					if(eat.r > fish.get(i).r) {
						eat = fish.get(i);
					}
				}
			}
			
			answer += eat.dist;			//먹은 물고기 거리만큼 시간 지남(1칸이동에 1h)
			eatCnt++;					//먹은 물고기 cnt+1
			map[eat.r][eat.c] = 0;		//물고기 먹었으니 없애기
			
			//먹은 물고기 수가 아기상어 크기랑 같아지면
			if(eatCnt == babySharkState) {
				//아기상어 크기 증가, 먹은 물고기 수 초기화
				babySharkState++;
				eatCnt = 0;
			}
			
			//아기상어 좌표 이동
			sr = eat.r;
			sc = eat.c;
		}

		System.out.println(answer);
	}
}