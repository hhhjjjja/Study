package boj14888_�����ڳ����ֱ�;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
   static int [] numArr;
   //�������� : ��� ���� ����� �׻� -10�ﺸ�� ũ�ų����� 10�ﺸ�� �۰ų� ����.
   static int max = -100000001;
   static int min = 100000001;
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      //����len:n, �ѿ����ڰ���:n-1
      int n = Integer.parseInt(st.nextToken());
      numArr = new int [n];
      int [] cal = new int [4];   //0:+   1:-     2:*   3:/ ����
      String [] calArr = new String [n-1];      //������ ������ŭ ����ִ� �迭
      
      //����
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++) {
         numArr[i] = Integer.parseInt(st.nextToken());
      }
      
      //������
      int cnt = 0;
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<4; i++) {
         cal[i] = Integer.parseInt(st.nextToken());
         if(cal[i] == 0) continue;
         for(int j=0; j<cal[i]; j++) {
            if(i == 0) calArr[cnt] = "+";
            else if(i == 1) calArr[cnt] = "-";
            else if(i == 2) calArr[cnt] = "*";
            else calArr[cnt] = "/";
            cnt++;
         }
      }
      
      String [] output = new String[n-1];
      boolean [] visited = new boolean[n-1];
      //������ ���� DFS
      permutation(calArr, output, visited, 0, n-1, n-1);
      
      //print result
      System.out.println(max);
      System.out.print(min);
   }
   
   public static void permutation(String [] arr, String [] output, boolean [] visited, int depth, int n, int r) {
      if(depth == r) {
         calc(output, r);
         return;
      }
      
      for(int i=0; i<n; i++) {
         if(visited[i] != true) {
            visited[i] = true;
            output[depth] = arr[i];
            permutation(arr, output, visited, depth+1, n, r);    
            visited[i] = false;
         }
      }
   }
   
   public static void calc(String [] arr, int r) {
      int idx = 0;
      int sum = numArr[0];
      
      while(true) {
         int cnum = numArr[idx+1];
         
         if(arr[idx].equals("+")) sum += cnum;
         else if(arr[idx].equals("-")) sum -= cnum;
         else if(arr[idx].equals("*")) sum *= cnum;
         else if(arr[idx].equals("/")) sum /= cnum;
         idx++;
         
         if(idx >= r) break;
      }
      
      //�ִ�, �ּҰ� ��
      max = Math.max(max, sum);
      min = Math.min(min, sum);
   }
}