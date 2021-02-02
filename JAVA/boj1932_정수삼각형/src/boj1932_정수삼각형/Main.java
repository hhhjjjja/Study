package boj1932_정수삼각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 다이나믹 프로그래밍 문제
 * https://www.acmicpc.net/problem/1932
 * 
 * bottom-up
 * */

public class Main {
	static int n;
	static int[][] d;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());		//삼각형의 크기(1 <= n <= 500)
		
		d = new int[n][n];
		arr = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<i+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//가장 아래 값 d[][]에 넣기
		for(int i=0; i<n; i++) {
			d[n-1][i] = arr[n-1][i];
		}
		
		//d[i][j]에 양쪽 아래값을 더한것 중 큰값 넣기
		for(int i=n-2; i>=0; i--) {
			for(int j=0; j<i+1; j++) {
				d[i][j] = Math.max(d[i+1][j]+arr[i][j], d[i+1][j+1]+arr[i][j]);
			}
		}
		
		System.out.println(d[0][0]);
	}
}