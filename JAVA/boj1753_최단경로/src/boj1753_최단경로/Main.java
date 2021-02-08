package boj1753_�ִܰ��;

/* �ִܰ�� ����
 * - ���ͽ�Ʈ��
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int v, weight;
	
	public Node(int v, int weight) {
		this.v = v;
		this.weight = weight;
	}

	//����ġ �������� �������� ����
	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}
}

public class Main {
	static int V, E, K;
	static ArrayList<Node>[] graph;
	static int [] distance;
	
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static int INF = 10*300000;		//�ִ밡��ġ*�ִ밣������
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());		//���� ����
		E = Integer.parseInt(st.nextToken());		//���� ����
		
		//���� ����
		graph = new ArrayList[V+1];
		
		for(int i = 1; i <= V; i++){
            graph[i] = new ArrayList<>();
        }
		
		//����������� i������ �ִܰ�θ� INF�� �ʱ�ȭ
		distance = new int[V+1];
		Arrays.fill(distance, INF);
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());		//���� ������ ��ȣ
		
		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			//start���� end���� ���� ����ġ weight
			graph[start].add(new Node(end, weight));
		}
		
		//����� �ִܰŸ� = 0
		distance[K] = 0;
		
		//Dijkstra
		boolean[] visited = new boolean[V+1];
		pq.add(new Node(K, 0));
		
		while(!pq.isEmpty()) {
			Node tmp = pq.poll();
			
			//�̹� ó���� �� �ִ� ���� continue
			if(visited[tmp.v] == true) continue;
			visited[tmp.v] = true;
			
			for(Node node : graph[tmp.v]) {
				//������ (tmp.v) ���İ��°� ����ġ�� ������
				if(distance[node.v] > distance[tmp.v] + node.weight) {
					//���� ����ġ�� ����
					distance[node.v] = distance[tmp.v] + node.weight;
					pq.add(new Node(node.v, distance[node.v]));
				}
			}
		}
		
		//Print Answer
		for(int i=1; i<=V; ++i) {
			if(distance[i] >= INF) System.out.println("INF");
			else {
				System.out.println(distance[i]);
			}
		}
	}
}