package boj3665_최종순위;
/* 위상정렬
 * 작년 순위 순서로 1위는 2,3,4...위들로 간선연결
 * 순위바뀐 경우 간선 방향 reverse
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T, n, m;
	static boolean[][] edge;
	static int[] last;
	static int[] indegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; ++t) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());		//팀의 수 n
			
			edge = new boolean[n+1][n+1];	//간선 정보(팀의 등수와 순서를 알 수 있도록)
			last = new int[n];				//작년 등수
			indegree = new int[n+1];		//각 팀별 진입차수
			
			//모든 노드의 진입차수를 0으로 초기화
			Arrays.fill(indegree, 0);
			
			st = new StringTokenizer(br.readLine());
			//작년 등수 input
			for(int i=0; i<n; ++i) {
				last[i] = Integer.parseInt(st.nextToken());
			}
			
			//작년 등수 순으로 간선연결, 진입차수 cnt input
			for(int i=0; i<n; ++i) {
				for(int j=i+1; j<n; ++j) {
					edge[last[i]][last[j]] = true;
					indegree[last[j]] += 1;
				}
			}
			
			//상대적으로 바뀐 등수
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<m; ++i) {
				st = new StringTokenizer(br.readLine());
				
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				//작년에 s->e였다면 e->s로 바꾸고 진입차수 +1, -1해주기
				if(edge[s][e]) {
					edge[s][e] = false;
					edge[e][s] = true;
					
					indegree[s] += 1;
					indegree[e] -= 1;
				
				//작년에 e->s인 경우
				} else {
					edge[s][e] = true;
					edge[e][s] = false;
					
					indegree[e] += 1;
					indegree[s] -= 1;
				}
			}
			
			//위상정렬(Topology sort)
			ArrayList<Integer> result = new ArrayList<Integer>();
			Queue<Integer> q = new LinkedList<Integer>();
			
			boolean certain = true;				//답이 될 수 있는 순서의 개수가 하나인지 아닌지 판별
			boolean cycle = false;				//사이클 발생 ox 판별하는 변수
			
			//진입차수가 0인 것(첫번째라는 의미)부터 큐에 삽입
			for(int i=1; i<=last.length; ++i) {
				if(indegree[i] == 0) q.add(i);
			}
			
			for(int i=0; i<n; ++i) {
				//q가 비어있으면 사이클 발생
				if(q.isEmpty()) {
					cycle = true;
					break;
				}
				
				//q에 2개이상 있으면 답이 여러가지가 될 수 있다는 의미
				if(q.size() >= 2) {
					certain = false;
					break;
				}
				
				int now = q.poll();
				result.add(now);
				
				for(int j=1; j<=n; ++j) {
					if(edge[now][j]) {
						indegree[j] -= 1;
						
						if(indegree[j] == 0) q.add(j);
					}
				}
			}
			
			//데이터에 일관성이 없어서 순위를 정할 수 없다면 "IMPOSSIBLE"
			if(cycle) {
				System.out.println("IMPOSSIBLE");
				
			//확실한 순위를 찾을 수 없으면 "?"
			} else if(!certain) {
				System.out.println("?");
				
			//1등 팀부터 순서대로 출력
			} else {
				for(int i=0; i<result.size(); ++i) {
					System.out.print(result.get(i)+" ");
				}
				System.out.println();
			}
		}
	}
}