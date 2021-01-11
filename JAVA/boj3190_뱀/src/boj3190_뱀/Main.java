package boj3190_뱀;

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
		
		//위, 오, 아래, 왼
		//D:시계방향회전(반복) - (i+1)%4
		//L:반시계 - (i+3)%4
		int[] dx = {-1, 0, +1, 0};
		int[] dy = {0, +1, 0, -1};
		
		// 사과좌표(1)
		for(int i=0; i<k; i++) {
			int a = s.nextInt();
			int b = s.nextInt();
			
			board[a-1][b-1] = 1;
		}
		
		//방향전환 횟수[0], 방향[1]
		int l = s.nextInt();
		String[][] dir = new String[l][2];
		for(int i=0; i<l; i++) {
			int x = s.nextInt();
			String c = s.next();
			
			dir[i][0] = Integer.toString(x);
			dir[i][1] = c;
		}
		
		//====================== 출력 =======================
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				System.out.print(board[i][j]+" ");
//			}
//			System.out.println("");
//		}
		//==================================================
		
		int time = 0;
		int x = 0;	//머리 x
		int y = 0;	//머리 y
		
		int slen = 1;

		int i = 0;	//방향전환 arr idx
		int di = 1;	//진행방향 idx
		
		while(true) {
			time++;
			board[x][y] = -1;
			
			snake = new Snake();
			snake.x = x;
			snake.y = y;
			sArr.add(snake);

			x += dx[di];
			y += dy[di];
			
//			System.out.println("time: " + time + " 머리좌표: " + x + ", " + y);
			
			//벽부딛힘
			if(x >(n-1) || y >(n-1) || x<0 || y<0) {
				System.out.println(time);
				break;
			}
			
			//자기몸이랑 부딛힘
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
			
			//사과먹음(꼬리유지, 뱀크기 +1)
			//아니면 꼬리안녕
			//먹은사과 갯수만큼 이전좌표에 -1찍기(?)
			if(board[x][y] == 1) {
				slen++;
			} else {
//				System.out.println("time-slen : " + (time-slen));
//				System.out.println(sArr.get(time-slen).x +", " + sArr.get(time-slen).y);
				
				board[sArr.get(time-slen).x][sArr.get(time-slen).y] = 0;
			}
			
			//방향전환
			if(dir[i][0].equals(Integer.toString(time))) {
//				System.out.println(dir[i][0] + "초 끝 " + dir[i][1]);
				//시계
				if(dir[i][1].equals("D")) {
					di = (di+1)%4;
					
				//반시계
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
