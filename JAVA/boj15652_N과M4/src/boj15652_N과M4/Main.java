package boj15652_N과M4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int [] res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		res = new int[M];
		
		if(M == 1) {
			for(int i=0; i<N; i++) {
				System.out.println(i+1);
			}
			return ;
		}
		
		dfs(1, 0);
	}
	
	public static void dfs(int s, int idx) {
		if(idx == M) {
			for(int i=0; i<M; i++) System.out.print(res[i] + " ");
			System.out.println();
			return;
		}
		
		for(int i=s; i<=N; i++) {
			res[idx] = i;
			//비내림차순이므로 i를 s값에
			dfs(i, idx+1);
		}
	}
}