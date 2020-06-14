package baek_n1254;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static boolean isPalindrome(String str) {
		int len = str.length();
		for(int i=0 ; i<len ; i++) {
			if(str.charAt(i) != str.charAt(len-i-1)) {
				return false;
			}
		}
		return true;
	}
	
	static int result(String str, int len) {
		for(int i=0 ; i<len ; i++) {
			if(isPalindrome(str.substring(i))) {
				return i + len;
			}
		}
		return len*2;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine().trim();
		
		int len = str.length();
		
		bw.write(result(str, len) + "");
		bw.flush();
		bw.close();
	}
}