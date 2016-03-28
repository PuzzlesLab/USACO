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
        int length=Integer.parseInt(br.readLine());
        int [][] value=new int [length][length];
        for (int i=0;i<length;i++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	for (int j=0;j<=i;j++) {
        		value[i][j]=Integer.parseInt(st.nextToken());
        	}
        }
        int [] dp = new int [length];
        dp[0]=value[0][0];
        for (int i=1;i<length;i++) {
        	int [] newDP=new int [i+1];
        	for (int j=0;j<=i;j++) {
        		if (j==0) {
        			newDP[j]=dp[j]+value[i][j];
        		} else if (j<i) {
        			newDP[j]=Math.max(dp[j-1],dp[j])+value[i][j];
        		} else {
        			newDP[j]=dp[j-1]+value[i][j];
        		}
        	}
        	dp=newDP;
        }

        int max=Integer.MIN_VALUE;
        for (int i=0;i<dp.length;i++) {
        	max=Math.max(max, dp[i]);
        }
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
        pw.println(max);
        pw.close();
        System.exit(0);
    }
}