/*
ID:king8791
LANG:JAVA 
TASK:prefix
*/ 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

class prefix {
	
	public static void main (String [] zzzz) throws IOException {
		long time=System.currentTimeMillis();
		BufferedReader br=new BufferedReader(new FileReader("prefix.in"));
		String [][] prefixList=new String [26][70];
		int [][] prefixLengthList=new int [26][70];
		int [] prefixListCount=new int [26];
		String line;
		while (!(line=br.readLine()).equals(".")) {
			String [] prefixes=line.split(" ");
			for (int i=0;i<prefixes.length;i++) {
				int index=prefixes[i].charAt(0)-'A';
				prefixList[index][prefixListCount[index]]=prefixes[i];
				prefixLengthList[index][prefixListCount[index]++]=prefixes[i].length();
			}
		}
		int max=0;
		String seq="";
		StringBuilder sb=new StringBuilder();
		while ((line=br.readLine())!=null) {
			sb.append(line);
		}
		seq=sb.toString();
		for (int i=0;i<Math.min(max+1,seq.length());i++) {
			int index=seq.charAt(i)-'A';
			for (int i2=0;i2<prefixListCount[index];i2++) {
				if (i+prefixLengthList[index][i2]>max && prefixLengthList[index][i2]<=seq.length()-i && seq.substring(i,i+prefixLengthList[index][i2]).equals(prefixList[index][i2])) {
					max=Math.max(max,i+prefixLengthList[index][i2]);
				}
			}
		}
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		pw.println(max);
		pw.close();
		System.out.println(System.currentTimeMillis()-time);
	}
}