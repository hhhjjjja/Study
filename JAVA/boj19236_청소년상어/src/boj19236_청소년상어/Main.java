package boj19236_û�ҳ���;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
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
	
	static int[][] map = new int[4][4];
	static Info[] fishes = new Info[17];
	
	//[0]�� ���x, 1���� ��, ��, ��, ��, ��, ��, ��, ��
	static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	
	static int answer = 0;
	static int sr, sc, sd;			//��� ��ǥ r, c, ������ sd
	
	public static void main(String[] args) throws IOException {
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
		
		sr = sc = 0;		//��� ��ġ (0, 0)
		sd = fishes[map[0][0]].direct;		//��� ���� (0,0)�� �ִ� ������� ����
		
		answer += map[0][0];		//(0, 0) ����� �Ծ����ϱ� ����� ��ȣ+
		fishes[map[0][0]] = null;	//�Ծ����� �����
		map[0][0] = -1;				//�����ġ state -1
		
		dfs();
		
		System.out.println(answer);
	}
	
	static void dfs() {
		//����� �̵� 1������ 16������
		for(int i=1; i<=16; ++i) {
			Info fish = fishes[i];		//���� �����
			
			if(fish == null) continue;
			
			int nd = fish.direct;
			//�̵��� �� �ִ� ���� ã��
			while(true) {
				boolean flag = false;
				
				int nr = fish.r + dr[fish.direct];
				int nc = fish.c + dc[fish.direct];
				
				//�ʿ��� ����ų� �� ������ �ݽð� 45�� ȸ��
				if(nr<0 || nr>=4 || nc<0 || nc>=4 || map[nr][nc] == -1) {
					fish.direct = (fish.direct + 1) % 8;
					flag = true;
					continue;
				}
				
				Info t = fishes[map[nr][nc]];
				//����� ����ĭ���� �̵��ϸ� ���� ����� ������ ����
				if(t == null) {
					fishes[i] = new Info(nr, nc, fish.direct);
				} else {
					//��ĭ�� �ƴϸ� ����� ���� ���� ��ȯ
					fishes[map[nr][nc]] = new Info(fish.r, fish.c, t.direct);	//�̵��� ��ǥ�� ���ݹ���� ������ ����
					fishes[i] = new Info(nr, nc, fish.direct);		//���ݹ���� �ִ� ĭ���� �̵�����ǥ�� �ִ� ����� ���� ����
				}
				
				int mtmp = map[nr][nc];
				map[nr][nc] = i;
				map[fish.r][fish.c] = mtmp;
				
				//���ư� ���� �ְ� �����ߴ� ����(nd) == ������ ����(fish.direct)�̸� �̵��Ҽ��ִ� ĭ�� ����
				if(flag && nd == fish.direct) break;
			}
		}
		
		//��� �̵�
		for(int i=1; i<4; ++i) {
			int nr = sr + dr[sd]*i;
			int nc = sc + dr[sd]*i;
			
			if(nr<0 || nr>=4 || nc<0 || nc>=3) break;
			if(map[nr][nc] == 0) continue;
		}
	}
}