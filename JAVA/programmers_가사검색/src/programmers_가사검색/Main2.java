package programmers_가사검색;

import java.util.ArrayList;
import java.util.Collections;

/* 이진탐색 사용
 * lower/upper bound 구현
 * */

public class Main2 {
	public static void main(String[] args) {
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		
		int[] sol = Solution(words, queries);
		
		for(int i=0; i<sol.length; i++) {
			System.out.print(sol[i]+" ");
		}
	}

	static int[] Solution(String[] words, String[] queries) {
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> revArr = new ArrayList<ArrayList<String>>();
		
		int[] answer = new int [queries.length];
		
		//단어의 길이별로 Array Init
		for(int i = 0; i < 10001; i++) { 
			arr.add(new ArrayList<String>()); 
			revArr.add(new ArrayList<String>());
		}
		
		//단어들 역방향으로 저장
		for(int i=0; i<words.length; i++) {
			String word = words[i];
			arr.get(word.length()).add(word);
			revArr.get(word.length()).add((new StringBuffer(word)).reverse().toString());
		}
		
		//단어 정렬
		for(int i=0; i<10001; i++) {
			Collections.sort(arr.get(i));
			Collections.sort(revArr.get(i));
		}

		//쿼리문의 와일드카드 위치 확인
		for(int i=0; i<queries.length; i++) {
			int ansCnt = 0;
			//와일드카드가 접두에 위치 -> 쿼리도 뒤집어서 revArr와 비교
			if(queries[i].charAt(0) == '?') {
				String tmp = (new StringBuffer(queries[i])).reverse().toString();		//쿼리 단어 뒤집
				
				//쿼리의 ?들이 표현할 수 있는 가장 작은값과 가장 큰값으로 만들어서(replace) 찾기
				int rightIndex = upper_bound(revArr.get(queries[i].length()), tmp.replaceAll("\\?", "z"));	//인자값(ex: frozzz)보다 큰 첫번째 idx
				int leftIndex = lower_bound(revArr.get(queries[i].length()), tmp.replaceAll("\\?", "a"));	//인자값(ex: froaaa)과 같거나 큰 첫번째 idx
				
				ansCnt = rightIndex - leftIndex;		//초과 위치 - 시작 위치
			} else {		//와일드카드가 접미에 위치 -> arr와 비교
				int rightIndex = upper_bound(arr.get(queries[i].length()), queries[i].replaceAll("\\?", "z"));
				int leftIndex = lower_bound(arr.get(queries[i].length()), queries[i].replaceAll("\\?", "a"));
				
				ansCnt = rightIndex - leftIndex;
			}
			answer[i] = ansCnt;
		}
		
        return answer;
    }
	
	//lower_bound : 주어진 인자값(target)보다 크거나 같은 값이 처음 나올 때의 위치 return
	static int lower_bound(ArrayList<String> list, String target) {
		int begin = 0;
		int end = list.size();
		
		while(begin < end) {
			int mid = (begin+end)/2;
			//list[mid]가 target보다 사전순으로 같거나 크면(뒤에 있으면)
			if(list.get(mid).compareTo(target) >= 0) end = mid;
			else begin = mid + 1;
		}
		
		return begin;
	}

	//upper_bound : 인자값(target)보다 큰 첫번째 위치를 반환
	static int upper_bound(ArrayList<String> list, String target) {
		int begin = 0;
	    int end = list.size();
	    
	    while(begin < end) {
	    	int mid = (begin+end)/2;
	    	
	    	//list[mid]가 target보다 작거나 같으면
	    	if(target.compareTo(list.get(mid)) >= 0) begin = mid + 1;
	    	else end = mid;
	    }
	    
	    return begin;
	}
}