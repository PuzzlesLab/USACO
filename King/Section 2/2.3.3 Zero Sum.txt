/*
ID:king8791
LANG:JAVA 
TASK:zerosum
*/  

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class zerosum {
	
	public static String [] solutions=new String[100];
	public static int solutionsCount=0;
	
	public static void dfs (int currNum, int max, String expr) {
		if (currNum<max) {
			int nextNum=currNum+1;
			//
			StringBuilder sb=new StringBuilder(expr);
			sb.append("+");
			sb.append(nextNum);
			dfs(nextNum,max,sb.toString());
			//
			
			//
			sb=new StringBuilder(expr);
			sb.append("-");
			sb.append(nextNum);
			dfs(nextNum,max,sb.toString());
			//
			
			//
			sb=new StringBuilder(expr);
			sb.append(" ");
			sb.append(nextNum);
			dfs(nextNum,max,expr+" "+(currNum+1));
			//
		} else {
			//building expression.
			char lastOperator=0;
			int sum=0;
			for (int i=0;i<expr.length();i++) {
				char c=expr.charAt(i);
				if (c!=' ') {
					if (c=='+' || c=='-') {
						lastOperator=c;
					} else {
						int number=c-'0';
						int i2=i+1;
						for (;i2<expr.length();i2++) {
							char c2=expr.charAt(i2);
							if (c2>='0' && c2<='9') {
								number=number*10+(c2-'0');
							} else if (c2!=' '){
								break;
							}
						}
						if (lastOperator=='+') {
							sum+=number;
						} else if (lastOperator=='-') {
							sum-=number;
						} else {
							sum=number;
						}
						i=i2-1;
					}
				}
			}
			if (sum==0) {
				solutions[solutionsCount++]=expr;
			}
		}
	}
	
	public static void main (String [] zzzz) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("zerosum.in"));
		int value=Integer.parseInt(br.readLine());
		br.close();
		dfs(1,value,"1");
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		Arrays.sort(solutions,0,solutionsCount);
		for (int i=0;i<solutionsCount;i++) {
			pw.println(solutions[i]);
		}
		pw.close();
	}
}