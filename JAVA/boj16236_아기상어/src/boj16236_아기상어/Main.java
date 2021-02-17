package boj16236_�Ʊ���;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r, c, dist;
		
		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	static int n;
	static int[][] map;
	static int answer, eatCnt;
	static int sr, sc;
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
			
	static ArrayList<Point> fish;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		int babySharkState = 2;
		
		for(int i=0; i<n; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; ++j) {
				//0 : ��ĭ
				//1,2,3,4,5,6 : ĭ�� �ִ� ������� ũ��
				//9 : �Ʊ����� ��ġ
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 9) {
					//�Ʊ����� ��ġ (i,j)
					sr = i;
					sc = j;
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			Queue<Point> bfs = new LinkedList<>();			//bfs
			
			fish = new ArrayList<>();			//���� ����� ����Ʈ
			boolean visited[][] = new boolean[n][n];
			
			bfs.add(new Point(sr, sc, 0));
			visited[sr][sc] = true;
			
			//BFS
			while(!bfs.isEmpty()) {
				Point babyShark = bfs.poll();
				
				//�����¿� Ž��
				for(int i=0; i<4; ++i) {
					int nr = babyShark.r + dy[i];
					int nc = babyShark.c + dx[i];
					
					if(nr<0 || nr>=n || nc<0 || nc>=n) continue;			//��ǥ ���� ����� �ٸ���ǥ Ž��
					if(visited[nr][nc]) continue;							//�湮�� ��ǥ�� ��������
					
					//���̸� ã����(���̴� 1~6����)
					//�Ʊ��� ũ�⺸�� ������ �������ִ� �����
					if (1 <= map[nr][nc] && map[nr][nc] <= 6 && map[nr][nc] < babySharkState) {
						bfs.offer(new Point(nr, nc, babyShark.dist + 1));		//����� ��ġ ����, ��ĭ �̵������Ƿ� �Ÿ�+1
						fish.add(new Point(nr, nc, babyShark.dist + 1));		//���� ����Ʈ�� ����
						visited[nr][nc] = true; //�湮 ǥ��
					}
					
					else if(map[nr][nc] == babySharkState || map[nr][nc] == 0) {
						bfs.offer(new Point(nr, nc, babyShark.dist + 1));		//��� ��ġ ����, ��ĭ �̵������Ƿ� �Ÿ�+1
						visited[nr][nc] = true;									//�湮 ǥ��
					}
				}
			}
			
			//���� ����Ⱑ ����
			if(fish.size() == 0) break;
			
			//���̹���Ⱑ ���������� ���� ����� ����� ã�Ƽ� �Ա�
			Point eat = fish.get(0);
			
			for(int i=1; i<fish.size(); ++i) {
				//���� ������ �ִ� ����� ã�Ƽ� �Ա�
				if(eat.dist > fish.get(i).dist) {
					eat = fish.get(i);
				}
				
				//�Ÿ��� ���� ����Ⱑ ������ ���� �����ִ� ����� �Ա�(r�� ���� ����)
				//�������� r��ġ�� �ִ� ����Ⱑ ������ ���� ���ʺ��� (c�� ���� ����)
				if(eat.dist == fish.get(i).dist) {
					if(eat.r > fish.get(i).r) {
						eat = fish.get(i);
					}
				}
			}
			
			answer += eat.dist;			//���� ����� �Ÿ���ŭ �ð� ����(1ĭ�̵��� 1h)
			eatCnt++;					//���� ����� cnt+1
			map[eat.r][eat.c] = 0;		//����� �Ծ����� ���ֱ�
			
			//���� ����� ���� �Ʊ��� ũ��� ��������
			if(eatCnt == babySharkState) {
				//�Ʊ��� ũ�� ����, ���� ����� �� �ʱ�ȭ
				babySharkState++;
				eatCnt = 0;
			}
			
			//�Ʊ��� ��ǥ �̵�
			sr = eat.r;
			sc = eat.c;
		}

		System.out.println(answer);
	}
}