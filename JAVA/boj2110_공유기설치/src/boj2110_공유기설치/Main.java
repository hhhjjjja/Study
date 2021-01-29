package boj2110_공유기설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int c, n;
	static int[] combi;
	static int [] hp;
	static PriorityQueue<Integer> minArr = new PriorityQueue<Integer>(Collections.reverseOrder());
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());	//도현이집 개수
		c = Integer.parseInt(st.nextToken());	//공유기 개수
		
		hp = new int[n];
		combi = new int[c];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			hp[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(hp);
		combination(0, 0);
		
		System.out.println("RESULT : " + minArr.poll());
	}
	
	static void combination(int r, int start) {
		if(r == c) {
			int min = hp[n-1];
			for(int i=0; i<c-1; i++) {
				if(i+1 < c) {
					min = Math.min(min, combi[i+1] - combi[i]);
				}
			}
			minArr.add(min);
			
			return;
		}
		
		for(int i=start; i<n; i++) {
			combi[r] = hp[i];
			combination(r+1, i+1);
		}
	}
}