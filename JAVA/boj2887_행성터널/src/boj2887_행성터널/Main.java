package boj2887_행성터널;

/* 최소신장트리 문제
 * 크루스칼 알고리즘
 * -> 메모리초과
 * weight의 min값 구하는 부분에서 100,000*99999/2*12(byte) > 128MB
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge>{
		int start, end, weight;
		
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return weight-o.weight;
		}
	}
	
	static int n;
	static int[][] space;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		space = new int[n][3];
		int [] parent = new int[n];
		
		int answer = 0;
		int weight = 0;
		
		//i행성의 좌표값 input
		for(int i=0; i<n; ++i) {
			st = new StringTokenizer(br.readLine());
			
			space[i][0] = Integer.parseInt(st.nextToken());		//x
			space[i][1] = Integer.parseInt(st.nextToken());		//y
			space[i][2] = Integer.parseInt(st.nextToken());		//z
		}
		
		//A행성-B행성 연결시 비용 : min(|xA-xB|, |yA-yB|, |zA-zB|)
		//i-j = j-i 이므로 j는 i부터 시작(두번 할 필요x)
		for(int i=0; i<n; ++i) {
			for(int j=i; j<n; ++j) {
				if(i == j) continue;
				
				//x
				weight = Math.abs(space[i][0]-space[j][0]);
				//x, y
				weight = Math.min(weight, Math.abs(space[i][1]-space[j][1]));
				//x, y, z
				weight = Math.min(weight, Math.abs(space[i][2]-space[j][2]));

				pq.add(new Edge(i, j, weight));
			}
		}
		
		//parent를 자신으로 초기화
		for(int i=0; i<n; ++i) {
			parent[i] = i;
		}
		
		//가중치 작은것(앞쪽idx)부터 선택하면서 크루스칼 알고리즘 실행
		int len = pq.size();
		for(int i=0; i<len; ++i) {
			Edge node = pq.poll();
			
			//사이클이 발생하지 않는 경우(시작노드와 도착노드의 루트노드가 같지않으면)
			//에 집합에 포함(union 함수 실행), 노드(터널) 연결
			if(ckCycle(parent, node.start) != ckCycle(parent, node.end)) {
				union(parent, node.start, node.end);
				answer += node.weight;
			}
		}
		
		System.out.println(answer);
	}
	
	//x가 속한 집합 찾기(사이클 발생 확인), 루트노드 반환
	static int ckCycle(int[] parent, int node) {
		//parent[node]가 현재 노드랑 다르면 루트노드가 따로 있으므로
		//루트노드 찾아서 올라가는 재귀
		if(parent[node] != node) {
			parent[node] = ckCycle(parent, parent[node]);
		}
		return parent[node];
	}
	
	//start, end 속한 집합 합치기
	static void union(int[] parent, int start, int end) {
		//start와 end노드의 루트노드 찾아서 저장
		start = ckCycle(parent, start);
		end = ckCycle(parent, end);
		
		//더 위에 있는(숫자가 작은) 노드가 루트노드가 됨
		if(start < end) parent[end] = start;
		else parent[start] = end;
	}
}