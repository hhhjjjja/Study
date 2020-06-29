package baek_n15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int size = 101;
	static boolean map[][] = new boolean[size][size];
	static int ans;
	static int dir_x[] = { 1, 0, -1, 0 };
	static int dir_y[] = { 0, -1, 0, 1 };

	static void draw(int x, int y, int d, int g) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int index;
		map[x][y] = true;

		list.add(d);

		for (int i = 0; i < g; i++) {
			for (int j = list.size() - 1; j >= 0; j--) {
				index = (list.get(j) + 1) % 4;
				list.add(index);
			}
		}

		for (int i = 0; i < list.size(); i++) {
			x = x + dir_x[list.get(i)];
			y = y + dir_y[list.get(i)];
			map[x][y] = true;
		}
	}

	static void getResult() {
		for (int i = 0; i < size-1; i++) {
			for (int j = 0; j < size-1; j++) {
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
					ans++;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int x, y, d, g;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());

			draw(x, y, d, g);
		}
		getResult();
		System.out.println(ans);
	}
}