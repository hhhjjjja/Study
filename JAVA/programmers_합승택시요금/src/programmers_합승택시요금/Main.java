package programmers_합승택시요금;

import java.util.Arrays;

public class Main {
	static int[][] d;
	public static void main(String[] args) {
		//TEST CASE 01 -> result : 82
		int n = 6;
		int s = 4; 
		int a = 6;
		int b = 2;
		int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		
		//TEST CASE 02 -> result : 14
//		int n = 7;
//		int s = 3; 
//		int a = 4;
//		int b = 1;
//		int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
		
		//TEST CASE 03 -> result : 18
//		int n = 6;
//		int s = 4; 
//		int a = 5;
//		int b = 6;
//		int[][] fares = {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}};
		
		System.out.print(solution(n, s, a, b, fares));
	}
	
	static int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = 20000000;			//100000*200
		
		d = new int[n][n];
		for(int t[]:d) {
			Arrays.fill(t, 20000000);
		}
		
		for(int i=0; i<fares.length; ++i) {
			int start = fares[i][0]-1;
			int end = fares[i][1]-1;
			
			//양방향 요금 똑같음
			d[start][end] = fares[i][2];
			d[end][start] = fares[i][2];
		}
		
		//i에서 j까지의 최소거리 d[][]
		for(int k=0; k<n; ++k) {
			for(int i=0; i<n; ++i) {
				for(int j=0; j<n; ++j) {
					if(i==j) {
						d[i][j] = 0;
					} else {
						d[i][j] = Math.min(d[i][j], d[i][k]+d[k][j]);
					}
				}
			}
		}
		
		for(int i=0; i<n; ++i) {
			answer = Math.min(answer, d[s-1][i] + d[i][a-1] + d[i][b-1]);
		}
		
		return answer;
	}
}