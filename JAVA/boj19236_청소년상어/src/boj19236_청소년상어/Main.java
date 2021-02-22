package boj19236_청소년상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r, c, direct;
		
		public Point(int r, int c, int direct) {
			this.r = r;
			this.c = c;
			this.direct = direct;
		}
	}
	
	static int[][] map = new int[4][8];
	static PriorityQueue<Point> pq = new PriorityQueue<>();
	
	static int answer = 0;
	static int sr=0, sc=0;			//상어 좌표 r, c
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<4; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<4; ++j) {
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
//				pq.add(new Point(i, j, s, d));
				map[i][j] = s;
			}
		}
		
		//BFS로 풀면 될듯
		while(true) {
			int len = pq.size();
			for(int i=0; i<len; ++i) {
				Point fish = pq.poll();
				
				if(fish.direct == 1) {		//위
					if(fish.r == 0) continue;
					
					if(fish.r-1 != sr && fish.c != sc) {
						
					}
				}
			}
		}
	}
}




