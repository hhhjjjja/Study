package baek_n1012;

//dfs����

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int M, N, K;
	static int map[][];
	static int visit[][];
	static int cnt;
	static int dir_x[] = { 0, -1, 0, 1 };
	static int dir_y[] = { -1, 0, 1, 0 };
	static Queue<Point> queue = new LinkedList<Point>();
	static ArrayList<Integer> list = new ArrayList<Integer>();
	
	static void bfs(int x, int y) {
		int new_x;
		int new_y;
		Point point;
		
		queue.offer(new Point(x, y));

		while (!queue.isEmpty()) {
			point = queue.poll();

			for (int j = 0; j < 4; j++) {
				new_x = point.x + dir_x[j];
				new_y = point.y + dir_y[j];

				if (new_x >= 0 && new_y >= 0 && new_x < N && new_y < M) {
					if (map[new_x][new_y] == 1 && visit[new_x][new_y] == 0) {
						visit[new_x][new_y] = 1;
						queue.offer(new Point(new_x, new_y));
					}
				}
			}
		}
		cnt++;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		int x, y;

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  //�� ũ��
			M = Integer.parseInt(st.nextToken());  //�� ũ��
			K = Integer.parseInt(st.nextToken());  // ���� ����

			map = new int[N][M];
			visit =new int[N][M];
			
			for (int j = 0; j < K; j++) {
				StringTokenizer st1 = new StringTokenizer(br.readLine());
				
				x = Integer.parseInt(st1.nextToken());
				y = Integer.parseInt(st1.nextToken());
				map[x][y] = 1;
			}

			//n ���α��� x��, m ���� y��
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (map[n][m] == 1 && visit[n][m] == 0) {
						visit[n][m]= 1 ;
						bfs(n, m);
					}
				}
			}
			list.add(cnt);
			
			//T>=2 �̸� cnt=0�ʱ�ȭ
			cnt = 0;
		}
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}