package baek_n14502;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//연구소
//연구소 크기 N*M, 빈칸과 벽으로 이루어져 있으며 벽은 칸 하나를 가득 차지
//일부 칸은 바이러스가 존재, 바이러스는 상하좌우 인접한 빈칸으로 퍼져나갈 수 있다.
//새로 세울 수 있는 벽은 3개, 꼭 3개를 세워야 함.
//연구소의 지도가 주어졌을 때 얻을 수 있는 안전영역의 크기의 최댓값 구하기

//첫째줄은 세로크기N, 가로크기M
//0:빈칸, 1:벽, 2:바이러스

public class Main {

	static int N, M;
	static int map[][];
	static int temp[][];
	static int max = 0;
	
	static int dir_x[] = { 0, -1, 0, 1 };
	static int dir_y[] = { -1, 0, 1, 0 };
	
	static ArrayList<Point> virus_pos = new ArrayList<Point>();
	
	//벽 세우기
	static void dfsBF(int idx, int depth) {  //전체 탐색 , 브루트 포스
		if (depth == 3) {
			copyArray(); // 기존 map 훼손을 막기 위해 temp배열을 이용

			for (Point pos : virus_pos) {
				virusDFS(pos.x, pos.y);
			}
			max = Math.max(max, getSafeArea()); // 벽 3개 세우고 바이러스를 퍼트린 후 안전지역갯수 return
			return;
		}

		for (int i = idx; i < N * M; i++) {  //N 행 크기, M 열 크기
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

		N = Integer.parseInt(st.nextToken()); //행 크기
		M = Integer.parseInt(st.nextToken()); //열 크기
		
		map = new int[N][M];
		temp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virus_pos.add(new Point(i, j));  // 바이러스 위치를 저장
			}
		}
		dfsBF(0, 0); // 조합 문제 -> 벽 세우는 순서에 상관없이 결과는 같으므로
					// 중복 X -> 이미 세운 벽 위치에 또 벽을 못 지음
		System.out.println(max);
	}
}