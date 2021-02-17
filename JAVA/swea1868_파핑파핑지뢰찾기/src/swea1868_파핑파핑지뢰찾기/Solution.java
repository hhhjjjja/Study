package swea1868_������������ã��;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int T, N;
	static char [][] map;
	static int [][] landMine;
	static boolean[][] visited;
	
	//�������� �ð���� 
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		
		for(int t=1; t<=T; ++t) {
			answer = 0;
			N = Integer.parseInt(br.readLine().trim());
			
			map = new char [N][N];				//����ã�� input��
			landMine = new int[N][N];			//�ֺ��� �ִ� ���� ����
			visited = new boolean[N][N];		//�湮Ȯ��
			
			for(int i=0; i<N; ++i) {
				map[i] = br.readLine().toCharArray();
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//����ĭ ã���� �ֺ� ĭ�� ���� ���� +1
					if(map[i][j] == '*') {
						for(int d=0; d<8; ++d) {
							int nr = i + dr[d];
							int nc = j + dc[d];
							
							//nr,nc��ǥ�� �� ���� ����
							if(nr>=0 && nr<N && nc>=0 && nc<N) {
								landMine[nr][nc]++;
							}
							//���ڰ� �ִ� ĭ�� �湮�ߴٰ� ħ
							visited[i][j] = true;
						}
					}
				}
			}
			
			//�ֺ��� �ִ� ���ڰ����� map�� �ٲٱ�
			check();
			
			System.out.println("#" + t + " " + answer);
		}
	}
	
	static void check() {
		//0���� ã�Ƽ� ó��
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(landMine[i][j] == 0 && !visited[i][j]) {
					answer++;	//Ŭ���� �ѹ� �ø���
					visited[i][j] = true;
					
					//�ֺ��� �ִ� 0���� �ѹ�Ŭ������ �� ���� ���ؼ� bfs���
					Queue<Point> bfs  = new LinkedList<>();
					bfs.offer(new Point(i, j));
					
					while(!bfs.isEmpty()) {
						Point p = bfs.poll();
						
						for(int d=0; d<8; ++d) {
							int nr = p.r + dr[d];
							int nc = p.c + dc[d];
							
							//Ž���ϴ� ��ǥ�� �� ���� ���̰�, ���ھƴϰ� �湮������ ������
							if(nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc] != '*' && !visited[nr][nc]) {
								visited[nr][nc] = true;
								
								if(landMine[nr][nc] == 0) {
									bfs.offer(new Point(nr, nc));
								}
							}
						}
					}
				}
			}
		}
		
		//0�̿� ������ ĭ�� ����
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != '*' && !visited[i][j]) {
					visited[i][j] = true;
					answer++;
				}
			}
		}
		
	}
}