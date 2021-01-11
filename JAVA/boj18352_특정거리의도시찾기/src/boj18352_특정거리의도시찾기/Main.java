package boj18352_Ư���Ÿ��ǵ���ã��;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
      
      int n = s.nextInt();   //������ ����
      int m = s.nextInt();   //������ ����
      int k = s.nextInt();   //�Ÿ� ����
      int x = s.nextInt();   //��� ������ ��ȣ
      
      int[] distance = new int[300001];
      ArrayList<ArrayList<Integer>> linkLst = new ArrayList<ArrayList<Integer>>();
      
      //INIT link list & distance Array
      for(int i=0; i<=n; i++) {
         linkLst.add(new ArrayList<Integer>());
         distance[i] = -1;
      }
      
      distance[x] = 0;   //��ߵ���->��ߵ��� �Ÿ�
      
      for(int i=0; i<m; i++) {
         int a = s.nextInt();
         int b = s.nextInt();
         
         linkLst.get(a).add(b);   //a->b �ܹ���
      }
      
      //BFS
      Queue<Integer> queue = new LinkedList<Integer>();
      queue.add(x);         //��ߵ���x
      
      while(!queue.isEmpty()) {
         int tmp = queue.poll();
         
         for(int i=0; i<linkLst.get(tmp).size(); i++) {
            int next = linkLst.get(tmp).get(i);
            
            //�湮���� ������
            if(distance[next] == -1) {
               distance[next] = distance[tmp]+1;   //��ߵ��ÿ��� next���ñ��� �Ÿ�
               queue.add(next);
            }
         }
      }
      
      //�ִܰŸ�==k check, ���ù�ȣ print
      boolean chk = false;
      for(int i=0; i<=n; i++) {
         if(distance[i] == k) {
            System.out.println(i);
            chk = true;
         }
      }
      if(!chk) System.out.println(-1);
   }
}