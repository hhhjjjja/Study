package boj18353_병사배치하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int arr[];
	static int d[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n];
		d = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		/************************input end*************************/
		
		//가장 긴 증가하는 부분 수열 찾기
		for(int i=1; i<n; ++i) {
			for(int j=0; j<=i; j++) {
				if(arr[i] < arr[j]) {
					d[i] = Math.max(d[i], d[j]+1);
				}
			}
		}
		Arrays.sort(d);
		
		//배열의 크기 - 최댓값
		//증가수열의 시작을 1이아닌 0으로 했으므로 -1 해줌
		System.out.println(n-d[n-1]-1);
	}
}