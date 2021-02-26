package boj19236_û�ҳ���;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Info {
		int r, c, direct;
		
		public Info(int r, int c, int direct) {
			this.r = r;
			this.c = c;
			this.direct = direct;
		}
	}
	
	//1���� ��, ��, ��, ��, ��, ��, ��, ��
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		int[][] map = new int[4][4];
		Info[] fishes = new Info[17];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<4; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<4; ++j) {
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				fishes[s] = new Info(i, j, d);		//s�� ������� ���� d
				map[i][j] = s;			//i, j�� s�� ����� ����
			}
		}
		
		int answer = map[0][0];		//(0, 0) ����� �Ծ����ϱ� ����� ��ȣ+
		Info shark = new Info(0, 0, fishes[map[0][0]].direct);		//��� ������ġ�� 0,0
		
		fishes[0] = new Info(0, 0, -1);		//nullĭ ä���ֱ�
		
		fishes[map[0][0]] = new Info(0, 0, -1);	//�Ծ����� �����
		map[0][0] = -1;				//�����ġ state -1
		
		dfs(map, fishes, answer, shark);
		
		System.out.println(max);
	}
	
	static void dfs(int[][] map, Info[] fishes, int answer, Info shark) {
		//���� ������ �������ؼ� ������ ī��
		int[][] map2 = new int [4][4];
		Info[] fishes2 = new Info[17];
		
		for(int i=0; i<4; ++i) {
			System.arraycopy(map[i], 0, map2[i], 0, 4);
		}
		
		for(int i=1; i<17; ++i) {
			fishes2[i] = new Info(fishes[i].r, fishes[i].c, fishes[i].direct);
		}
		
		//����� �̵� 1������ 16������
		for(int i=1; i<=16; ++i) {
			if(fishes2[i].direct == -1) continue;
			
			Info fish = fishes2[i];		//���� �����
			
			int nd = fish.direct;
			//�̵��� �� �ִ� ���� ã��
			for(int d=0; d<8; ++d) {
				nd %= 8;
				
				int nr = fish.r + dr[nd];
				int nc = fish.c + dc[nd];
				
				//�ʿ��� ����ų� �� ������ �ݽð� 45�� ȸ��
				if(nr<0 || nr>=4 || nc<0 || nc>=4 || map[nr][nc] == -1) {
					nd++;
					continue;
				}
		
				if(map2[nr][nc] == 0) {
					map2[fish.r][fish.c] = 0;
					map2[nr][nc] = i;
					fishes2[i] = new Info(nr, nc, nd);
					break;
				} else if(map2[nr][nc] > 0 ) {
					map2[fish.r][fish.c] = map2[nr][nc];
					
					//��ĭ�� �ƴϸ� ����� ���� ���� ��ȯ
					fishes2[map2[nr][nc]] = new Info(fish.r, fish.c, fishes2[map2[nr][nc]].direct);	//�̵��� ��ǥ�� ���ݹ���� ������ ����
					map2[nr][nc] = i;
					fishes2[i] = new Info(nr, nc, nd);		//���ݹ���� �ִ� ĭ���� �̵�����ǥ�� �ִ� ����� ���� ����
					break;
				}
			}
		}
		
		boolean ff = false;
		
		//��� �̵�
		for(int i=1; i<=4; ++i) {
			
			int nr = shark.r + dr[shark.direct]*i;
			int nc = shark.c + dc[shark.direct]*i;
			
			//�ʿ��� ����� break
			if(nr<0 || nr>=4 || nc<0 || nc>=4) break;
			
			int tmp = map2[nr][nc];
			
			//����� �ִ� ĭ�̸�
			if(tmp > 0) {
				ff = true;
				
				map2[shark.r][shark.c] = 0;
				
				//��� �̵�, ��������
				Info s = new Info(nr, nc, fishes2[tmp].direct);
				//(nr, nc)��ǥ�� �ִ� ����� �Ծ����ϱ� ����� �����
				fishes2[tmp] = new Info(0, 0, -1);
				map2[nr][nc] = -1;
				
				dfs(map2, fishes2, answer+tmp, s);
				
				map2[nr][nc] = tmp;		//�� �ǵ�������
				fishes[tmp] = new Info(nr, nc, s.direct);
				map2[shark.r][shark.c] = -1;
			}
		}
		
		if(!ff) max = Math.max(max, answer);
	}
}