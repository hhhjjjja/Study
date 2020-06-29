package baek_n15652;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int list[];
	static boolean check[];
	static StringBuilder sb = new StringBuilder();
	
	static void dfs(int idx, int depth) {
		if(depth == m) {
			for(int i=0; i<m; i++) {
				sb.append(list[i]).append(" ");
			}
			sb.append("\n");
			return ;
		}
		for(int i=idx; i<n; i++) {
			list[depth] = i+1;
			dfs(i, depth+1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new int[n];
		check = new boolean[n];
		
		dfs(0, 0);
		System.out.println(sb.toString());
	}

}
