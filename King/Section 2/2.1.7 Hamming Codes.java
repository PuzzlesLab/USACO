/*
ID:king8791
LANG:JAVA 
TASK:hamming
*/ 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class hamming {
	
	public static int Bits;
	public static int Distance;
	
	public static boolean valid (int int1, int int2) {
		int count=0;
		int1=int1<<1;
		int2=int2<<1;
		while (int1>>1!=int2>>1) {
			int1=int1>>1;
			int2=int2>>1;
			if (int1%2!=int2%2) {
				count++;
				if (count==Distance) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main (String [] abc) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("hamming.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        Bits=Integer.parseInt(st.nextToken());
        Distance=Integer.parseInt(st.nextToken());
        int [] codes=new int [n];
        int codesCount=0;
        codes[codesCount++]=0;
        boolean flag;
        for (int i=1;codesCount<n;i++) {
        	flag=true;
        	for (int i2=0;i2<codesCount;i2++) {
        		if (!valid(i,codes[i2])) {
        			flag=false;
        			break;
        		}
        	}
        	if (flag) {
        		codes[codesCount++]=i;
        	}
        }
        String s="";
        for (int i=1;i<=codesCount;i++) {
        	s=s+codes[i-1]+" ";
        	if (i%10==0) {
        		s=s.substring(0,s.length()-1)+"\n";
        	}
        }
        if (codesCount+1%10==0) {
        	s=s.substring(0,s.length()-2);
        } else {
        	s=s.substring(0,s.length()-1);
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        pw.println(s);
        pw.close();
        System.exit(0);
	}
}