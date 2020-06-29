package baek_n1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, V;
	static int map[][];
	static int visit[][];
	static boolean check[];
	
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	static void dfs(int V) {
		if (check[V - 1]) return ;  // list�� �� ����(�̹� Ȯ���� ����)�� return; 

		check[V - 1] = true;
		list.add(V);

		for (int j = 0; j < N; j++) {
			if (map[V - 1][j] == 1 && visit[V - 1][j] == 0) {
				visit[V - 1][j] = 1;
				dfs(j + 1);
			}
		}
	}

	static void bfs(int V) {
		queue.offer(V);

		while (!queue.isEmpty()) {
			V = queue.poll();
			for (int j = 0; j < N; j++) {
				if(check[V-1]) continue;
				
				if (map[V - 1][j] == 1 && visit[V - 1][j] == 0) {
					visit[V - 1][j] = 1;
					queue.offer(j + 1);
				}
			}
			
			if(check[V-1]!=true) { // ������ ���� ���� false�϶� list�� �߰�
				list.add(V);	// �̹� �� ������ �� ������ ����
			}
			check[V - 1] = true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //������ ����
		M = Integer.parseInt(st.nextToken()); //������ ����
		V = Integer.parseInt(st.nextToken()); //������

		map = new int[N][N];
		visit = new int[N][N];
		check = new boolean[N];

		for (int i = 0; i < M; i++) {		// ���� �Է�
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			map[a - 1][b - 1] = 1;  //������ ������̹Ƿ�
			map[b - 1][a - 1] = 1;
		}

		dfs(V);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();

		list.clear();
		
		for (int[] row : visit) {
			Arrays.fill(row, 0);
		}
		Arrays.fill(check, false);
		
		bfs(V);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
}