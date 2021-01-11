package boj3190_��;

import java.util.ArrayList;
import java.util.Scanner;

class Snake{
	int x;
	int y;
}

public class Main {

	public static void main(String[] args) {
		Snake snake;
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int k = s.nextInt();
		
		int[][] board = new int[n][n];
		ArrayList<Snake> sArr = new ArrayList<>();
		
		//��, ��, �Ʒ�, ��
		//D:�ð����ȸ��(�ݺ�) - (i+1)%4
		//L:�ݽð� - (i+3)%4
		int[] dx = {-1, 0, +1, 0};
		int[] dy = {0, +1, 0, -1};
		
		// �����ǥ(1)
		for(int i=0; i<k; i++) {
			int a = s.nextInt();
			int b = s.nextInt();
			
			board[a-1][b-1] = 1;
		}
		
		//������ȯ Ƚ��[0], ����[1]
		int l = s.nextInt();
		String[][] dir = new String[l][2];
		for(int i=0; i<l; i++) {
			int x = s.nextInt();
			String c = s.next();
			
			dir[i][0] = Integer.toString(x);
			dir[i][1] = c;
		}
		
		//====================== ��� =======================
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				System.out.print(board[i][j]+" ");
//			}
//			System.out.println("");
//		}
		//==================================================
		
		int time = 0;
		int x = 0;	//�Ӹ� x
		int y = 0;	//�Ӹ� y
		
		int slen = 1;

		int i = 0;	//������ȯ arr idx
		int di = 1;	//������� idx
		
		while(true) {
			time++;
			board[x][y] = -1;
			
			snake = new Snake();
			snake.x = x;
			snake.y = y;
			sArr.add(snake);

			x += dx[di];
			y += dy[di];
			
//			System.out.println("time: " + time + " �Ӹ���ǥ: " + x + ", " + y);
			
			//���ε���
			if(x >(n-1) || y >(n-1) || x<0 || y<0) {
				System.out.println(time);
				break;
			}
			
			//�ڱ���̶� �ε���
			if(board[x][y] == -1) {
				System.out.println(time);
				break;
			}
			
//			for(int r=0; r<n; r++) {
//				for(int j=0; j<n; j++) {
//					System.out.print(board[r][j]+" ");
//				}
//				System.out.println("");
//			}
			
			//�������(��������, ��ũ�� +1)
			//�ƴϸ� �����ȳ�
			//������� ������ŭ ������ǥ�� -1���(?)
			if(board[x][y] == 1) {
				slen++;
			} else {
//				System.out.println("time-slen : " + (time-slen));
//				System.out.println(sArr.get(time-slen).x +", " + sArr.get(time-slen).y);
				
				board[sArr.get(time-slen).x][sArr.get(time-slen).y] = 0;
			}
			
			//������ȯ
			if(dir[i][0].equals(Integer.toString(time))) {
//				System.out.println(dir[i][0] + "�� �� " + dir[i][1]);
				//�ð�
				if(dir[i][1].equals("D")) {
					di = (di+1)%4;
					
				//�ݽð�
				} else if(dir[i][1].equals("L")) {
					di = (di+3)%4;
				}
				if(i < dir.length-1) {
					i++;
				}
			}
		}
		
//		for(int r=0; r<n; r++) {
//			for(int j=0; j<n; j++) {
//				System.out.print(board[r][j]+" ");
//			}
//			System.out.println("");
//		}
	}

}
