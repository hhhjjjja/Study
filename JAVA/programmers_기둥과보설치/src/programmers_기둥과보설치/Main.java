package programmers_��հ�����ġ;

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
      
      int[][] answer = new int[rlen][3]; //[x][0]:����x [1]:����y [2]:���(0)or��(1)
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
         x = build_frame[i][0];   //��ġ������ǥ x(����)
         y = build_frame[i][1];   //��ġ������ǥ y(����)
         a = build_frame[i][2];   //0:���(wlen-(y+1),x ~ wlen-y,x), 1:��(wlen-(y+1),x ~ wlen-(y+1), x+1)
         b = build_frame[i][3];   //0:����, 1:��ġ
         
         //��ġ
         if(b == 1) {
            // ���
            if(a == 0) {
               //�ٴڿ� ��� ��� or �Ʒ���ǥ�� ����� ���� or ������ �Ѱ��� ��¾�(0or1��������)
               if(y==0 || wall[wlen-y][x]==0 || wall[wlen-(y+1)][x-1]==1 || wall[wlen-(y+1)][x-1]==0) {
                  wall[wlen-(y+1)][x] = 0;
               }
            }
            // ��
            else if(a == 1) {
               //���ʿ� ��� or �����ʿ� ��� or ���ʿ� ��
               if(wall[wlen-y][x]==0 || wall[wlen-y][x+1]==0 || (wall[wlen-(y+1)][x-1]==1 && wall[wlen-(y+1)][x+1]==1)) {
                  wall[wlen-(y+1)][x] = 1;
               }
            }
         }
         
         //����
         else if(b == 0) {
            wall[wlen-(y+1)][x] = 2;
            
            // ���
            if(a == 0) {
               //�ٴڿ� ��� ��� or �Ʒ���ǥ�� ����� ���� or ������ �Ѱ��� ��¾�(0or1��������)
               if(y==0 || wall[wlen-y][x]==0 || wall[wlen-(y+1)][x-1]==1 || wall[wlen-(y+1)][x-1]==0) {
                  System.out.println("��ջ��� "+x+", "+y);
                  continue;
               }
               wall[wlen-(y+1)][x] = 0;
            }
            
            // ��
            else if(a == 1) {
               //���ʿ� ��� or �����ʿ� ��� or ���ʿ� ��
               if(wall[wlen-y][x]==0 || wall[wlen-y][x+1]==0 || (wall[wlen-(y+1)][x-1]==1 && wall[wlen-(y+1)][x+1]==1)) {
                  System.out.println("������ "+x+", "+y);
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