package boj16234_인구이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int row;
	int col;
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Main {
	static ArrayList<Point> openArr = new ArrayList<Point>();
	static Queue<Point> queue = new LinkedList<Point>();
	
	static int n, r, l;
	static int answer = 0;
	static boolean isPossible;
	static int [] dx = {0, -1, 0, 1};
	static int [] dy = {1, 0, -1, 0};
	
	static boolean [][] visited = new boolean[101][101];
	static int [][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		A = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while(true) {
			isPossible = false;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(visited[i][j]) continue;
					bfs(i, j);
				}
			}
			
			//인구이동 끝
			if(!isPossible) {
				System.out.println(answer);
				return;
			}
			answer++;
			visited = new boolean[101][101];
		}
	}
	
	public static void bfs(int a, int b) {
		//인구이동 o,x
		boolean flag = false;
		queue.add(new Point(a, b));
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int row = p.row;
			int col = p.col;
			
			for(int i=0; i<4; i++) {
				int nr = row + dy[i];
				int nc = col + dx[i];
				
				if(nr>=0 && nc>=0 && nr<n && nc<n) {
					//이미 방문한 좌표
					if(visited[nr][nc]) continue;
					
					int abs = Math.abs(A[row][col] - A[nr][nc]);
					if(abs >= l && abs <= r) {
						queue.add(new Point(nr, nc));
						openArr.add(new Point(nr, nc));
						
						visited[nr][nc] = true;
						//인구이동 필요
						isPossible = true;
						flag = true;
					}
				}
			}
		}
		
		//인구이동해야할 연합이 있음
		if(flag) {
			//도시 개수
			int len = openArr.size();
			int sum = 0;
			
			if(len == 1) {
				return;
			}
			//연합 총 인구
			for(int i=0; i<len; i++) {
				Point p = openArr.get(i);
				sum += A[p.row][p.col];
			}
			//평균값으로 도시인구 변경
			for(int i=0; i<len; i++) {
				Point p = openArr.get(i);
				A[p.row][p.col] = sum/len;
			}
			openArr.clear();
		}
	}
}
