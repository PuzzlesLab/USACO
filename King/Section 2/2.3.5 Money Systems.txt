/*
ID:king8791
LANG:JAVA 
TASK:money 
*/  

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


class money {
	public static void main (String [] zzz) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("money.in"));
		String [] s=br.readLine().split(" ");
		int coinTypeCount=Integer.parseInt(s[0]);
		int value=Integer.parseInt(s[1]);
		int [] coinsValue=new int [coinTypeCount];
		int c=coinTypeCount;
		while (c>0) {
			s=br.readLine().split(" ");
			for (int i=0;i<s.length;i++) {
				coinsValue[--c]=Integer.parseInt(s[i]);
			}
		}
		Arrays.sort(coinsValue);
		long [] dp=new long [value+1];
		dp[0]=1;
		for (int i=0;i<coinTypeCount;i++) {
			for (int i2=coinsValue[i];i2<=value;i2++) {
				dp[i2]=dp[i2]+dp[i2-coinsValue[i]];
			}
		}
		System.out.println(dp[value]);
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		pw.println(dp[value]);
		pw.close();
	}
}
