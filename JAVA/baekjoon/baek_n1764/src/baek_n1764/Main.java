package baek_n1764;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//ArrayList<String> nlist = new ArrayList();
		HashSet<String> hs = new HashSet<String>();
		ArrayList<String> list = new ArrayList();
		
		try {
			StringTokenizer tr = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(tr.nextToken());
			int m = Integer.parseInt(tr.nextToken());
			
			for (int i=0; i<n; i++) {
				hs.add(br.readLine());
			}
			
			for (int j=0; j<m; j++) {
				String b = br.readLine();
				
				if(hs.contains(b)) {
					list.add(b);
				}
			}
			
			Collections.sort(list);
			
			bw.write(list.size() + "\n");
			for(int i=0; i<list.size(); i++) {
				bw.write(list.get(i) + "\n");
			}
			
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
