/*
ID: king8791
LANG: JAVA
TASK: dualpal
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class dualpal {
	
	private static boolean isPalindrome (String s) {
		int length=s.length();
		for (int i=0;i<length/2;i++) {
			if (s.charAt(i)!=s.charAt(length-1-i)) {
				return false;
			}
		}
		return true;
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("dualpal.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int s=Integer.parseInt(st.nextToken());
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        int total=0;
        for (int i=s+1;total<n;i++) {
        	int palinCount=0;
        	for (int base=2;base<=10;base++) {
        		if (isPalindrome(Integer.toString(i, base))) {
        			palinCount++;
        			if (palinCount>=2) {
        				break;
        			}
        		}
        	}
        	if (palinCount>=2) {
        		total++;
        		pw.println(i);
        	}
        }
        pw.close();
    }
}