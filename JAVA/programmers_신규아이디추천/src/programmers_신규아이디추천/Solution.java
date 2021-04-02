package programmers_�űԾ��̵���õ;
/* https://programmers.co.kr/learn/courses/30/lessons/72410?language=java
 * ���� ������ ���󰡸� Ǯ�⽬��
 * ���ڿ� ó��
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
		//1�ܰ� : ��->�ҹ��� ġȯ
        new_id = new_id.toLowerCase();
        
        //2�ܰ� : �ҹ���, ����, -, _, .�� ����� ��� ����
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
        
        //3�ܰ� : .�� 2�� �̻� ���ӵ� �κп��� .�ϳ��� �����
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
        
        //4�ܰ� : .�� ó���̳� ���� ��ġ�ϸ� ����
        if(id3.startsWith(".")) {
            id = id3.substring(1);
        } else if(id3.endsWith(".")) {
            id = id3.substring(0, id3.length()-1);
        }
        
        //5�ܰ� : 4�ܰ� ������ ���ڿ��� �� ���ڿ��̸� new_id�� "a" ����
        if(id.length() == 0) id = "a";
        
        //6�ܰ� : id ���̰� 16�� �̻��̸� ù 15�� ���� �����ϰ� ������ ���� ��� ����
        //������ �Ŀ� ���� ���ڿ� ���� .�̸� ����
        if(id.length() >= 16) {
            id = id.substring(0, 15);
        }
        if(id.endsWith(".")) {
            id = id.substring(0, id.length()-1);
        }
        
        //7�ܰ� : ���̰� 2 ���ϸ� ������ ���ڸ� ���̰� 3�� �ɶ����� ����
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