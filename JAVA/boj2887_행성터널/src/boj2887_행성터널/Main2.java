package boj2887_행성터널;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
	static class Edge implements Comparable<Edge>{
		int start, end, weight;
		
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		//가중치 오름차순 정렬
		@Override
		public int compareTo(Edge o) {
			return weight-o.weight;
		}
	}
	
	static class Node implements Comparable<Node> {
		int pos, num;		//좌표, 노드번호
		
		public Node(int pos, int num) {
			this.pos = pos;
			this.num = num;
		}
		
		//좌표 오름차순, 좌표가 같으면 노드순
		@Override
		public int compareTo(Node o) {
			if(pos == o.pos) return num - o.num;
			return pos - o.pos;
		}
	}
	
	static int n;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int [] parent = new int[n];
		
		//우선순위 큐를 사용해서 정렬 해주지 않아도됨
		PriorityQueue<Node> x = new PriorityQueue<>();
		PriorityQueue<Node> y = new PriorityQueue<>();
		PriorityQueue<Node> z = new PriorityQueue<>();
		
		int answer = 0;
		
		//i행성의 좌표값 input
		for(int i=0; i<n; ++i) {
			st = new StringTokenizer(br.readLine());
			
			x.add(new Node(Integer.parseInt(st.nextToken()), i));
			y.add(new Node(Integer.parseInt(st.nextToken()), i));
			z.add(new Node(Integer.parseInt(st.nextToken()), i));
		}	
		
		//A행성-B행성 연결비용 : x,y,z좌표 계산값의 절대값 중 최솟값이지만 최솟값을 구하는 과정은 필요없음.
		//오름차순 후 비용이 작은 간선(터널)부터 연결해서 최소값을 계산하는 과정 없어도 ㄱㅊ
		//|xA-xB|
		for(int i=0; i<n-1; ++i) {
			Node A = x.poll();
			Node B = x.peek();
			pq.add(new Edge(A.num, B.num, Math.abs(A.pos - B.pos)));
		}
		
		//|yA-yB|
		for(int i=0; i<n-1; ++i) {
			Node A = y.poll();
			Node B = y.peek();
			pq.add(new Edge(A.num, B.num, Math.abs(A.pos - B.pos)));
		}
		
		//|zA-zB|
		for(int i=0; i<n-1; ++i) {
			Node A = z.poll();
			Node B = z.peek();
			pq.add(new Edge(A.num, B.num, Math.abs(A.pos - B.pos)));
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