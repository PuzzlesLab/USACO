/*
ID:king8791
LANG:JAVA 
TASK:runround
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class runround {

	
	public static boolean isRunaroundNumbers (long num) {
		char [] c=String.valueOf(num).toCharArray();
		boolean [] exists=new boolean [128];
		exists['0']=true;
		for (int i=0;i<c.length;i++) {
			if (!exists[c[i]]) {
				exists[c[i]]=true;
			} else {
				return false;
			}
		}
		boolean [] checked=new boolean [c.length];
		int currPos=0;
		int count=0;
		while (count<=c.length) {
			if (checked[currPos]) {
				if (count<c.length) {
					return false;
				} else {
					return currPos==0;
				}
			} else {
				checked[currPos]=true;
				currPos=(currPos+(c[currPos]-48))%c.length;
				count++;
			}
		}
		return true;
	}
	
	public static void main (String[] abc) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("runround.in"));
		long num=Long.parseLong(br.readLine())+1;
		while (true) {
			if (isRunaroundNumbers(num)) {
				break;
			}
			num++;
		}
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		pw.println(num);
		pw.close();
	}
	
}