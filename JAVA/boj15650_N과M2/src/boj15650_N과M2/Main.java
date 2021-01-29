package boj15650_N과M2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] res;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		res = new int[M];

		if(M == 1) {
			for(int i=0; i<N; i++) System.out.println(i+1);
			return;
		}
		comb(0, 1);
	}
	
	public static void comb(int a, int s) {
		if(a == M) {
			for(int i=0; i<M; i++) {
				System.out.print(res[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=s; i<=N; i++) {
			res[a] = i;
			comb(a+1, i+1);
		}
	}
}