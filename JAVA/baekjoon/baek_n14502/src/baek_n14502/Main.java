package baek_n14502;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//������
//������ ũ�� N*M, ��ĭ�� ������ �̷���� ������ ���� ĭ �ϳ��� ���� ����
//�Ϻ� ĭ�� ���̷����� ����, ���̷����� �����¿� ������ ��ĭ���� �������� �� �ִ�.
//���� ���� �� �ִ� ���� 3��, �� 3���� ������ ��.
//�������� ������ �־����� �� ���� �� �ִ� ���������� ũ���� �ִ� ���ϱ�

//ù°���� ����ũ��N, ����ũ��M
//0:��ĭ, 1:��, 2:���̷���

public class Main {

	static int N, M;
	static int map[][];
	static int temp[][];
	static int max = 0;
	
	static int dir_x[] = { 0, -1, 0, 1 };
	static int dir_y[] = { -1, 0, 1, 0 };
	
	static ArrayList<Point> virus_pos = new ArrayList<Point>();
	
	//�� �����
	static void dfsBF(int idx, int depth) {  //��ü Ž�� , ���Ʈ ����
		if (depth == 3) {
			copyArray(); // ���� map �Ѽ��� ���� ���� temp�迭�� �̿�

			for (Point pos : virus_pos) {
				virusDFS(pos.x, pos.y);
			}
			max = Math.max(max, getSafeArea()); // �� 3�� ����� ���̷����� ��Ʈ�� �� ������������ return
			return;
		}

		for (int i = idx; i < N * M; i++) {  //N �� ũ��, M �� ũ��
			int x = i / M; 
			int y = i % M;

			if (map[x][y] == 0) {
				map[x][y] = 1;
				idx++;
				dfsBF(idx, depth + 1);
				map[x][y] = 0;
			}
		}
	}

	static void virusDFS(int x, int y) {
		int new_x;
		int new_y;

		for (int i = 0; i < 4; i++) {
			new_x = x + dir_x[i];
			new_y = y + dir_y[i];

			if (new_x >= 0 && new_y >= 0 && new_x < N && new_y < M) {
				if (temp[new_x][new_y] == 0) {
					temp[new_x][new_y] = 2;
					virusDFS(new_x, new_y);
				}
			}
		}
	}

	static void copyArray() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}

	static int getSafeArea() {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //�� ũ��
		M = Integer.parseInt(st.nextToken()); //�� ũ��
		
		map = new int[N][M];
		temp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virus_pos.add(new Point(i, j));  // ���̷��� ��ġ�� ����
			}
		}
		dfsBF(0, 0); // ���� ���� -> �� ����� ������ ������� ����� �����Ƿ�
					// �ߺ� X -> �̹� ���� �� ��ġ�� �� ���� �� ����
		System.out.println(max);
	}
}