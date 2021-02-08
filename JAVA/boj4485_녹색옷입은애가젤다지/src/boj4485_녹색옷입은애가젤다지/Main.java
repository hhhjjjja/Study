package boj4485_녹색옷입은애가젤다지;
/* BFS + 다익스트라
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int r, c, rupee;
	
	public Node(int r, int c, int rupee) {
		this.r = r;
		this.c = c;
		this.rupee = rupee;
	}

	@Override
	public int compareTo(Node o) {
		return rupee - o.rupee;
	}
}

public class Main {
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[][] resRupee;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//예제 cnt
		int probCnt = 0;
		
		while(true) {
			probCnt++;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			if(n == 0) return;
			
			map = new int[n][n];
			visited = new boolean[n][n];
			resRupee = new int[n][n];
			
			for(int i=0; i<n; ++i) {
				//최대 거리값으로 초기화
				Arrays.fill(resRupee[i], n*125);
				
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<Node> q = new PriorityQueue<Node>();
			q.add(new Node(0, 0, map[0][0]));
			resRupee[0][0] = map[0][0];
			
			while(!q.isEmpty()) {
				Node node = q.poll();
				int r = node.r;
				int c = node.c;
				int rupee = node.rupee;
				
				if(visited[r][c] == true) continue;
				visited[r][c] = true;
				
				for(int i=0; i<4; ++i) {
					int nr = r + dy[i];
					int nc = c + dx[i];
					
					if(nr>=0 && nr<n && nc>=0 && nc<n) {
						if(rupee + map[nr][nc] < resRupee[nr][nc]) {
							resRupee[nr][nc] = rupee + map[nr][nc];
							q.add(new Node(nr, nc, resRupee[nr][nc]));
						}
					}
				}
			}
			
			System.out.println("Problem " + probCnt + ": " + resRupee[n-1][n-1]);
		}
	}
}