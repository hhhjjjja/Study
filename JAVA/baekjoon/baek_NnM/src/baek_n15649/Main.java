package baek_n15649;
import java.util.Scanner;

//�ڿ��� N�� M�� �־����� ��, �Ʒ� ������ �����ϴ� ���̰� M�� ������ ��� ���ϴ� ���α׷� �ۼ�
//1���� N���� �ڿ��� �߿��� �ߺ� ���� M���� �� ����
//1 <= M <= N <= 8

public class Main {
	static int n, m;
	static int [] visit;
	static int [] list;
	
	public static void dfs(int cnt) {
		if(cnt == m) {
			for(int i=0 ; i<m ; i++) {
				System.out.print(list[i] + " ");
			}
			System.out.println();
			return ;
		}
		
		for(int i=1 ; i<=n ; i++) {
			if(visit[i] == 1) continue;
			
			visit[i] = 1;
			list[cnt] = i;
			
			dfs(cnt+1);
			visit[i] = 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		n = scan.nextInt();
		m = scan.nextInt();
		
		visit = new int[9];
		list = new int[9];
		
		dfs(0);
	}
}
