package boj14501_퇴사;

/* 다이나믹 프로그래밍으로 풀기
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	static int n, max=0;
	static int[][] arr;
	static int[] d;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][2];
		d = new int[n+1];
		
		for(int i=0; i<n; ++i) {
			st = new StringTokenizer(br.readLine());
			
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=n-1; i>=0; --i) {
			//인덱스에서 걸리는시간이 n보다 작아야만 +
			if(i+arr[i][0] <= n) {
				d[i] = Math.max(arr[i][1]+d[i+arr[i][0]], max);
				max = d[i];
			} else {
				d[i] = max;
			}
		}
		
		System.out.println(d[0]);
	}
}