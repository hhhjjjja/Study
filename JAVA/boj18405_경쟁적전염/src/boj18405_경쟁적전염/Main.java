package boj18405_경쟁적전염;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
   int virus;
   int row;
   int col;
   
   public Virus(int virus, int row, int col) {
      this.virus = virus;
      this.row = row;
      this.col = col;
   }
}

public class Main {
   public static void main(String[] args) throws IOException {
      Queue<Virus> queue = new LinkedList<Virus>();
      ArrayList<Virus> vList = new ArrayList<Virus>();
      
      int [] dx = {1, 0, -1, 0};   //우, 하, 좌, 상
      int [] dy = {0, 1, 0, -1};
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int N = Integer.parseInt(st.nextToken());      //시험관 크기
      int K = Integer.parseInt(st.nextToken());      //바이러스 종류 1~K번까지 K가지
      
      //0:빔 1:바이러스1번 ...
      int[][] ex = new int[N][N];
      for(int i=0; i<N; i++) {
         st = new StringTokenizer(br.readLine());
         for(int j=0; j<N; j++) {
            ex[i][j] = Integer.parseInt(st.nextToken());
            if(ex[i][j] != 0) {
               vList.add(new Virus(ex[i][j], i, j));
            }
         }
      }
      
      st = new StringTokenizer(br.readLine());   //answer 조건
      int S = Integer.parseInt(st.nextToken());   //S초 뒤에
      int X = Integer.parseInt(st.nextToken());   //(X,Y)에 존재하는 바이러스의 종류
      int Y = Integer.parseInt(st.nextToken());   //없으면 print 0
      
      //바이러스 작은 숫자부터 정렬
      Collections.sort(vList, new Comparator<Virus>() {
         public int compare(Virus o1, Virus o2) {
            return o1.virus - o2.virus;
         };
      });
      
      //S=0이면 전염시키지않고 바로 빠져나감
      if(S == 0) {
         System.out.println(ex[X-1][Y-1]);
         return;
      }
      
      int t = 0;
      //BFS
      //정렬된 list 순서대로 증식      
      for(int i=0; i<vList.size(); i++) {
         queue.offer(new Virus(vList.get(i).virus, vList.get(i).row, vList.get(i).col));
      }
      
      while(!queue.isEmpty()) {
         int vnum = queue.peek().virus;
         int row = queue.peek().row;
         int col = queue.poll().col;
         
         for(int i=0; i<4; i++) {
            int nrow = row + dy[i];
            int ncol = col + dx[i];
            
            if(nrow>=0 && nrow<N && ncol>=0 && ncol<N) {
               if(ex[nrow][ncol] == 0) {
                  ex[nrow][ncol] = vnum;
                  queue.offer(new Virus(vnum, nrow, ncol));
               }
            }
         }
         t++;
         
         if(vList.size()*S == t) {
            break;
         }
      }
      System.out.println(ex[X-1][Y-1]);
   }
}