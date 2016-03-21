/*
ID: king8791
LANG: JAVA
TASK: castle
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class castle {
                                      
    public static boolean [] westFlag=new boolean [16];
    public static boolean [] northFlag=new boolean [16];
    public static boolean [] eastFlag=new boolean [16];
    public static boolean [] southFlag=new boolean [16];
    
    public static void generateFlag() {
        for (int i=0;i<16;i++) {
            int i2=i;
            if (i2>=8) {
                i2-=8;
                southFlag[i]=true;
            }
            if (i2>=4) {
                i2-=4;
                eastFlag[i]=true;
            }
            if (i2>=2) {
                i2-=2;
                northFlag[i]=true;
            }
            if (i2>=1) {
                i2-=1;
                westFlag[i]=true;
            }
        }
    }
    
    public static int placeMarker (int [][] numbers, int X, int Y, boolean [][] travelled, int [][] marker, int roomId) {
        if (X<0 || X>=numbers.length || Y<0 || Y>=numbers[X].length || travelled[X][Y]) {
            return 0;
        } else {
            travelled[X][Y]=true;
            marker[X][Y]=roomId;
            int num=numbers[X][Y];
            int size=1;
            if (!westFlag[num]) {
                size+=placeMarker(numbers,X-1,Y,travelled,marker,roomId);
            }
            if (!eastFlag[num]) {
                size+=placeMarker(numbers,X+1,Y,travelled,marker,roomId);
            }
            if (!northFlag[num]) {
                size+=placeMarker(numbers,X,Y-1,travelled,marker,roomId);
            }
            if (!southFlag[num]) {
                size+=placeMarker(numbers,X,Y+1,travelled,marker,roomId);
            }
            return size;
        }
    }
    
    public static class Solution implements Comparable<Solution> {
        public int roomSize;
        public int removedWallRoomSize;
        public int x;
        public int y;
        public char direct;
        
        @Override
        public int compareTo (Solution s) {
            int diff=this.removedWallRoomSize-s.removedWallRoomSize;
            if (diff==0) {
                int diffx=this.x-s.x;
                if (diffx==0) {
                    int diffy=this.y-s.y;
                    if (diffy==0) {
                        return this.direct-s.direct;
                    } else {
                        return diffy;
                    }
                } else {
                    return -diffx;
               }
            } else {
                return diff;
            }
        }
    }
    
    public static Solution FinalSolution;
    
    public static void getSolution (int [][] numbers, int X, int Y, boolean [][] travelled, int [][] marker, int [] roomSize) {
        if (X<0 || X>=numbers.length || Y<0 || Y>=numbers[X].length || travelled[X][Y]) {
        } else {
            travelled[X][Y]=true;
            int roomId=marker[X][Y];
            if (X-1>=0) {
                if (marker[X-1][Y]!=roomId) {
                    Solution s=new Solution();
                    s.x=X;
                    s.y=Y;
                    s.roomSize=roomSize[marker[X][Y]];
                    s.removedWallRoomSize=s.roomSize+roomSize[marker[X-1][Y]];
                    s.direct='W';
                    if (FinalSolution==null) {
                        FinalSolution=s;
                    } else {
                        if (s.compareTo(FinalSolution)>0) {
                            FinalSolution=s;
                        }
                    }
                }
                getSolution(numbers,X-1,Y,travelled,marker,roomSize);
            }
            if (X+1<numbers.length) {
                if (marker[X+1][Y]!=roomId) {
                    Solution s=new Solution();
                    s.x=X;
                    s.y=Y;
                    s.roomSize=roomSize[marker[X][Y]];
                    s.removedWallRoomSize=s.roomSize+roomSize[marker[X+1][Y]];
                    s.direct='E';
                    if (FinalSolution==null) {
                        FinalSolution=s;
                    } else {
                        if (s.compareTo(FinalSolution)>0) {
                            FinalSolution=s;
                        }
                    }
                }
                getSolution(numbers,X+1,Y,travelled,marker,roomSize);
            }
            if (Y-1>=0) {
                if (marker[X][Y-1]!=roomId) {
                    Solution s=new Solution();
                    s.x=X;
                    s.y=Y;
                    s.roomSize=roomSize[marker[X][Y]];
                    s.removedWallRoomSize=s.roomSize+roomSize[marker[X][Y-1]];
                    s.direct='N';
                    if (FinalSolution==null) {
                        FinalSolution=s;
                    } else {
                        if (s.compareTo(FinalSolution)>0) {
                            FinalSolution=s;
                        }
                    }
                }
                getSolution(numbers,X,Y-1,travelled,marker,roomSize);
            }
            if (Y+1<numbers[X].length) {
                if (marker[X][Y+1]!=roomId) {
                    Solution s=new Solution();
                    s.x=X;
                    s.y=Y;
                    s.roomSize=roomSize[marker[X][Y]];
                    s.removedWallRoomSize=s.roomSize+roomSize[marker[X][Y+1]];
                    s.direct='S';
                    if (FinalSolution==null) {
                        FinalSolution=s;
                    } else {
                        if (s.compareTo(FinalSolution)>0) {
                            FinalSolution=s;
                        }
                    }
                }
                getSolution(numbers,X,Y+1,travelled,marker,roomSize);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        generateFlag();
        BufferedReader br=new BufferedReader(new FileReader("castle.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int width=Integer.parseInt(st.nextToken());
        int height=Integer.parseInt(st.nextToken());
        int [][] numbers=new int [width][height];
        for (int i=0;i<height;i++) {
            st=new StringTokenizer(br.readLine());
            for (int i2=0;i2<width;i2++) {
                numbers[i2][i]=Integer.parseInt(st.nextToken());
            }
        }
        boolean [][] travelled=new boolean [50][50];
        int [][] marker=new int [50][50];
        int roomId=0;
        int [] size=new int [2500];
        int temp;
        for (int i=0;i<height;i++) {
            for (int i2=0;i2<width;i2++) {
                temp=placeMarker(numbers,i2,i,travelled,marker,roomId);
                if (temp>0) {
                    size[roomId++]=temp;
                }
            }
        }
        int maxRoomSize=size[0];
        for (int i=1;i<roomId;i++) {
            if (size[i]>maxRoomSize) {
                maxRoomSize=size[i];
            }
        }
        getSolution(numbers,0,0,new boolean[50][50],marker,size);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
        pw.println(roomId+"\n"+maxRoomSize+"\n"+FinalSolution.removedWallRoomSize+"\n"+(FinalSolution.y+1)+" "+(FinalSolution.x+1)+" "+FinalSolution.direct);
        pw.close();
        System.exit(0);
    }
    
}
