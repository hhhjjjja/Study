package boj15686_ġŲ���;

import java.util.ArrayList;
import java.util.*;

class Build {
	int r;
	int c;
	public Build(int i, int j) {
		r=i;
		c=j;
	}
}

public class Main {
	public static void main(String[] args) {
		ArrayList<Build> hArr = new ArrayList<>();	//����ǥ
		ArrayList<Build> cArr = new ArrayList<>();	//ġŲ����ǥ

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();	// ����ũ��
		int m = sc.nextInt();	// �ִ� ġŲ�� ����
		
		int[] select = new int[m];
		
		//������ǥ
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int tmp = sc.nextInt();
				if(tmp == 1) {
					hArr.add(new Build(i, j));
				} else if(tmp == 2) {
					cArr.add(new Build(i, j));
				}
			}
		}
		
		//????
		int sum = 0;
		for(int i=0; i<cArr.size(); i++) {
			for(int j=0; j<m; j++) {
				sum = Math.abs(hArr.get(j).c-cArr.get(i).c)+Math.abs(hArr.get(j).r-cArr.get(i).r);
			}
		}
		
		//???????
		int[] sumArr = new int[cArr.size()];
		for(int i=0; i<cArr.size(); i++) {
			for(int j=0; j<hArr.size(); j++) {
				sum += Math.abs(hArr.get(j).c-cArr.get(i).c)+Math.abs(hArr.get(j).r-cArr.get(i).r);
			}
			sumArr[i] = sum;
			System.out.println(sumArr[i]);
			sum=0;
		}
	}
}