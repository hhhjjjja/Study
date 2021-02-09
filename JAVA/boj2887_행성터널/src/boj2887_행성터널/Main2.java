package boj2887_�༺�ͳ�;

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
		
		//����ġ �������� ����
		@Override
		public int compareTo(Edge o) {
			return weight-o.weight;
		}
	}
	
	static class Node implements Comparable<Node> {
		int pos, num;		//��ǥ, ����ȣ
		
		public Node(int pos, int num) {
			this.pos = pos;
			this.num = num;
		}
		
		//��ǥ ��������, ��ǥ�� ������ ����
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
		
		//�켱���� ť�� ����ؼ� ���� ������ �ʾƵ���
		PriorityQueue<Node> x = new PriorityQueue<>();
		PriorityQueue<Node> y = new PriorityQueue<>();
		PriorityQueue<Node> z = new PriorityQueue<>();
		
		int answer = 0;
		
		//i�༺�� ��ǥ�� input
		for(int i=0; i<n; ++i) {
			st = new StringTokenizer(br.readLine());
			
			x.add(new Node(Integer.parseInt(st.nextToken()), i));
			y.add(new Node(Integer.parseInt(st.nextToken()), i));
			z.add(new Node(Integer.parseInt(st.nextToken()), i));
		}	
		
		//A�༺-B�༺ ������ : x,y,z��ǥ ��갪�� ���밪 �� �ּڰ������� �ּڰ��� ���ϴ� ������ �ʿ����.
		//�������� �� ����� ���� ����(�ͳ�)���� �����ؼ� �ּҰ��� ����ϴ� ���� ��� ����
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
		
		//parent�� �ڽ����� �ʱ�ȭ
		for(int i=0; i<n; ++i) {
			parent[i] = i;
		}
		
		//����ġ ������(����idx)���� �����ϸ鼭 ũ�罺Į �˰��� ����
		int len = pq.size();
		for(int i=0; i<len; ++i) {
			Edge node = pq.poll();
			
			//����Ŭ�� �߻����� �ʴ� ���(���۳��� ��������� ��Ʈ��尡 ����������)
			//�� ���տ� ����(union �Լ� ����), ���(�ͳ�) ����
			if(ckCycle(parent, node.start) != ckCycle(parent, node.end)) {
				union(parent, node.start, node.end);
				answer += node.weight;
			}
		}
		
		System.out.println(answer);
	}
	
	//x�� ���� ���� ã��(����Ŭ �߻� Ȯ��), ��Ʈ��� ��ȯ
	static int ckCycle(int[] parent, int node) {
		//parent[node]�� ���� ���� �ٸ��� ��Ʈ��尡 ���� �����Ƿ�
		//��Ʈ��� ã�Ƽ� �ö󰡴� ���
		if(parent[node] != node) {
			parent[node] = ckCycle(parent, parent[node]);
		}
		return parent[node];
	}
	
	//start, end ���� ���� ��ġ��
	static void union(int[] parent, int start, int end) {
		//start�� end����� ��Ʈ��� ã�Ƽ� ����
		start = ckCycle(parent, start);
		end = ckCycle(parent, end);
		
		//�� ���� �ִ�(���ڰ� ����) ��尡 ��Ʈ��尡 ��
		if(start < end) parent[end] = start;
		else parent[start] = end;
	}
}