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
import java.util.StringTokenizer;

class castle {

	public static int [][] mask;
	public static int [][] visited;
	public static int [] roomSize=new int [2501];
	
	public static int floodfill (int id, int x, int y) {
		if (x>=0 && x<visited.length && y>=0  && y<visited[x].length && visited[x][y]==0) {
			visited[x][y]=id;
			int count=1;
			if ((mask[x][y] & (1 << 0))==0) count+=floodfill(id,x,y-1);
			if ((mask[x][y] & (1 << 1))==0) count+=floodfill(id,x-1,y);
			if ((mask[x][y] & (1 << 2))==0) count+=floodfill(id,x,y+1);
			if ((mask[x][y] & (1 << 3))==0) count+=floodfill(id,x+1,y);
			return count;
		}
		return 0;
	}
	
	public static int [] floodfillAll () {
		visited=new int [mask.length][mask[0].length];
		int maxSize=0;
		int roomCount=1;
		for (int x=0;x<mask.length;x++)
			for (int y=0;y<mask[x].length;y++)
				if (visited[x][y]==0) {
					roomSize[roomCount]=floodfill(roomCount,x,y);
					maxSize=Math.max(maxSize,roomSize[roomCount++]);
				}
		return new int [] {roomCount-1,maxSize};
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("castle.in"));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int m=Integer.parseInt(st.nextToken()), n=Integer.parseInt(st.nextToken());
		mask=new int [n][m];
		for (int x=0;x<n;x++) {
			st=new StringTokenizer(br.readLine());
			for (int y=0;y<m;y++) mask[x][y]=Integer.parseInt(st.nextToken());
		}
		int [] ans=floodfillAll();

		StringBuilder sb=new StringBuilder();
		sb.append(ans[0]); sb.append("\n"); sb.append(ans[1]); sb.append("\n"); 
		int maxSize=0; int [] coor=new int [2]; char c='Z';
		for (int y=0;y<m;y++) {
			for (int x=n-1;x>=0;x--) {
				if (x>0 && ((mask[x][y] & (1 << 1))!=0) && visited[x][y]!=visited[x-1][y]) {
					int s=roomSize[visited[x][y]]+roomSize[visited[x-1][y]];
					if (s>maxSize) {
						coor[0]=x+1; coor[1]=y+1; c='N';
						maxSize=s;
					}
				}
				if (y<m-1 && ((mask[x][y] & (1 << 2))!=0) && visited[x][y]!=visited[x][y+1]) {
					int s=roomSize[visited[x][y]]+roomSize[visited[x][y+1]];
					if (s>maxSize) {
						coor[0]=x+1; coor[1]=y+1; c='E';
						maxSize=s;
					}
				}
			}
		}
		
		sb.append(maxSize);sb.append("\n");sb.append(coor[0]);sb.append(' ');sb.append(coor[1]);sb.append(' ');sb.append(c);
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		pw.println(sb.toString());
		pw.close();
	}

}
