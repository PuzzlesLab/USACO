/*
ID:king8791
LANG:JAVA 
TASK:subset
*/

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;

class subset {
	
	public static int N;
	public static long [] Count;
	public static int SumToN;
	
	public static void main (String[] abc) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("subset.in"));
		N=Integer.parseInt(br.readLine());
		SumToN=(N*(N+1))/2;
		Count=new long [SumToN/2+1];
		Count[0]=1;
		if (SumToN%2==0) {
		    for (int i=1;i<=N;i++){
		        for (int j=SumToN/2;j>=i;j--){
		            Count[j]+=Count[j-i]; //Add counter to possible combinations.
		        }
		    }
		}
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		pw.println(Count[SumToN/2]/2); //2 sides, therefore divide by 2.
		pw.close();
	}
	
}
