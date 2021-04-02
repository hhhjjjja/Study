package programmers_신규아이디추천;
/* https://programmers.co.kr/learn/courses/30/lessons/72410?language=java
 * 조건 찬찬히 따라가면 풀기쉬움
 * 문자열 처리
 * */
public class Solution {

	public static void main(String[] args) {
		//Test Case
		String new_id = "...!@BaT#*..y.abcdefghijklm";		//return "bat.y.abcdefghi"
//		String new_id = "z-+.^.";		//return "z--"
//		String new_id = "=.=";		//return "aaa"
//		String new_id = "123_.def";		//return "123_.def"
//		String new_id = "abcdefghijklmn.p";		//return "abcdefghijklmn"
		
		System.out.println(solution(new_id));
	}
	
	public static String solution(String new_id) {
		//1단계 : 대->소문자 치환
        new_id = new_id.toLowerCase();
        
        //2단계 : 소문자, 숫자, -, _, .만 남기고 모두 제거
        String id = "";
        for(int i=0; i<new_id.length(); i++) {
            char tp = new_id.charAt(i);
            
            if(tp >= 'a' && tp <= 'z') {
				id += String.valueOf(tp);
			} else if(tp >= '0' && tp <= '9') {
				id += String.valueOf(tp);
			} else if(tp == '.' || tp == '-' || tp == '_') {
				id += String.valueOf(tp);
			}
        }
        
        //3단계 : .가 2번 이상 연속된 부분에서 .하나만 남기기
        int dotCnt = 0;
        String id3 = "";
        for(int i=0; i<id.length(); i++) {
            char tp = id.charAt(i);
            
            if(tp == '.') {
                if(dotCnt == 0) {
                    id3 += String.valueOf(tp);
                    dotCnt++;
                }
                else continue;
            } else {
                id3 += String.valueOf(tp);
                dotCnt = 0;
            }
        }
        
        //4단계 : .가 처음이나 끝에 위치하면 제거
        if(id3.startsWith(".")) {
            id = id3.substring(1);
        } else if(id3.endsWith(".")) {
            id = id3.substring(0, id3.length()-1);
        }
        
        //5단계 : 4단계 진행한 문자열이 빈 문자열이면 new_id에 "a" 대입
        if(id.length() == 0) id = "a";
        
        //6단계 : id 길이가 16자 이상이면 첫 15개 문자 제외하고 나머지 문자 모두 제거
        //제거한 후에 남은 문자열 끝이 .이면 제거
        if(id.length() >= 16) {
            id = id.substring(0, 15);
        }
        if(id.endsWith(".")) {
            id = id.substring(0, id.length()-1);
        }
        
        //7단계 : 길이가 2 이하면 마지막 문자를 길이가 3이 될때까지 복붙
        if(id.length() <= 2) {
            char add = id.charAt(id.length()-1);
            int addCnt = 3 - id.length();
            
            for(int i=0; i<addCnt; i++) {
                id += add;
            }
        }
        
        return id;
	}
}