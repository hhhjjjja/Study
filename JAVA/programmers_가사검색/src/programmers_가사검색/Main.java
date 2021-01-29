package programmers_가사검색;

/* 이진탐색 사용 X
 * 정확성 테스트 18/18 통과 (25.0)
 * 효율성 테스트 2/5 통과 (30.0)
 * 합계 55.0 / 100.0
 */

public class Main {
	public static void main(String[] args) {
		//TEST CASE
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		
		int[] sol = Solution(words, queries);
		
		for(int i=0; i<sol.length; i++) {
			System.out.print(sol[i]+" ");
		}
	}

	static int[] Solution(String[] words, String[] queries) {
		int[] answer = new int [queries.length];
		
		for(int i=0; i<queries.length; i++) {
			char tmp = queries[i].charAt(0);		//쿼리의 첫번째 단어
			
			String compareStr = "";
			boolean location = false;		//와일드카드의 위치 -> true:접두사에	false:접미사에 위치
			int qcnt = 0;					//와일드카드 COUNT
			
			//와일드카드가 접두사에 위치
			if(tmp == '?') {
				location = true;
				qcnt = 1;
			} else {
				//와일드카드가 접미사에 위치하면 첫번째 알파벳 연결
				compareStr = Character.toString(tmp);
			}
			
			//두번째 알파벳부터 탐색
			for(int j=1; j<queries[i].length(); j++) {
				tmp = queries[i].charAt(j);
				
				if(tmp == '?') {
					qcnt++;				//와일드카드면 와일드카드 CNT++
				} else {
					compareStr += Character.toString(tmp);		//알파벳이라면 뒤에 단어들과 비교할 compareStr에 연결해줌
				}
			}
			
			//단어들과 비교 시작
			int ansCnt = 0;
			for(int j=0; j<words.length; j++) {
				if(words[j].length() != compareStr.length()+qcnt) continue;				//단어와 길이가 다르면 비교할 필요X
				
				if(location) {
					//와일드카드가 접두사에 위치하면 와일드카드CNT(qcnt) 이후부터 알파벳과 비교
					if(words[j].substring(qcnt, words[j].length()).equals(compareStr)) ansCnt++;
				} else {
					//와일드카드가 접미사에 위치하면 첫번째부터 qcnt까지의 알파벳과 비교
					if(words[j].substring(0, compareStr.length()).equals(compareStr)) ansCnt++;
				}
			}
			answer[i] = ansCnt;
		}
		
        return answer;
    }
}