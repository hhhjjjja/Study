package boj14501_퇴사;

/* 완전탐색
 * (DP문제지만 통과됨..!)
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, max=0;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][2];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		sol(0, 0);
		System.out.println(max);
	}
	
	static void sol(int idx, int money) {
		if(idx >= n)  {
			max = Math.max(max, money);
			return ;
		}
		
		//n이후에는 회사에 없기때문에 idx+걸리는 일수가 n을 넘으면 안됨
		if(idx+arr[idx][0] <= n) {
			sol(idx+arr[idx][0], money+arr[idx][1]);
		}
		
		sol(idx+1, money);
	}
}