package baek_n1371;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int [] alpha = new int[26];
		String str;
		int max = 0;
		
		while((str = br.readLine()) != null) {
			for(int i=0 ; i<str.length() ; i++) {
				if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
					alpha[str.charAt(i)-'a']++;
				}
			}
		}
		
		for(int i=0 ; i<26 ; i++) {
			max = Math.max(max,  alpha[i]);
		}
		for(int i=0 ; i<26 ; i++) {
			if(alpha[i] == max) {
				bw.write('a' + i);
			}
		}
			
		bw.flush();
		bw.close();
	}
}