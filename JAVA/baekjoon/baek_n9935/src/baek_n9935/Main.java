package baek_n9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String bomb = br.readLine();
		
		while(true) {
			if(str.contains(bomb)) {
				str = str.replace(bomb, "");
			} else {
				break;
			}
		}
		
		if(str.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(str);
		}
	}
}



//문자열 '압축' -> 스택
//스택에 들어오는 문자길이 >= 패턴의 문자길이일때 비교하고 같으면 pop
//비교 뒤부터


//비교시작은 같은조건
//스택사용x
//res[]에 string무조건 넣고 bomb 끝자리가 res에 들어오면 앞자리들까지 비교

