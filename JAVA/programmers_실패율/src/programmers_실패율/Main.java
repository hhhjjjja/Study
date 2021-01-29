package programmers_실패율;

import java.util.ArrayList;

class Stage implements Comparable<Stage>{
	int stage;
	double failul;
	
	public Stage(int stage, double failul) {
		this.stage = stage;
		this.failul = failul;
	}

	@Override
	public int compareTo(Stage o) {
		if(this.failul == o.failul) {
			return this.stage < o.stage ? -1 : 1;
		} else {
			return this.failul > o.failul ? -1 : 1;
		}
	}
}

public class Main {

	public static void main(String[] args) {
		int N = 5;
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
//		int N = 5;
//		int[] stages = {4, 4, 4, 4, 4};
		
		solution(N, stages);
	}
	
	//N:전체 스테이지 개수
	//stage: 게임을 이용하는 사용자가 현재 멈춰있는 스테이지 번호가 담긴 배열
	//실패율 = 스테이지에 도달했으나 아직 클리어하지 못한 플레이어 수 / 스테이지에 도달한 플레이어수
	static int[] solution(int N, int[] stages) {
		ArrayList<Stage> list = new ArrayList<Stage>();

		for(int i=1; i<N+1; i++) {
			int now = 0;		//현재(i번쨰) 스테이지에 있는 플레이어 cnt
			int compl = 0;		//현재(i번째) 스테이지를 클리어한 플레이어 수 cnt
			
			for(int j=0; j<stages.length; j++) {
				if(i <= stages[j]) compl++;
				if(i == stages[j]) now++;
			}
			
			//i번 스테이지를 클리어한 플레이어가 없으면 실패율 0
			if(compl == 0) {
				list.add(new Stage(i, 0));
			} else {
				list.add(new Stage(i, (double)now/compl));
			}
		}
		
		//실패율이 높은 스테이지부터 내림차순
		list.sort(Stage::compareTo);
		
		//실패율 내림차순으로 정렬된 list에서 스테이지 번호만 answer배열에 저장
		int[] answer = new int[list.size()];
		for(int i=0; i<answer.length; i++) {
			answer[i] = list.get(i).stage;
		}

		return answer;
	}
}