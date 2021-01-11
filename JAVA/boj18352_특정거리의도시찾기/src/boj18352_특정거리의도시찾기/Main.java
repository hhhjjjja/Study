package boj18352_특정거리의도시찾기;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
      
      int n = s.nextInt();   //도시의 개수
      int m = s.nextInt();   //도로의 개수
      int k = s.nextInt();   //거리 정보
      int x = s.nextInt();   //출발 도시의 번호
      
      int[] distance = new int[300001];
      ArrayList<ArrayList<Integer>> linkLst = new ArrayList<ArrayList<Integer>>();
      
      //INIT link list & distance Array
      for(int i=0; i<=n; i++) {
         linkLst.add(new ArrayList<Integer>());
         distance[i] = -1;
      }
      
      distance[x] = 0;   //출발도시->출발도시 거리
      
      for(int i=0; i<m; i++) {
         int a = s.nextInt();
         int b = s.nextInt();
         
         linkLst.get(a).add(b);   //a->b 단방향
      }
      
      //BFS
      Queue<Integer> queue = new LinkedList<Integer>();
      queue.add(x);         //출발도시x
      
      while(!queue.isEmpty()) {
         int tmp = queue.poll();
         
         for(int i=0; i<linkLst.get(tmp).size(); i++) {
            int next = linkLst.get(tmp).get(i);
            
            //방문한적 없으면
            if(distance[next] == -1) {
               distance[next] = distance[tmp]+1;   //출발도시에서 next도시까지 거리
               queue.add(next);
            }
         }
      }
      
      //최단거리==k check, 도시번호 print
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