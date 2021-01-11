package programmers_기둥과보설치;

import java.util.Arrays;

public class Main {

   public static void main(String[] args) {
      int n = 5;
//      int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
      int[][] build_frame = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
      int[][] res = solution(n, build_frame);
      
//      for(int i=0; i<res.length; i++) {
//         for(int j=0; j<res[0].length; j++) {
//            System.out.print(res[i][j] + " ");
//         }
//         System.out.println("");
//      }
   }
   
   public static int[][] solution(int n, int[][] build_frame) {
      int rlen = build_frame.length;
      int wlen = n+1;
      
      int[][] answer = new int[rlen][3]; //[x][0]:시작x [1]:시작y [2]:기둥(0)or보(1)
      int[][] wall = new int[wlen][wlen];
      
      for(int i=0; i<wlen; i++) {
         for(int j=0; j<wlen; j++) {
            wall[i][j] = 2;
         }
      }
      
      int x = 0;
      int y = 0;
      int a = 0;
      int b = 0;
      
      for(int i=0; i<rlen; i++) {
         x = build_frame[i][0];   //설치시작좌표 x(가로)
         y = build_frame[i][1];   //설치시작좌표 y(세로)
         a = build_frame[i][2];   //0:기둥(wlen-(y+1),x ~ wlen-y,x), 1:보(wlen-(y+1),x ~ wlen-(y+1), x+1)
         b = build_frame[i][3];   //0:삭제, 1:설치
         
         //설치
         if(b == 1) {
            // 기둥
            if(a == 0) {
               //바닥에 닿는 기둥 or 아래좌표에 기둥이 있음 or 양쪽중 한곳에 닿는애(0or1이있으면)
               if(y==0 || wall[wlen-y][x]==0 || wall[wlen-(y+1)][x-1]==1 || wall[wlen-(y+1)][x-1]==0) {
                  wall[wlen-(y+1)][x] = 0;
               }
            }
            // 보
            else if(a == 1) {
               //왼쪽에 기둥 or 오른쪽에 기둥 or 양쪽에 보
               if(wall[wlen-y][x]==0 || wall[wlen-y][x+1]==0 || (wall[wlen-(y+1)][x-1]==1 && wall[wlen-(y+1)][x+1]==1)) {
                  wall[wlen-(y+1)][x] = 1;
               }
            }
         }
         
         //삭제
         else if(b == 0) {
            wall[wlen-(y+1)][x] = 2;
            
            // 기둥
            if(a == 0) {
               //바닥에 닿는 기둥 or 아래좌표에 기둥이 있음 or 양쪽중 한곳에 닿는애(0or1이있으면)
               if(y==0 || wall[wlen-y][x]==0 || wall[wlen-(y+1)][x-1]==1 || wall[wlen-(y+1)][x-1]==0) {
                  System.out.println("기둥삭제 "+x+", "+y);
                  continue;
               }
               wall[wlen-(y+1)][x] = 0;
            }
            
            // 보
            else if(a == 1) {
               //왼쪽에 기둥 or 오른쪽에 기둥 or 양쪽에 보
               if(wall[wlen-y][x]==0 || wall[wlen-y][x+1]==0 || (wall[wlen-(y+1)][x-1]==1 && wall[wlen-(y+1)][x+1]==1)) {
                  System.out.println("보삭제 "+x+", "+y);
                  continue;
               }
               wall[wlen-(y+1)][x] = 1;
            }
         }
         
         for(int k=0; k<wlen; k++) {
            for(int j=0; j<wlen; j++) {
               System.out.print(wall[k][j] + " ");
            }
            System.out.println("");
         }
         System.out.println("");
      }
      
//      for(int i=0; i<wlen; i++) {
//         for(int j=0; j<wlen; j++) {
//            System.out.print(wall[i][j] + " ");
//         }
//         System.out.println("");
//      }
      
      int cnt=0;
      for(int i=0; i<wlen; i++) {
         for(int j=0; j<wlen; j++) {
            if(wall[i][j]==2) continue;
            
            answer[cnt][0] = j;
            answer[cnt][1] = wlen-(i+1);
            answer[cnt][2] = wall[i][j];
            cnt++;
         }
      }
      
      return answer;
   }
}