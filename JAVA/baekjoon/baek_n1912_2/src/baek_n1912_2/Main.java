package baek_n1912_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine().trim());
		
		int [] array = new int [n];
		int [] dp = new int [n];
		
		String [] str = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			array[i] = Integer.parseInt(str[i]);
		}
		
		int res = array[0];
		dp[0] = array[0];
		
		for(int i=1; i<n; i++) {
			dp[i] = Math.max(dp[i-1]+array[i], array[i]);
			res = Math.max(res, dp[i]);
		}
		System.out.println(res);
	}
}