package boj2887_�༺�ͳ�;

/* �ּҽ���Ʈ�� ����
 * ũ�罺Į �˰���
 * -> �޸��ʰ�
 * weight�� min�� ���ϴ� �κп��� 100,000*99999/2*12(byte) > 128MB
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
		
		//i�༺�� ��ǥ�� input
		for(int i=0; i<n; ++i) {
			st = new StringTokenizer(br.readLine());
			
			space[i][0] = Integer.parseInt(st.nextToken());		//x
			space[i][1] = Integer.parseInt(st.nextToken());		//y
			space[i][2] = Integer.parseInt(st.nextToken());		//z
		}
		
		//A�༺-B�༺ ����� ��� : min(|xA-xB|, |yA-yB|, |zA-zB|)
		//i-j = j-i �̹Ƿ� j�� i���� ����(�ι� �� �ʿ�x)
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