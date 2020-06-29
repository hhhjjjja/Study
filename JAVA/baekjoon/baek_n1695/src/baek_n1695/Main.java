package baek_n1695;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int [][] dp = new int [5000][5000];
	static int [] array = new int [5000];

	static int palindrome(int start, int end) {
		int a, b;
		
		if (start >= end) {
			dp[start][end] = 0;
		}
		
		if(dp[start][end] != -1) {
			return dp[start][end];
		}
		
		if(array[start] == array[end]) {
			dp[start][end] = palindrome(start+1, end-1);
		} else {
			if((a = palindrome(start, end-1)) > (b = palindrome(start+1, end))) {
				dp[start][end] = b+1;
			} else {
				dp[start][end] = a+1;
			}
		}
		return dp[start][end];
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine().trim());
		
		String [] tmp = br.readLine().split(" ");
		
		for(int i=0; i<n; i++) {
			//수열값 넣기
			array[i] = Integer.parseInt(tmp[i]);
			
			//dp 초기화
			for(int j=0; j<n; j++) {
				dp[i][j] = -1;
			}
		}
		
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				palindrome(i, j);
			}
		}
		
		bw.write(dp[0][n-1]+"");
		bw.flush();
	}
}