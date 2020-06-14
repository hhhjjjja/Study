import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		try {
			String[] tmp = null;
			int n = Integer.parseInt(br.readLine().trim());
			for (int i=0; i<n; i++) {
				tmp = br.readLine().split(" ");
				
				int a = Integer.parseInt(tmp[0]);
				int b = Integer.parseInt(tmp[1]);
				bw.write((a+b) + "\n");
			}
			
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}