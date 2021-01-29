package boj10825_국영수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Student {
	String name;
	int kn;
	int en;
	int math;
	
	public Student(String name, int kn, int en, int math) {
		this.name = name;
		this.kn = kn;
		this.en = en;
		this.math = math;
	}
}

public class Main {
	static ArrayList<Student> arr = new ArrayList<Student>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			arr.add(new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(arr, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				//국어점수가 같으면
				if(o1.kn == o2.kn) {
					//영어점수가 같으면
					if(o1.en == o2.en) {
						//수학점수가 같으면
						if(o1.math == o2.math) {
							//이름 오름차순
							return o1.name.compareTo(o2.name);
						} else {
							//수학점수가 다르면 수학점수 내림차순
							return o2.math - o1.math;
						}
					} else {
						//영어 : 오름차순
						return o1.en - o2.en;
					}
					
				//국어 점수가 다르면
				} else {
					//국어 : 내림차순
					return o2.kn - o1.kn;
				}
			}
		});
		
		//정렬된 값 이름만 print
		for(int i=0; i<arr.size(); i++) {
			System.out.println(arr.get(i).name);
		}
	}
}