package swea5653_줄기세포배양;
/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRJ8EKe48DFAUo
 * 세포 번식 -> BFS
 * 우선순위 큐를 이용해서 vitality 기준
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Cell implements Comparable<Cell> {
		int row;			//좌표 Y
		int col;			//X
		int vital;			//생명력
		int start;			//활성화 시작 시간
		
		public Cell(int row, int col, int vital, int start) {
			this.row = row;
			this.col = col;
			this.vital = vital;
			this.start = start;
		}
		
		//vital이 큰 순서부터 오름차순 정렬
		@Override
		public int compareTo(Cell o) {
			return -(this.vital - o.vital);
		}
	}
	
	static int T, N, M, K;
	static boolean[][] visited;
	
	static Queue<Cell> queue = new LinkedList<>();				//활성, 비활성상태 세포들 있는 큐
	static PriorityQueue<Cell> pq = new PriorityQueue<>();		//활성화. 번식하는 세포들 큐
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; ++t) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			//초기화 필수. 이거 안해주면 뒤 테케들 답 이상하게 나옴...
			visited = new boolean[1000][1000];		//방문배열 초기화
			queue.clear(); 			//큐 초기화
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; ++j) {
					//이건... 왜 400더하는지 모르겠음...
					//맵의 중간으로 보낼려고..?
					int r = i + 400;
					int c = j + 400;
					
					int vitality = Integer.parseInt(st.nextToken());
					
					//0인 경우 줄기세포가 존재하지 않는 그리드이므로 큐에 넣지않아도 됨.
					if(vitality != 0) {
						visited[r][c] = true;
						// y, x, 생명력, 활성시작시간
						queue.add(new Cell(r, c, vitality, vitality));
					}
				}
			}
			
			//K시간까지 time 돌리기
			for(int time=1; time<=K; ++time) {
				check(time);
				bfs(time);
			}
			
			System.out.println("#" + t + " " + queue.size());
		}
	}
	
	//세포 번식
	static void bfs(int time) {
		while(!pq.isEmpty()) {
			Cell c = pq.poll();
			
			//번식 끝(활성시작~생명력) 시간보다 작으면 아직 안죽은상태
			if(time < c.start+c.vital) {
				queue.add(c);
			}
			
			//상하좌우 번식
			for(int i=0; i<4; ++i) {
				int nr = c.row + dx[i];
				int nc = c.col + dy[i];
				
				if(!visited[nr][nc]) {
					visited[nr][nc] = true;
					// y, x, 생명력, 활성시작시간(지금시간+생명력)
					queue.add(new Cell(nr, nc, c.vital, time + c.vital));
				}
			}
		}
	}
	
	static void check(int time) {
		//아래 반복문에서 큐의 크기가 계속 변하므로 len으로 고정필요
		int len = queue.size();
		
		for(int i=0; i<len; ++i) {
			Cell c = queue.poll();
			
			//활성화 전
			if(time <= c.start) {
				queue.add(c);			//되돌려놓기
			} else if(time == c.start+1) {
				pq.add(c);				//활성화
			} else if(time > c.start && time < c.start+c.vital) {
				queue.add(c);			//죽기 전
			}
		}
	}
}