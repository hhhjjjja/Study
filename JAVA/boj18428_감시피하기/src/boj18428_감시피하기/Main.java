package boj18428_감시피하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Point {
	int row;
	int col;
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Main {
	static String [][] map;
	static String [][] tmap;
	static int n;
	static int res = 0;
	
	static ArrayList<Point> sArr = new ArrayList<Point>();
	static ArrayList<Point> tArr = new ArrayList<Point>();
	
	//DFS use
	static Stack<Point> stack = new Stack<Point>();
	static boolean [] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		map = new String[n][n];
		visited = new boolean[n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = st.nextToken();
				if(map[i][j].equals("T")) tArr.add(new Point(i, j));
				else if(map[i][j].equals("S")) sArr.add(new Point(i, j));
			}
		}
		
		int oCnt = 3;
		makeO(oCnt);
		
		if(res == 0) System.out.println("NO");
		else System.out.println("YES");
	}
	
	public static void makeO(int oCnt) {
		//장애물 설치 끝나면 선생님 감시 Go
		if(oCnt == 0) {
			res += watchT();
			return ;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				//빈칸이면 장애물
				if(map[i][j].equals("X")) {
					map[i][j] = "O";
					makeO(oCnt-1);
					map[i][j] = "X";
				}
			}
		}
	}
	
	public static int watchT() {
		int [] dx = {0, -1, 0, 1};
		int [] dy = {1, 0, -1, 0};
		
		for(int i=0; i<tArr.size(); i++) {
			for(int j=0; j<4; j++) {
				int nr = tArr.get(i).row;
				int nc = tArr.get(i).col;
				
				while(true) {
					nr += dx[j];
					nc += dy[j];
					if (nc <0 || nc > n-1 || nr < 0 || nr > n-1 || map[nr][nc].equals("O")) break;
					//걸림
					if (map[nr][nc].equals("S")) return 0;
				}
			}
		}
		//안걸림
		return 1;
	}
}