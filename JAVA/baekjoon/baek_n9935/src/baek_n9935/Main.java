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



//���ڿ� '����' -> ����
//���ÿ� ������ ���ڱ��� >= ������ ���ڱ����϶� ���ϰ� ������ pop
//�� �ں���


//�񱳽����� ��������
//���û��x
//res[]�� string������ �ְ� bomb ���ڸ��� res�� ������ ���ڸ������ ��

