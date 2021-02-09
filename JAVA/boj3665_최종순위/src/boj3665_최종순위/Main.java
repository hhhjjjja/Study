package boj3665_��������;
/* ��������
 * �۳� ���� ������ 1���� 2,3,4...����� ��������
 * �����ٲ� ��� ���� ���� reverse
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
			n = Integer.parseInt(st.nextToken());		//���� �� n
			
			edge = new boolean[n+1][n+1];	//���� ����(���� ����� ������ �� �� �ֵ���)
			last = new int[n];				//�۳� ���
			indegree = new int[n+1];		//�� ���� ��������
			
			//��� ����� ���������� 0���� �ʱ�ȭ
			Arrays.fill(indegree, 0);
			
			st = new StringTokenizer(br.readLine());
			//�۳� ��� input
			for(int i=0; i<n; ++i) {
				last[i] = Integer.parseInt(st.nextToken());
			}
			
			//�۳� ��� ������ ��������, �������� cnt input
			for(int i=0; i<n; ++i) {
				for(int j=i+1; j<n; ++j) {
					edge[last[i]][last[j]] = true;
					indegree[last[j]] += 1;
				}
			}
			
			//��������� �ٲ� ���
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<m; ++i) {
				st = new StringTokenizer(br.readLine());
				
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				//�۳⿡ s->e���ٸ� e->s�� �ٲٰ� �������� +1, -1���ֱ�
				if(edge[s][e]) {
					edge[s][e] = false;
					edge[e][s] = true;
					
					indegree[s] += 1;
					indegree[e] -= 1;
				
				//�۳⿡ e->s�� ���
				} else {
					edge[s][e] = true;
					edge[e][s] = false;
					
					indegree[e] += 1;
					indegree[s] -= 1;
				}
			}
			
			//��������(Topology sort)
			ArrayList<Integer> result = new ArrayList<Integer>();
			Queue<Integer> q = new LinkedList<Integer>();
			
			boolean certain = true;				//���� �� �� �ִ� ������ ������ �ϳ����� �ƴ��� �Ǻ�
			boolean cycle = false;				//����Ŭ �߻� ox �Ǻ��ϴ� ����
			
			//���������� 0�� ��(ù��°��� �ǹ�)���� ť�� ����
			for(int i=1; i<=last.length; ++i) {
				if(indegree[i] == 0) q.add(i);
			}
			
			for(int i=0; i<n; ++i) {
				//q�� ��������� ����Ŭ �߻�
				if(q.isEmpty()) {
					cycle = true;
					break;
				}
				
				//q�� 2���̻� ������ ���� ���������� �� �� �ִٴ� �ǹ�
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
			
			//�����Ϳ� �ϰ����� ��� ������ ���� �� ���ٸ� "IMPOSSIBLE"
			if(cycle) {
				System.out.println("IMPOSSIBLE");
				
			//Ȯ���� ������ ã�� �� ������ "?"
			} else if(!certain) {
				System.out.println("?");
				
			//1�� ������ ������� ���
			} else {
				for(int i=0; i<result.size(); ++i) {
					System.out.print(result.get(i)+" ");
				}
				System.out.println();
			}
		}
	}
}