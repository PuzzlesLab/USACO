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
    
    public static boolean isPalindrome (String s) {
        char [] c=s.toCharArray();
        int max=c.length-1;
        for (int i=0;i<c.length/2;i++) {
            if (c[i]!=c[max-i]) {
                return false;
            }
        }
        return true;
    }
    
    public static String base10ToBaseN(int value, int n) {
        int maxPower=(int)(Math.log(value)/Math.log(n));
        String s="";
        int quotient;
        int divisor;
        for (int i=maxPower;i>=0;i--) {
            divisor=(int)(Math.pow(n, i));
            quotient=value/divisor;
            value=value-(quotient*divisor);
            s=s+quotient;
        }
        return s;
    }
    
    public static boolean hasDoublePalindrome(int value) {
        int count=0;
        if (isPalindrome(String.valueOf(value))) {
            count++;
        }
        for (int i=2;i<=9;i++) {
            if (isPalindrome(base10ToBaseN(value,i))) {
                count++;
                if (count==2) {
                    break;
                }
            }
        }
        return (count==2);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("dualpal.in"));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int count=Integer.parseInt(st.nextToken());
        int num=Integer.parseInt(st.nextToken())+1;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        for (;count>0;num++) {
            if (hasDoublePalindrome(num)) {
                pw.println(num);
                count--;
            }
        }
        pw.close();
        System.exit(0);
    }
    
}