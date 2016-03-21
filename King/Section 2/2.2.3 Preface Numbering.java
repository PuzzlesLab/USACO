/*
ID:king8791
LANG:JAVA 
TASK:preface
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class preface {
	
	public static String [] charSet={"I","IV","V","IX","X","XL","L","XC","C","CD", "D", "CM", "M"};
	public static int [] charSetValue={1,  4,  5,   9, 10,  40,  50, 90, 100, 400, 500, 900, 1000};
	public static int [] count;
	
	public static void generateNum (int num) {
		for (int i=charSetValue.length-1;i>=0;i--) {
			if (num>=charSetValue[i]) {
				num-=charSetValue[i];
				for (int i2=0;i2<charSet[i].length();i2++) {
					count[charSet[i].charAt(i2)]++;
				}
				i=charSetValue.length;
			}
		}
	}
	
	public static void main (String [] abc) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("preface.in"));
		int n=Integer.parseInt(br.readLine());
		count=new int [128];
		for (int i=1;i<=n;i++) {
			generateNum(i);
		}
		char [] c={'I','V','X','L','C','D','M'};
		String s="";
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		for (int i=0;i<c.length;i++) {
			if (count[c[i]]>0) {
				s=s+c[i]+" "+count[c[i]]+"\n";
			}
		}
		pw.print(s);
		pw.close();
		System.exit(0);
	}
}