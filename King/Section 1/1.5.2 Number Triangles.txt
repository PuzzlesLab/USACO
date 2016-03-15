/*
ID: king8791
LANG: JAVA
TASK: numtri
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class numtri {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("numtri.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int height=Integer.parseInt(st.nextToken());
        int [][] numbers=new int [height][height];
        int [][] sum=new int[height][height];
        for (int i=0;i<height;i++) {
            st=new StringTokenizer(br.readLine());
            for (int i2=0;i2<=i;i2++) {
                numbers[i][i2]=Integer.parseInt(st.nextToken());
            }
        }
        int max=numbers[0][0];
        sum[0][0]=max;
        for (int i=1;i<height;i++) {
            sum[i][0]=numbers[i][0]+sum[i-1][0];
            sum[i][i]=numbers[i][i]+sum[i-1][i-1];
            if (sum[i][0]>max) {
                max=sum[i][0];
            }
            if (sum[i][i]>max) {
                max=sum[i][i];
            }
            for (int i2=1;i2<i;i2++) {
                if (sum[i-1][i2-1]>sum[i-1][i2]) {
                    sum[i][i2]=numbers[i][i2]+sum[i-1][i2-1];
                } else {
                    sum[i][i2]=numbers[i][i2]+sum[i-1][i2];
                }
                if (sum[i][i2]>max) {
                    max=sum[i][i2];
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
        pw.println(max);
        pw.close();
        System.exit(0);
    }
}