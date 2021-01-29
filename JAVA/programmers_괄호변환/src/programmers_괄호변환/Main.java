package programmers_괄호변환;

public class Main {
	public static void main(String[] args) {
		//TestCase
//		String p = "(()())()";
//		String p = ")(";
		String p = "()))((()";
		
		System.out.println(solution(p));
	}
	
	static String solution(String p) {
		return cutStr(p);
	}
	
	static String cutStr(String w) {
		//1. 입력이 빈 문자열인 경우, 빈 문자열 반환
		if(w.length() == 0) return "";
		
		String u = "";
		String v = "";
		
		int lcnt = 0;
		int rcnt = 0;
		
		//2) 균형잡힌 문자열 u와 v로 분리 (v는 빈문자열일수도 있음)
		char [] ptoc = w.toCharArray();
		for(int i=0; i<ptoc.length; i++) {
			u += ptoc[i];
			if(ptoc[i] == '(') lcnt++;
			else rcnt++;		//ptoc[i]==')'
			
			if(lcnt == rcnt) {
				v = w.substring(i+1, w.length());
				break;
			}
		}
		
		//3-1) u가 올바른 괄호 문자열이면
		if(properStr(u)) {
			//v에 대해 1단계부터 수행
			return u + cutStr(v);
		}
		//4) u가 올바른 괄호 문자열이 아니면
		else {
			//4-1) 빈 문자열(tmp)에 첫번째 문자로 '(' 붙임
			//4-2) v에 대해 1단계부터 재귀적으로 수행한 결과를 이어붙임
			//4-3) ')'를 다시 붙임
			String tmp = "(" + cutStr(v) + ")";
			
			//4-4) u의 첫번째와 마지막 문자를 제거하고
			u = u.substring(1, u.length()-1);
			//나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙임
			u = reverse(u);
			tmp += u;
			
			//4-5) 생성된 문자열 반환
			return tmp;
		}
	}
	
	//올바른 문자열 판별
	static boolean properStr(String u) {
		int cnt = 0;
		for(int i=0; i<u.length(); i++) {
			if(u.charAt(i) == '(') cnt++;
			else cnt--;
			
			if(cnt < 0) return false;
		}
		return true;
	}
	
	//문자열 뒤집기
	static String reverse(String w) {
		char[] wArr = w.toCharArray();
		
		for(int i=0; i<wArr.length; i++) {
			if(wArr[i] == ')') wArr[i] = '(';
			else wArr[i] = ')';
		}
		return new String(wArr);
	}
}