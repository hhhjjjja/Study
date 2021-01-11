package boj14502_������;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
   int row;
   int col;
   public Virus(int row, int col) {
      this.row = row;
      this.col = col;
   }
}

public class Main {
   static ArrayList<Virus> virus = new ArrayList<Virus>();
   static int[][] map;
   static int[][] tmp;
   
   static int N;
   static int M;
   static int max = 0;
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      N = Integer.parseInt(st.nextToken());   //���� ����(3<=N)
      M = Integer.parseInt(st.nextToken());   //���� ����(M<=8)
      
      map = new int[N][M];
      
      for(int i=0; i<N; i++) {
         st = new StringTokenizer(br.readLine());
         for(int j=0; j<M; j++) {
            //0:��ĭ  1:��  2:���̷���
            map[i][j] = Integer.parseInt(st.nextToken());
            //�ʱ� ���̷��� ��ǥ
            if(map[i][j] == 2) {
               virus.add(new Virus(i, j));
            }
         }
      }
      
      int wallCnt = 0;
      makeWall(wallCnt);
      
      //��� ���
      System.out.println(max);
   }
   
   //tmp Array ����
   static void copyMap() {
      tmp = new int[N][M];
      for(int i=0; i<N; i++) {
         for(int j=0; j<M; j++) {
            tmp[i][j] = map[i][j];
         }
      }
   }
   
   //�� �� �����
   public static void makeWall(int wallCnt) {
      //�ִ� 3��
      if(wallCnt == 3) {
         copyMap();
         //�� 3�� ����� ���̷��� �۶߸���
         spreadVirus();
         //0�� ���帹���� return
         max = Math.max(max, getMax());
         return;
      }
      
      for(int i=0; i<N; i++) {
         for(int j=0; j<M; j++) {
            //i,j�� ��ĭ�� ��
            if(map[i][j] == 0) {
               map[i][j] = 1;
               makeWall(wallCnt+1);
               
               map[i][j] = 0;
            }
         }
      }
   }
   
   public static void spreadVirus() {
      int[] dx = {-1, 0, 1, 0};   //���ʺ��� �ð����
      int[] dy = {0, 1, 0, -1};
      
      Queue<Virus> queue = new LinkedList<Virus>();
      for(int i=0; i<virus.size(); i++) {
         queue.offer(new Virus(virus.get(i).row, virus.get(i).col));
      }
      
      while(!queue.isEmpty()) {
         int r = queue.peek().row;
         int c = queue.poll().col;
         
         for(int j=0; j<4; j++) {
            int nr = r + dy[j];
            int nc = c + dx[j];
            
            if(nr >= 0 && nc >= 0 && nr < N && nc < M) {
               if(tmp[nr][nc] == 0) {
                  tmp[nr][nc] = 2;
                  queue.offer(new Virus(nr, nc));
               }
            }
         }
      }
   }
   
   public static int getMax() {
      int cnt = 0;
      for(int i=0; i<N; i++) {
         for(int j=0; j<M; j++) {
            if(tmp[i][j] == 0) cnt++;
         }
      }
      return cnt;
   }
}