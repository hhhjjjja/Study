package boj11404_플로이드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int bus[][];
	static int d[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());				//도시 개수
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());				//버스 개수
		
		bus = new int[m][3];		//[m][0]:버스의시작도시, [1]:도착도시, [2]:한번 타는데 필요한 비용
		d = new int[n][n];
		
		for(int i=0; i<m; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<3; ++j) {
				bus[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//최단거리 테이블 초기화
		for(int a[]:d) {
			Arrays.fill(a, 10000000);		//100000*100
		}
		
		for(int i=0; i<m; ++i) {
			int start = bus[i][0]-1;
			int end = bus[i][1]-1;
			
			if(d[start][end] > bus[i][2]) d[start][end] = bus[i][2];
		}
		
		//플로이드 워셜 알고리즘
		//거쳐가는 노드를 기준으로 최단거리 테이블 갱신
		for(int k=0; k<n; ++k) {
			for(int i=0; i<n; ++i) {
				for(int j=0; j<n; ++j) {
					if(i==j) d[i][j] = 0;				//시작도시와 도착도시가 같으면 0
					
					d[i][j] = Math.min(d[i][j], d[i][k]+d[k][j]);
				}
			}
		}
		
		//print answers
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				if(d[i][j] >= 10000000) d[i][j] = 0;		//최댓값인 10000000이면 i에서 j도시로 갈 수 없는 경우이므로 0
				System.out.print(d[i][j] + " ");
			}
			System.out.println();
		}
	}
}