package boj18310_안테나;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] hpoint;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//집의 수 n
		n = Integer.parseInt(st.nextToken());
		hpoint = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			hpoint[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(hpoint);
		System.out.println(hpoint[n/2-1]);
	}
}