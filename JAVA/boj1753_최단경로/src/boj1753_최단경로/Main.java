package boj1753_최단경로;

/* 최단경로 문제
 * - 다익스트라
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

	//가중치 기준으로 오름차순 정렬
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
	static int INF = 10*300000;		//최대가중치*최대간선개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());		//정점 개수
		E = Integer.parseInt(st.nextToken());		//간선 개수
		
		//간선 정보
		graph = new ArrayList[V+1];
		
		for(int i = 1; i <= V; i++){
            graph[i] = new ArrayList<>();
        }
		
		//출발지점에서 i까지의 최단경로를 INF로 초기화
		distance = new int[V+1];
		Arrays.fill(distance, INF);
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());		//시작 정점의 번호
		
		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			//start에서 end까지 갈때 가중치 weight
			graph[start].add(new Node(end, weight));
		}
		
		//출발점 최단거리 = 0
		distance[K] = 0;
		
		//Dijkstra
		boolean[] visited = new boolean[V+1];
		pq.add(new Node(K, 0));
		
		while(!pq.isEmpty()) {
			Node tmp = pq.poll();
			
			//이미 처리된 적 있는 노드면 continue
			if(visited[tmp.v] == true) continue;
			visited[tmp.v] = true;
			
			for(Node node : graph[tmp.v]) {
				//현재노드 (tmp.v) 거쳐가는게 가중치가 작으면
				if(distance[node.v] > distance[tmp.v] + node.weight) {
					//작은 가중치로 갱신
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