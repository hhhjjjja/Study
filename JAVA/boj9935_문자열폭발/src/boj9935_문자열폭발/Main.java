package boj9935_문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String str,  bombStr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		str = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		bombStr = st.nextToken();
		
		int idx = 0;
		char[] result = new char[str.length()];
		for(int i=0; i<str.length(); i++) {
			//str 문자열 하나씩 잘라서 res 넣기
			result[idx] = str.charAt(i);
			
			//res에 넣은 문자열에 폭탄있는지 찾음
			if(findBomb(result, idx, bombStr)) {
				//있으면 폭탄문자열 길이만큼 index--
				idx = idx - bombStr.length();
			}
			//다음 str의 문자열이 덮음
			idx++;
		}
		String answer = String.valueOf(result, 0, idx);
		
		if(answer.length() == 0) System.out.println("FRULA");
		else System.out.println(answer);
	}
	
	public static boolean findBomb(char [] res, int idx, String bomb) {
		//res에 든 문자열이 폭탄문자열보다 짦으면 폭탄일리 없음
		if(idx < bombStr.length()-1) {
			return false;
		}
		
		//폭탄문자열 하나씩 res값이랑 비교
		for(int i=0; i<bombStr.length(); i++) {
			if(bombStr.charAt(i) != res[idx - bomb.length()+i+1]) {
				return false;
			}
		}
		return true;
	}
}