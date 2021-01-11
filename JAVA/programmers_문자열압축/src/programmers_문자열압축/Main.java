package programmers_문자열압축;

public class Main {

	public static void main(String[] args) {
//		String s = "aabbaccc";
		String s = "ababcdcdababcdcd";
		
		char[] c = s.toCharArray();
		
		int answer = s.length();
		int rlen = 0;
		
		for(int i=1; i<(s.length()/2)+1; i++) {
			
			rlen = comp(c, i).length();
			answer = Math.min(rlen, answer);
		}
		
        System.out.println(answer);
//        return answer;
	}
	
	public static String comp(char[] c, int len) {
		String rs = "";
		
		int cnt = 1;
		int i=0;
		
		String [] ssr;
		
		if (c.length%len == 0) {
			ssr = new String[c.length/len];
		} else {
			ssr = new String[c.length/len+1];
		}
		
		while(i<c.length/len) {
			String cs = "";
			
			for(int j=len*i; j<len*(i+1); j++) {
				cs += Character.toString(c[j]);
			}
			ssr[i] = cs;
			i++;
		}
		if(c.length%len != 0) {
			String cs = "";
			for(int j=(c.length-c.length%len); j<c.length; j++) {
				cs += Character.toString(c[j]);
			}
			ssr[i] = cs;
		}
		
		for(int j=1; j<ssr.length; j++) {
			if(ssr[j-1].equals(ssr[j])) cnt++;
			else {
				if(cnt == 1) {
					rs += ssr[j-1];
				} else {
					rs += cnt+ssr[j-1];
				}
				cnt=1;
			}
			
			if(j == ssr.length-1) {
				if(cnt == 1) {
					rs += ssr[j];
				} else {
					rs += cnt+ssr[j];
				}
			}
		}
		return rs;
	}

}
