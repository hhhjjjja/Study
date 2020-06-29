package baek_n2146;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static int dir_x[] = { 0, -1, 0, 1 };
	static int dir_y[] = { -1, 0, 1, 0 };
	static int N;
	static int visit[][];

	static Queue<Point> queue = new LinkedList<Point>();

	static int min = Integer.MAX_VALUE;

	static void islandDFS(int x, int y, int islandNum) {
		int new_x, new_y;

		for (int i = 0; i < 4; i++) {
			new_x = x + dir_x[i];
			new_y = y + dir_y[i];

			if (new_x >= 0 && new_y >= 0 && new_x < N && new_y < N) {
				if (map[new_x][new_y] == 1 && visit[new_x][new_y] == 0) {
					visit[new_x][new_y] = 1;
					map[new_x][new_y] = islandNum;
					islandDFS(new_x, new_y, islandNum);
				}
			}
		}
	}

	static void bfs(int x, int y) {
		int new_x, new_y;
		Point point;
		queue.offer(new Point(x, y));
		int bridgeNum = map[x][y];
		int cnt;
		
		while (!queue.isEmpty()) {
			point = queue.poll();
			cnt = visit[point.x][point.y];
			
			cnt++;
			
			for (int i = 0; i < 4; i++) {
				new_x = point.x + dir_x[i];
				new_y = point.y + dir_y[i];

				if (new_x >= 0 && new_y >= 0 && new_x < N && new_y < N) {
					if (map[new_x][new_y] == 0 && visit[new_x][new_y] == 0) {
						visit[new_x][new_y] = cnt;
						queue.offer(new Point(new_x, new_y));
					} else if (map[x][y] != map[new_x][new_y] && map[new_x][new_y] != 0) {
						min = Math.min(cnt-1, min);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visit = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int bridgeNum = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && visit[i][j] == 0) {
					bridgeNum++;
					
					map[i][j] = bridgeNum;
					visit[i][j] = 1;
					islandDFS(i, j, bridgeNum);
				}
			}
		}

		for (int i = 0; i < visit.length; i++) {
			Arrays.fill(visit[i], 0);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					bfs(i, j);
					for (int k = 0; k < visit.length; k++) {
						Arrays.fill(visit[k], 0);
					}
				}
			}
		}
		System.out.println(min);
	}
}