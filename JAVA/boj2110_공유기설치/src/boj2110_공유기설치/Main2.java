package boj2110_공유기설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	static int c, n;
	static int [] hp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());	//도현이집 개수
		c = Integer.parseInt(st.nextToken());	//공유기 개수
		
		hp = new int[n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			hp[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(hp);
		
		int min = 1;				//공유기간 최소거리
		int max = hp[n-1] - 1;		//공유기 간 최대거리
		int answer = 0;
		
		while(min <= max) {
			int middle = (max+min)/2;		//공유기 거리 기준
			int prev = hp[0];				//첫 위치 설치
			int cnt = 1;					//설치한 공유기 수
			
			for(int i=0; i<n; i++) {
				int distance = hp[i] - prev;	//거리차
				
				if(distance >= middle) {		//거리차가 기준보다 크거나 같아야 설치 가능
					cnt++;
					prev = hp[i];
				}
			}
			
			//공유기 설치가 c보다 더 많으면 간격을 넓혀서 개수를 줄임
			if(cnt >= c) {
				min = middle + 1;
				answer = middle;
			} else {
				max = middle - 1;
			}
		}
		System.out.println(answer);
	}
}