package baek_n17141;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static int N, M;
	static int temp[][];
	static int dir_x[] = { 0, -1, 0, 1 };
	static int dir_y[] = { -1, 0, 1, 0 };
	
	static ArrayList<Point> virus_pos = new ArrayList<Point>();
	static ArrayList<Point> wall_pos = new ArrayList<Point>();
	static Queue<Point> queue = new LinkedList<Point>();
	
	static int result;
	static Point pos[];
	static int min = Integer.MAX_VALUE;
	static int wall_cnt = 0;
	
	static void combination(int idx, int depth) {
		if (depth == M) {
			virusBFS();
			return ;
		}

		for (int i = idx; i < virus_pos.size(); i++) {
			pos[depth] = new Point(virus_pos.get(i).x, virus_pos.get(i).y);
			combination(i + 1, depth + 1);
		}
	}

	static void virusBFS() {
		int nx, ny;
		int sec = 0;
		boolean visit[][] = new boolean[N][N];

		for (int i = 0; i < temp.length; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, N);
		}

		for (int i = 0; i < pos.length; i++) {
			visit[pos[i].x][pos[i].y] = true;
			queue.offer(pos[i]);
		}

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			sec = temp[p.x][p.y];
			sec++;

			for (int i = 0; i < 4; i++) {
				nx = p.x + dir_x[i];
				ny = p.y + dir_y[i];

				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
					if (map[nx][ny] != -1 && !visit[nx][ny]) {
						visit[nx][ny] = true;
						temp[nx][ny] = sec;
						queue.offer(new Point(nx, ny));
					}
				}
			}
		}

		if (check(visit)) {
			min = Math.min(sec - 1, min);
		}
	}

	static boolean check(boolean visit[][]) {
		int wall = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] == false) {
					wall++;
				}
			}
		}

		if (wall == wall_cnt) return true;
		else return false;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		temp = new int[N][N];
		pos = new Point[M];

		for (int i = 0; i < N; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st1.nextToken());
				if (map[i][j] == 2) {
					virus_pos.add(new Point(i, j));
					map[i][j] = 0;
				} else if (map[i][j] == 1) {
					wall_pos.add(new Point(i, j));
					map[i][j] = -1;
					wall_cnt++;
				}
			}
		}
		combination(0, 0);
		min = (min == Integer.MAX_VALUE ? -1 : min);
		
		System.out.println(min);
	}
}