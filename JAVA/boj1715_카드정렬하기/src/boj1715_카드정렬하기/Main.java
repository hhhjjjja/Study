package boj1715_카드정렬하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	
	public static void main(String[] args) throws IOException {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();		//우선순위 기본값=오름차순
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(Integer.parseInt(st.nextToken()));
		}
		
		int sum = 0;
		while(pq.size()>1) {
			//작은값부터 더함
			int tmp = pq.poll();
			int tmp2 = pq.poll();
			
			sum += tmp + tmp2;
			//바로 앞 2개 더한 값 큐에 저장
			//이때 우선순위 큐는 오름차순 정렬
			pq.add(tmp + tmp2);
		}
		
		System.out.println(sum);
	}
}