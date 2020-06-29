package baek_n1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int [][] resArr;
	static int [] array;
	static int sum;
	
	static void res(int a, int b) {
		sum = 0;
		
		if(resArr[a][b] != -1) {
			return ;
		}
		
		for(int i=a; i<b; i++) {
			sum = sum + array[i];
		}
		resArr[a][b] = sum;
		
		return ;
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine().trim());
		array = new int [n];
		resArr = new int[n][n];
		
		String [] str = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			array[i] = Integer.parseInt(str[i]);
			
			for(int j=0; j<n; j++) {
				resArr[i][j] = -1;
			}
		}
		
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				res(i, j);
			}
		}
		
		int max = -1000;
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				if(max < resArr[i][j]) max = resArr[i][j];
			}
		}
		System.out.println(max);
	}
}